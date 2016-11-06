package org.apache.ojb.broker.util.sequence;

/* Copyright 2002-2005 The Apache Software Foundation
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

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.metadata.FieldDescriptor;

/**
 * For internal use only!
 * This class is used to create transient primary key values for transient 
 * {@link org.apache.ojb.broker.Identity} objects.
 *
 * @version $Id: SequenceManagerTransientImpl.java,v 1.1.2.3 2005/12/21 22:28:41 tomdz Exp $
 */
public class SequenceManagerTransientImpl extends AbstractSequenceManager
{
    /*
     Use keyword 'volatile' to make decrement of a long value an
     atomic operation
     */
    private static volatile long tempKey = 1000;

    public SequenceManagerTransientImpl(PersistenceBroker broker)
    {
        super(broker);
    }

    protected long getUniqueLong(FieldDescriptor field) throws SequenceManagerException
    {
        /*
        arminw:
        We need unique 'dummy keys' for new objects before storing.
        Variable 'tempKey' is declared volatile, thus increment should be atomic
        */
        return ++tempKey;
    }
}
