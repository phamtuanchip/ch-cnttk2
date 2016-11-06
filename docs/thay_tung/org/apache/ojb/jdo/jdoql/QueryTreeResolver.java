package org.apache.ojb.jdo.jdoql;

/* Copyright 2003-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jdo.JDOUserException;

import org.apache.ojb.broker.metadata.*;
import org.apache.ojb.broker.util.ClassHelper;
import org.apache.ojb.jdo.QueryImpl;

/**
 * Resolves and checks the query trees. This involves the following things:<br/>
 * <ul>
 * <li>Replace {@link NameExpression NameExpression} with {@link LocalVariableAccess VariableAccess},
 *     {@link FieldAccess FieldAccess}, or {@link TypeAccess TypeAccess}</li>
 * <li>Resolve types referenced by {@link Type Type} and {@link TypeAccess TypeAccess}</li>
 * <li>Resolve features/variables referenced by {@link LocalVariableAccess VariableAccess},
 *     {@link FieldAccess FieldAccess}, and {@link MethodInvocation MethodInvocation}</li>
 * <li>Check that expression types are valid, e.g. that the types of the two sides of a binary
 *     expression are compatible to each other and that this binary operator can be applied
 *     to them</li>
 * <li>Check that the accessed methods are allowed to be accessed as per JDO spec (or
 *     extensions allowed by OJB)</li>
 * <li>Check that for each accessed variable there is a variable or parameter defined</li>
 * <li>Check that each accessed field is persistent</li>
 * </ul> 
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class QueryTreeResolver extends VisitorBase
{
    // TODO: Integrate position info into the exceptions

    /** Defines the ordering of primitive types */
    private static HashMap _primitiveTypes = new HashMap();

    static
    {
        _primitiveTypes.put(byte.class,       new Integer(0));
        _primitiveTypes.put(char.class,       new Integer(1));
        _primitiveTypes.put(short.class,      new Integer(2));
        _primitiveTypes.put(int.class,        new Integer(3));
        _primitiveTypes.put(long.class,       new Integer(4));
        _primitiveTypes.put(BigInteger.class, new Integer(5));
        _primitiveTypes.put(float.class,      new Integer(6));
        _primitiveTypes.put(double.class,     new Integer(7));
        _primitiveTypes.put(BigDecimal.class, new Integer(8));
    }
    
    /** The currently resolved query */ 
    private QueryImpl _query;

    /**
     * Resolves and checks the given query. Results from a previous resolving are
     * overwritten.
     *  
     * @param query The query
     * @exception JDOUserException If the query is invalid
     */
    public void resolveAndCheck(QueryImpl query) throws JDOUserException
    {
        _query = query;
        try
        {
            checkImports(query.getImports());
            visit(query.getVariables());
            visit(query.getParameters());
            if (query.getFilterExpression() != null)
            {
                query.getFilterExpression().accept(this);
            }
            visit(query.getOrderings());
        }
        finally
        {
            _query = null;
        }
    }

    /**
     * Checks the imports. We're a bit more foregiving as the JDO Spec because
     * we don't demand that all imported types/packages exists as long as they
     * are not used.
     * 
     * @param imports The imports to check
     * @throws JDOUserException If two direct imports imports types with the same
     *                          short name
     */
    private void checkImports(Collection imports) throws JDOUserException
    {
        // It is an error if two direct imports import classes with the same short name
        HashMap directlyImportedClasses = new HashMap();
        Import  importDecl;
        String  shortName;

        for (Iterator it = imports.iterator(); it.hasNext();)
        {
            importDecl = (Import)it.next();
            if (!importDecl.isOnDemand())
            {
                shortName = importDecl.getSpec().substring(importDecl.getSpec().lastIndexOf('.'));
                if (directlyImportedClasses.containsKey(shortName))
                {
                    throw new JDOUserException("Multiple direct imports of classes with the same unqualified name "+shortName);
                }
                else
                {
                    directlyImportedClasses.put(shortName, null);
                }
            }
        }
    }
    
    /**
     * Visits all elements of the given map.
     * 
     * @param objects The objects
     * @exception JDOUserException If one of the elements is invalid
     */
    private void visit(Map objects) throws JDOUserException
    {
        if (objects != null)
        {
            for (Iterator it = objects.values().iterator(); it.hasNext();)
            {
                ((Acceptor)it.next()).accept(this);
            }
        }
    }
    
    /**
     * Visits all elements of the given collection.
     * 
     * @param objects The objects
     * @exception JDOUserException If one of the elements is invalid
     */
    private void visit(Collection objects) throws JDOUserException
    {
        if (objects != null)
        {
            for (Iterator it = objects.iterator(); it.hasNext();)
            {
                ((Acceptor)it.next()).accept(this);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.BinaryExpression)
     */
    public void visit(BinaryExpression binExpr) throws JDOUserException
    {
        super.visit(binExpr);

        // for the types of the left and right expressions one of the following must hold:
        // * the operator is '+' and both are String
        // * the operator is arithmethic and both are numeric (Character, Long or Double)
        // * the operator is bitwise and both are Character or Long
        // * the operator is logic and both are Boolean
        // * the operator is comparative and the both are String, or both are Date, or both are Numeric
        Class   leftType   = binExpr.getLeftSide().getType();
        Class   rightType  = binExpr.getRightSide().getType();
        boolean typeWasSet = false;

        switch (binExpr.getOperator())
        {
            case BinaryExpression.OPERATOR_MULTIPLY :
            case BinaryExpression.OPERATOR_DIVIDE :
            case BinaryExpression.OPERATOR_PLUS :
            case BinaryExpression.OPERATOR_MINUS :
                if (binExpr.getOperator() == BinaryExpression.OPERATOR_PLUS)
                {
                    if ((leftType == String.class) && (rightType == String.class))
                    {
                        binExpr.setType(String.class);
                        typeWasSet = true;
                        break;
                    }
                }
                if (isNumeric(leftType) && isNumeric(rightType))
                {
                    binExpr.setType(getBroaderType(leftType, rightType));
                    typeWasSet = true;
                }
                break;
            case BinaryExpression.OPERATOR_BITWISE_AND :
            case BinaryExpression.OPERATOR_BITWISE_XOR :
            case BinaryExpression.OPERATOR_BITWISE_OR :
                if (isInteger(leftType) && isInteger(rightType))
                {
                    binExpr.setType(getBroaderType(leftType, rightType));
                    typeWasSet = true;
                }
                else if ((leftType == boolean.class) && (rightType == boolean.class))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                break;
            case BinaryExpression.OPERATOR_LOWER :
            case BinaryExpression.OPERATOR_GREATER :
            case BinaryExpression.OPERATOR_LOWER_OR_EQUAL :
            case BinaryExpression.OPERATOR_GREATER_OR_EQUAL :
                if (isNumeric(leftType) && isNumeric(rightType))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                else if ((leftType == String.class) && (rightType == String.class))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                else if ((leftType == Date.class) && (rightType == Date.class))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                break;
            case BinaryExpression.OPERATOR_EQUAL :
            case BinaryExpression.OPERATOR_NOT_EQUAL :
                if (isNumeric(leftType) && isNumeric(rightType))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                else if ((leftType == boolean.class) && (rightType == boolean.class))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                else if (!leftType.isPrimitive() && !rightType.isPrimitive())
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                break;
            case BinaryExpression.OPERATOR_AND :
            case BinaryExpression.OPERATOR_OR :
                if ((leftType == boolean.class) && (rightType == boolean.class))
                {
                    binExpr.setType(boolean.class);
                    typeWasSet = true;
                }
                break;
        }
        if (!typeWasSet)
        {
            throw new JDOUserException("Binary expression cannot be applied to expressions of types "+leftType.getName()+" and "+rightType.getName());
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.MethodInvocation)
     */
    public void visit(MethodInvocation methodInvoc) throws JDOUserException
    {
        super.visit(methodInvoc);

        Class  type = methodInvoc.getBaseExpression().getType();
        String name = methodInvoc.getName();

        if (Collection.class.isAssignableFrom(type))
        {
            if ("contains".equals(name))
            {
                if (methodInvoc.getArguments().size() != 1)
                {
                    throw new JDOUserException("Illegal number of arguments to method Collection.contains");
                }
            }
            else if ("isEmpty".equals(name))
            {
                if (!methodInvoc.getArguments().isEmpty())
                {
                    throw new JDOUserException("Illegal number of arguments to method Collection.isEmpty");
                }
            }
            else
            {
                throw new JDOUserException("Only methods 'contains' and 'isEmpty' are allowed to be called at collection objects");
            }
            methodInvoc.setType(boolean.class);
        }
        else if (type == String.class)
        {
            if (!"startsWith".equals(name) && !"endsWith".equals(name))
            {
                throw new JDOUserException("Only methods 'contains' and 'isEmpty' are allowed to be called at collection objects");
            }
            if (methodInvoc.getArguments().size() != 1)
            {
                throw new JDOUserException("Illegal number of arguments to method String."+name);
            }
            if (((Expression)methodInvoc.getArguments().get(0)).getType() != String.class)
            {
                throw new JDOUserException("Illegal argument to method Collection."+name);
            }
            methodInvoc.setType(boolean.class);
        }
        else
        {
            throw new JDOUserException("Invocation of method "+methodInvoc.getName()+" at type "+type.getName()+" is not allowed");
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.NameExpression)
     */
    public void visit(NameExpression nameExpr) throws JDOUserException
    {
        super.visit(nameExpr);

        Expression newExpr  = null;
        Class      baseType = null;

        // we search in this order (which corresponds to Java's name resolution order):
        //   * variables (only if without base expression)
        //   * parameters (only if without base expression)
        //   * fields/references/collections (at searched type if without base expression)
        // types are currently not searched for as they are not allowed to appear directly
        // except in casts
        if (!nameExpr.hasBaseExpression())
        {
            // no base expressiom
            LocalVariable var = _query.getVariable(nameExpr.getName());

            if (var == null)
            {
                var = _query.getParameter(nameExpr.getName());
            }
            if (var != null)
            {
                LocalVariableAccess varAccess = new LocalVariableAccess(nameExpr.getName());

                varAccess.setAccessedVariable(var);
                newExpr = varAccess;
            }
            else
            {
                // neither a variable nor parameter, so we must search in the class
                // whose objects the query searches for
                baseType = _query.getSearchedClass();
            }
        }
        else
        {
            // we have a base expression which means that we follow a reference
            baseType = nameExpr.getBaseExpression().getType();
        }
        if (newExpr == null)
        {
            // so we determine the persistent type of the base expression
            ClassDescriptor classDesc = findClassDescriptorFor(baseType);
            
            if (classDesc == null)
            {
                throw new JDOUserException("Access to type "+baseType.getName()+" is not allowed because the type is not persistent");
            }

            FieldAccess fieldAccess = new FieldAccess(nameExpr.getBaseExpression(), nameExpr.getName());
            
            // it may be either a field, reference or collection descriptor -
            // this depends on whether the name expression is a base expression
            // to another name expression (not a method invocation or other expression)
            ObjectReferenceDescriptor refDesc = classDesc.getObjectReferenceDescriptorByName(nameExpr.getName());
            
            if (refDesc != null)
            {
                fieldAccess.setFieldDescriptor(refDesc);
            }
            else if (nameExpr.hasParent() && (nameExpr.getParent() instanceof NameExpression))
            {
                // if we are the base expression of another name expression, then it must be a reference
                throw new JDOUserException("Cannot find reference "+nameExpr.getName()+" in type "+baseType.getName()+" because it is not defined, not persistent or not a reference");
            }
            else
            {
                // it can be a field or collection
                CollectionDescriptor collDesc = classDesc.getCollectionDescriptorByName(nameExpr.getName());
                if (collDesc != null)
                {
                    fieldAccess.setFieldDescriptor(collDesc);
                }
                else
                {
                    FieldDescriptor fieldDesc = classDesc.getFieldDescriptorByName(nameExpr.getName());
    
                    if (fieldDesc == null)
                    {
                        throw new JDOUserException("Cannot find feature "+nameExpr.getName()+" in type "+baseType.getName()+" because it is not defined or not persistent");
                    }
                    fieldAccess.setFieldDescriptor(fieldDesc);
                }
            }
            newExpr = fieldAccess;
        }

        if (nameExpr.hasParent())
        {
            nameExpr.getParent().replaceChild(nameExpr, newExpr);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.NullLiteral)
     */
    public void visit(NullLiteral nullLit)
    {
        Expression parent = nullLit.getParent();

        if (parent == null)
        {
            return;
        }

        // the only interesting case is if the parent is a binary expression (e.g.
        // a '==' expression) in which case the null literal shall have the same
        // type as the other operand
        if (parent instanceof BinaryExpression)
        {
            Class type = null;

            if (((BinaryExpression)parent).getLeftSide() == nullLit)
            {
                type = ((BinaryExpression)parent).getRightSide().getType();
            }
            else
            {
                type = ((BinaryExpression)parent).getLeftSide().getType();
            }
            if (type.isPrimitive())
            {
                throw new JDOUserException("Illegal binary expression with a 'null' and a primitive operand");
            }
            nullLit.setType(type);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.ThisExpression)
     */
    public void visit(ThisExpression thisExpr)
    {
        super.visit(thisExpr);
        thisExpr.setType(_query.getSearchedClass());
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.Type)
     */
    public void visit(Type type) throws JDOUserException
    {
        ClassLoader loader = ClassHelper.getClassLoader();
        Class       result = null;
        String      name   = type.getName();
        int         pos    = name.indexOf('.');

        if (pos >= 0)
        {
            // its either a qualified name or refers to an inner class
            // we ignore inner classes here as they are handled by the import
            // resolution below
            // [tomdz] we might have to resolve inner/nested classes manually
            //         if the query should be executed in the scope of the
            //         searched class (which makes private/protected/friendly
            //         inner/nested classes visible)
            //         for now we assume that this is not the case
            try
            {
                result = Class.forName(name, true, loader);
            }
            catch (ClassNotFoundException ex)
            {
                // ignored
            }
        }
        if (result == null)
        {
            result = resolveUnqualifiedClassName(loader, name);
        }
        if (result == null)
        {
            throw new JDOUserException("No such class "+name);
        }
        else
        {
            type.setType(result);
        }
    }

    /**
     * Resolves the given unqualified class name against the imports of the query.
     * 
     * @param loader          The class loader to use
     * @param unqualifiedName The unqualified class name
     * @return The class if it has been found
     * @exception JDOUserException If a direct import declaration is invalid
     */
    private Class resolveUnqualifiedClassName(ClassLoader loader, String unqualifiedName) throws JDOUserException
    {
        // A direct import has precedence over on-demand imports of packages that contain
        // a class of the same short name (JLS 7.5.1)
        // If multiple on-demand imports import packages that have a class with a given
        // short name, then the last such import defines the class to be used
        Import importDecl;
        Class  result = null;
        int    pos;

        // we first try to resolve it against java.lang
        try
        {
            result = Class.forName("java.lang." + unqualifiedName, true, loader);
        }
        catch (ClassNotFoundException ex)
        {
            // ignored
        }
        for (Iterator it = _query.getImports().iterator(); it.hasNext();)
        {
            importDecl = (Import)it.next();
            if (importDecl.isOnDemand())
            {
                try
                {
                    result = Class.forName(importDecl.getSpec() + "." + unqualifiedName, true, loader);
                }
                catch (ClassNotFoundException ex)
                {
                    // ignored
                }
            }
            else
            {
                pos = importDecl.getSpec().lastIndexOf('.');
                if (unqualifiedName.equals(importDecl.getSpec().substring(pos + 1)))
                {
                    try
                    {
                        // there can only be one direct import of a class with this
                        // unqualified name (imports have already been checked), and
                        // no on-demand import can shadow it, so we can simply return it
                        return Class.forName(importDecl.getSpec() + "." + unqualifiedName, true, loader);
                    }
                    catch (ClassNotFoundException ex)
                    {
                        // we have a direct import for the class but the import is invalid
                        throw new JDOUserException("The import "+importDecl.getSpec()+" is invalid");
                    }
                }
            }
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.UnaryExpression)
     */
    public void visit(UnaryExpression unaryExpr) throws JDOUserException
    {
        super.visit(unaryExpr);

        // one of the following must hold:
        // * the operator is arithmetic and the inner type is numeric
        // * the operator is bitwise and the inner type is Character or Long
        // * the operator is logic and the inner type is Boolean
        // * it is a cast and the cast type is assignment compatible to the inner type
        Class   innerType  = unaryExpr.getInnerExpression().getType();
        boolean typeWasSet = false;

        switch (unaryExpr.getOperator())
        {
            case UnaryExpression.OPERATOR_PLUS :
            case UnaryExpression.OPERATOR_MINUS :
                if (isNumeric(innerType))
                {
                    unaryExpr.setType(innerType);
                    typeWasSet = true;
                }
                break;
            case UnaryExpression.OPERATOR_BITWISE_COMPLEMENT :
                if (isInteger(innerType))
                {
                    unaryExpr.setType(innerType);
                    typeWasSet = true;
                }
                break;
            case UnaryExpression.OPERATOR_NOT :
                if (innerType == boolean.class)
                {
                    unaryExpr.setType(innerType);
                    typeWasSet = true;
                }
                break;
            case UnaryExpression.OPERATOR_CAST :
                Class castType = unaryExpr.getCastType().getType();

                if (isNumeric(castType) && isNumeric(innerType))
                {
                    unaryExpr.setType(castType);
                    typeWasSet = true;
                }
                else
                {
                    // check for narrowing or widening reference conversion
                    if (castType.isAssignableFrom(innerType) || innerType.isAssignableFrom(castType))
                    {
                        unaryExpr.setType(castType);
                        typeWasSet = true;
                    }
                }
                break;

        }
        if (!typeWasSet)
        {
            if (unaryExpr.getOperator() == UnaryExpression.OPERATOR_CAST)
            {
                throw new JDOUserException("Invalid cast expression because inner expression of type "+innerType.getName()+" cannot be cast to "+unaryExpr.getCastType().getName());
            }
            else
            {
                throw new JDOUserException("Invalid unary expression");
            }
        }
    }

    // Helper methods

    /**
     * Retrieves OJB's class descriptor for the given type.
     * 
     * @param The type to lookup
     * @return The class descriptor or <code>null</code> if the class is not persistent
     */
    private ClassDescriptor findClassDescriptorFor(Class type)
    {
        return MetadataManager.getInstance().getRepository().getDescriptorFor(type);
    }

    /**
     * Determines whether the given class denotes an integer primitive type.
     * 
     * @param type The type
     * @return <code>true</code> if the type is an integer type
     */
    private static boolean isInteger(Class type)
    {
        if (type.isPrimitive())
        {
            return (type != boolean.class) && (type != float.class) && (type != double.class);
        }
        else
        {
            return type == BigInteger.class;
        }
    }

    /**
     * Determines whether the given class denotes a floating point primitive type.
     * 
     * @param type The type
     * @return <code>true</code> if the type is a floating point type
     */
    private static boolean isFloatingPoint(Class type)
    {
        if (type.isPrimitive())
        {
            return (type == float.class) || (type == double.class);
        }
        else
        {
            return type == BigDecimal.class;
        }
    }

    /**
     * Determines whether the given class denotes a numeric primitive type.
     * 
     * @param type The type
     * @return <code>true</code> if the type is a numeric type
     */
    private static boolean isNumeric(Class type)
    {
        if (type.isPrimitive())
        {
            return type != boolean.class;
        }
        else
        {
            return (type == BigDecimal.class) || (type == BigInteger.class);
        }
    }

    /**
     * Determines the broader of the two given numeric types.
     * 
     * @param typeA The first type
     * @param typeB The seconf type
     * @return The broader of the two types
     */
    private static Class getBroaderType(Class typeA, Class typeB)
    {
        Integer numA = (Integer)_primitiveTypes.get(typeA);
        Integer numB = (Integer)_primitiveTypes.get(typeB);

        return numA.intValue() < numB.intValue() ? typeB : typeA;
    }
}
