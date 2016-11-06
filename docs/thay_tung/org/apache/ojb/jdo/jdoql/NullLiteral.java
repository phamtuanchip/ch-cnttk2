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
 * The 'null' literal.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class NullLiteral extends Expression
{
    /** The type of this null literal */
    private Class _type = Object.class;

    /**
     * Creates a new null literal object. 
     */
    public NullLiteral()
    {
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
        return "null";
    }

    /**
     * Sets the type of this null literal. The type depends on the context
     * of the literal, e.g. in a binary expression where the other side
     * is of type string, this literal would also have type string.
     * 
     * @param type The type
     */
    public void setType(Class type)
    {
        _type = type;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        return _type;
    }
}
