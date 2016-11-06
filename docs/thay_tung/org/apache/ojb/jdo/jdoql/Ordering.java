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
 * Encapsulates an ordering.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class Ordering extends QueryTreeNode
{
    /** The ordering expression */
    private Expression _expr;
    /** Whether this is an ascending ordering specification */
    private boolean    _isAscending = true;

    /**
     * Creates a new ordering object.
     * 
     * @param expr        The ordering expression
     * @param isAscending Whether this is an ascending ordering specification
     */
    public Ordering(Expression expr, boolean isAscending)
    {
        _expr        = expr;
        _isAscending = isAscending;
    }
    
    /**
     * Returns the ordering expression.
     *  
     * @return The expression
     */
    public Expression getExpression()
    {
        return _expr;
    }

    /**
     * Returns whether this ordering is ascending or descending.
     *  
     * @return <code>true</code> if the ordering is ascending
     */
    public boolean isAscending()
    {
        return _isAscending;
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
        return _expr.toString() + " " + (_isAscending ? "ascending" : "descending");
    }
}
