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
 * The 'this' expression.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class ThisExpression extends Expression
{
    /** The resolved type (which is the type search type of the query) */
    private Class _type;

    /**
     * Creates a new this expression object. 
     */
    public ThisExpression()
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
        return "this";
    }

    /**
     * Sets the type of this expression.
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
