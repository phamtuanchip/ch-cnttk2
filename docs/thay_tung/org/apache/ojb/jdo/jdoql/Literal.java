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
 * A literal.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class Literal extends Expression
{
    /** The type of the literal */
    private Class  _type;
    /** The textual representation */
    private String _textRep;

    /**
     * Creates a new literal object.
     * 
     * @param type    The type of the literal
     * @param textRep The textual representation
     */
    public Literal(Class type, String textRep)
    {
        _type    = type;
        _textRep = textRep;
    }

    /**
     * Returns the (original) textual representation.
     * 
     * @return The text 
     */
    public String getTextRepresentation()
    {
        return _textRep;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        return _type;
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
        return _textRep;
    }
}
