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

import org.apache.ojb.broker.metadata.*;

/**
 * A field access expression. Note that this expression type can only be
 * present in a query tree after resolving it.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class FieldAccess extends NameExpression
{
    /** The descriptor for the accessed field, either a field or a reference descriptor */
    private AttributeDescriptorBase _descriptor;

    /**
     * Creates a new field access object.
     * 
     * @param base The base expression (can be <code>null</code>)
     * @param name The field's name
     */
    public FieldAccess(Expression base, String name)
    {
        super(base, name);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Acceptor#accept(org.apache.ojb.jdo.jdoql.Visitor)
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    /**
     * Sets the descriptor of the field accessed by this field access expression.
     * Must be either a reference or a field descriptor.
     *  
     * @param descriptor The descriptor of the field
     */
    public void setFieldDescriptor(AttributeDescriptorBase descriptor)
    {
        _descriptor = descriptor;
    }

    /**
     * Returns the descriptor of the field accessed by this field access expression.
     * 
     * @return The descriptor which is either a reference or field descriptor
     */
    public AttributeDescriptorBase getFieldDescriptor()
    {
        return _descriptor;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        if (_descriptor instanceof FieldDescriptor)
        {
            return ((FieldDescriptor)_descriptor).getPersistentField().getType();
        }
        else
        {
            // this also covers collections
            return ((ObjectReferenceDescriptor)_descriptor).getItemClass();
        }
    }
}
