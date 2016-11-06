package org.apache.ojb.jdo;

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

import org.apache.ojb.otm.OTMKit;
import org.apache.ojb.otm.OTMConnection;

import javax.jdo.Transaction;
import javax.jdo.PersistenceManager;
import javax.jdo.JDOUserException;
import javax.jdo.JDOUnsupportedOptionException;
import javax.transaction.Synchronization;

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 * @author <a href="mailto:brianm@apache.org">Brian McCallister</a>
 */
public class TransactionImpl implements Transaction
{
	private PersistenceManager m_pm;
    private OTMKit m_kit;
    private OTMConnection m_conn;

	public TransactionImpl(PersistenceManager pm, OTMKit kit, OTMConnection conn)
	{
		m_pm = pm;
        m_conn = conn;
        m_kit = kit;
	}

	public void begin()
	{
        if (m_kit.getTransaction(m_conn).isInProgress())
        {
            throw new JDOUserException("Transaction already in progress");
        }
        m_kit.getTransaction(m_conn).begin();
	}

	public void commit()
	{
        if (!m_kit.getTransaction(m_conn).isInProgress())
        {
            throw new JDOUserException("Transaction not in progress");
        }
        m_kit.getTransaction(m_conn).commit();
	}

	public void rollback()
	{
		if (!m_kit.getTransaction(m_conn).isInProgress())
        {
            throw new JDOUserException("Transaction not in progress");
        }
        m_kit.getTransaction(m_conn).rollback();
	}

	public boolean isActive()
	{
		return m_kit.getTransaction(m_conn).isInProgress();
	}

	public void setNontransactionalRead(boolean b)
	{
        throw new javax.jdo.JDOUnsupportedOptionException("Unsupported");
	}

	public boolean getNontransactionalRead()
	{
		return false;
	}

	public void setNontransactionalWrite(boolean b)
	{
        throw new javax.jdo.JDOUnsupportedOptionException("Unsupported");
	}

	public boolean getNontransactionalWrite()
	{
		return false;
	}

    /**
     * @todo figure out what to do if someone doesn't want this!
     */
	public void setRetainValues(boolean b)
	{
        // umh, this is always true.
	}

	public boolean getRetainValues()
	{
		return true;
	}

    /**
     * @todo implement
     */
	public void setRestoreValues(boolean b)
	{
        throw new JDOUnsupportedOptionException("Not Yet Implemented");
	}

    /**
     * @todo implement
     */
	public boolean getRestoreValues()
	{
		return false;
	}

    /**
     * @todo implement
     */
	public void setOptimistic(boolean b)
	{
        throw new JDOUnsupportedOptionException("Not yet implemented");
	}

    /**
     * @todo implement
     */
	public boolean getOptimistic()
	{
		return false;
	}

    /**
     * @todo figure out how to implement
     * @param synchronization
     */
	public void setSynchronization(Synchronization synchronization)
	{
        throw new JDOUnsupportedOptionException("Not Yet Implemented");
	}

    /**
     * @todo figure out how to implement
     */
	public Synchronization getSynchronization()
	{
		throw new JDOUnsupportedOptionException("Not Yet Implemented");
	}

	public PersistenceManager getPersistenceManager()
	{
		return m_pm;
	}
}
