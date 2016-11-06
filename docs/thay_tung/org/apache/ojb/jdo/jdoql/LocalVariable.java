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

/**
 * Encapsulates a variable local to the JDOQL query. This can be either a
 * variable or a parameter. Variables must be at some point bound to values
 * either directly by the user of the query (if a parameter) or during
 * evaluation of the query.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class LocalVariable extends QueryTreeNode
{
    /** The name of the variable */
    private String  _name;
    /** The type of the variable */
    private Type    _type;
    /** Whether this variable is already bound to a value */
    private boolean _isBound = false;
    /** The value of the variable */
    private Object  _value;

    /**
     * Creates a new variable object.
     * 
     * @param type The type of the variable
     * @param name The name of the variable
     */
    public LocalVariable(Type type, String name)
    {
        _type = type;
        _name = name;
    }
    
    /**
     * Returns the type of the variable.
     *  
     * @return The type
     */
    public Type getType()
    {
        return _type;
    }

    /**
     * Returns the value of this variable
     * 
     * @return The value
     */
    public Object getValue()
    {
        return _value;
    }

    /**
     * Sets the value of the variable.
     * 
     * @param value The value
     */
    public void setValue(Object value)
    {
        _value   = value;
        _isBound = true;
    }

    /**
     * Returns whether this variable is bound to a value.
     *  
     * @return <code>true</code> if the variable has a value
     */
    public boolean isBound()
    {
        return _isBound;
    }

    /**
     * Returns the name of the variable.
     * 
     * @return The name
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
        return _type.toString() + " " + _name;
    }
}
