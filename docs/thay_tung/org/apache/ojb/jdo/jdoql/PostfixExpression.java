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
 * Specifies an expression that can follow another expression (e.g. in a.b the
 * field access to b follows the field access to a).
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public abstract class PostfixExpression extends Expression
{
    /** The base expression (can be <code>null</code>) */
    protected Expression _base;

    /**
     * Creates a new postfix expression. This constructor is intended to be called
     * from subclass' constructors.
     * 
     * @param base The base expression (can be <code>null</code>)
     */
    public PostfixExpression(Expression base)
    {
        _base = base;
    }
    
    /**
     * Determines whether this postfix expression has a base expression.
     * 
     * @return <code>true</code> if it has a base expression
     */
    public boolean hasBaseExpression()
    {
        return _base != null;
    }

    /**
     * Returns the base expression.
     * 
     * @return The base expression (can be <code>null</code>)
     */
    public Expression getBaseExpression()
    {
        return _base;
    }

    /**
     * Sets the base expression.
     * 
     * @param base The base expression (can be <code>null</code>)
     */
    public void setBaseExpression(Expression base)
    {
        if (_base != null)
        {
            _base.setParent(null);
        }
        _base = base;
        if (_base != null)
        {
            _base.setParent(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#replaceChild(org.apache.ojb.jdo.jdoql.Expression, org.apache.ojb.jdo.jdoql.Expression)
     */
    public void replaceChild(Expression oldChild, Expression newChild)
    {
        setBaseExpression(newChild);
    }
}
