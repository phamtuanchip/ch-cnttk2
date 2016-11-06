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
 * A local variable access expression. Note that this expression type can only be
 * present in a query tree after resolving it.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class LocalVariableAccess extends NameExpression
{
    /** The accessed variable */
    private LocalVariable _variable;

    /**
     * Creates a new variable access object.
     * 
     * @param name The variable's name
     */
    public LocalVariableAccess(String name)
    {
        super(null, name);
    }

    /**
     * Returns the accessed variable.
     * 
     * @return The variable
     */
    public LocalVariable getAccessedVariable()
    {
        return _variable;
    }
    
    /**
     * Sets the accessed variable.
     * 
     * @param variable The variable
     */
    public void setAccessedVariable(LocalVariable variable)
    {
        _variable = variable;
    }
    
    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Acceptor#accept(org.apache.ojb.jdo.jdoql.Visitor)
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        return _variable.getType().getType();
    }
}
