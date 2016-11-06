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
 * Common base class for all JDOQL expressions.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public abstract class Expression extends QueryTreeNode
{
    /** The parent expression if any */
    private Expression _parent;

    /**
     * Determines whether this expression has a parent expression
     * (i.e. an expression where this expression is an inner or base expression).
     * 
     * @return <code>true</code> if this expression has a parent expression
     */
    public boolean hasParent()
    {
        return _parent != null;
    }

    /**
     * Returns the parent expression of this expression if it exists.
     * 
     * @return The parent expression or <code>null</code> if this expression
     *         has no parent
     */
    public Expression getParent()
    {
        return _parent;
    }

    /**
     * Sets the parent expression of this expression. Note that this method
     * does not remove this expression from the parent.
     * 
     * @param parent The new parent expression
     */
    public void setParent(Expression parent)
    {
        _parent = parent;
    }

    /**
     * Replaces the given old child expression with the new one. This also sets this
     * expression as the parent of the new child and removes it from the old child.
     * 
     * @param oldChild The old child to be replaced
     * @param newChild The new child
     */
    public void replaceChild(Expression oldChild, Expression newChild)
    {}

    /**
     * Returns the type of this expression. Note that in the case of primitives, 
     * the type only declares the general kind, not the actual data type. For instance,
     * a literal of type {@link Long} can be a <code>byte</code>, <code>short</code>,
     * <code>int</code>, <code>long</code>, or {@link java.math.BigInteger} value.
     * 
     * @return The type
     */
    public abstract Class getType();
}
