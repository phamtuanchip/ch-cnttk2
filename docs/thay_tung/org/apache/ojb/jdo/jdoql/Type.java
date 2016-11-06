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

import org.apache.ojb.broker.util.ClassHelper;

/**
 * Encapsulates a type, either primitive or reference.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class Type extends QueryTreeNode
{
    /** The (possibly unqualified) name of the type */
    private String  _name;
    /** The type resolved using the imports of the query */
    private Class   _type;

    /**
     * Creates a new type object.
     * 
     * @param typeName    The (possibly unqualified) name of the variable
     * @param isPrimitive Whether the type is a primitive type
     */
    public Type(String typeName, boolean isPrimitive)
    {
        _name = typeName;
        if (isPrimitive)
        {
            resolvePrimitiveType();
        }
    }

    /**
     * Resolves the primitive type of this variable by determining the corresponding
     * <code>java.lang</code> type.
     */
    private void resolvePrimitiveType()
    {
        final ClassLoader loader = ClassHelper.getClassLoader();
        try
        {
            _type = Class.forName(_name, true, loader);
        }
        catch (ClassNotFoundException ex)
        {
            // won't happen because the parser is restricted to valid type names
        }
    }
    
    /**
     * Returns the actual class object of the type if already resolved.
     *  
     * @return The type or <code>null</code> if not yet resolved
     */
    public Class getType()
    {
        return _type;
    }

    /**
     * Sets the resolved type.
     * 
     * @param type The type's class object
     */
    public void setType(Class type)
    {
        _type = type;
        if (_type != null)
        {
            _name = type.getName();
        }
    }

    /**
     * Returns the variable type's name.
     * 
     * @return The type name
     */
    public String getName()
    {
        return _name;
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
        return _type == null ? _name : _type.getName();
    }
}
