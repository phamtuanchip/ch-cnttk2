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
 * A name expression which can mean an access to a variable/field or to a type.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class NameExpression extends PostfixExpression
{
    /** The name */
    private String _name;

    /**
     * Creates a new name expression object.
     * 
     * @param base The base expression (can be <code>null</code>)
     * @param name The name
     */
    public NameExpression(Expression base, String name)
    {
        super(base);
        _name = name;
    }

    /**
     * Returns the name.
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
        StringBuffer result = new StringBuffer();

        if (_base != null)
        {
            result.append(_base.toString());
            result.append(".");
        }
        result.append(_name);
        return result.toString();
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        // No need to return something because this expression is replaced
        // in the resolving stage
        return null;
    }
}
