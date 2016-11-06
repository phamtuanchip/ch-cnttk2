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

import java.util.*;

/**
 * A method invocation expression. Because invocation of methods on persistent
 * classes is not supported, we can simply resolve the invocation to a reflection
 * method object.
 * 
 * TODO: What is the best way to handle these (esp. Collection.contains which
 *       does not behave like the Java method) ?
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class MethodInvocation extends PostfixExpression
{
    /** The method name */
    private String     _name;
    /** The arguments (expressions) */
    private List       _args;
    /** The return type of the method */
    private Class      _returnType;

    /**
     * Creates a new method invocation object.
     * 
     * @param base       The base expression (can be <code>null</code>)
     * @param methodName The name of the method
     * @param args       The arguments
     */
    public MethodInvocation(Expression base, String methodName, List args)
    {
        super(base);
        _name = methodName;
        _args = args;
        for (Iterator it = args.iterator(); it.hasNext();)
        {
            ((Expression)it.next()).setParent(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.PostfixExpression#hasBaseExpression()
     */
    public boolean hasBaseExpression()
    {
        return _base != null;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.PostfixExpression#getBaseExpression()
     */
    public Expression getBaseExpression()
    {
        return _base;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.PostfixExpression#setBaseExpression(org.apache.ojb.jdo.jdoql.Expression)
     */
    public void setBaseExpression(Expression base)
    {
        _base = base;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#replaceChild(org.apache.ojb.jdo.jdoql.Expression, org.apache.ojb.jdo.jdoql.Expression)
     */
    public void replaceChild(Expression oldChild, Expression newChild)
    {
        if (oldChild == _base)
        {
            _base.setParent(null);
            _base = newChild;
            _base.setParent(this);
        }
        else
        {
            Expression expr;

            for (int idx = 0; idx < _args.size(); idx++)
            {
                expr = (Expression)_args.get(idx);
                if (expr == oldChild)
                {
                    expr.setParent(null);
                    newChild.setParent(this);
                    _args.set(idx, newChild);
                    break;
                }
            }
        }
    }

    /**
     * Returns the name of the accessed method.
     * 
     * @return The method's name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Returns the arguments of the invocation.
     * 
     * @return The arguments (list of expressions) 
     */
    public List getArguments()
    {
        return _args;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Acceptor#accept(org.apache.ojb.jdo.jdoql.Visitor)
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();

        if (_base != null)
        {
            result.append(_base.toString());
            result.append(".");
        }
        result.append(_name);
        result.append("(");
        for (Iterator it = _args.iterator(); it.hasNext();)
        {
            result.append(it.next().toString());
            if (it.hasNext())
            {
                result.append(", ");
            }
        }
        result.append(")");
        return result.toString();
    }

    /**
     * Sets the return type of the method.
     * 
     * @param type The return type
     */
    public void setType(Class type)
    {
        _returnType = type;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        return _returnType;
    }
}
