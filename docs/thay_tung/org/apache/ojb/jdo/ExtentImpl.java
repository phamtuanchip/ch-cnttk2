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

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import org.apache.ojb.broker.accesslayer.OJBIterator;
import org.apache.ojb.broker.accesslayer.RsIterator;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.otm.OTMConnection;

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 * @author <a href="mailto:brianm@apache.org">Brian McCallister</a>
 * @version $Id: ExtentImpl.java,v 1.9.2.1 2005/12/21 22:28:40 tomdz Exp $
 */
public class ExtentImpl implements Extent
{
	private Class m_clazz;
	private OTMConnection m_conn;
	private PersistenceManager m_pm;
    private HashSet m_iterators = new HashSet();
    private Criteria m_criteria;

    /**
	 * Constructor for ExtentImpl.
     * @param subclasses is ignored
	 */
	public ExtentImpl(Class pClazz, OTMConnection conn, PersistenceManager pm, boolean subclasses)
	{
		m_clazz = pClazz;
		m_conn = conn;
		m_pm = pm;
        m_criteria = new Criteria();
	}

    Class ojbGetClass()
    {
        return m_clazz;
    }

    /**
     * @todo is this supposed to operate outside of a user specified tx? Right now it obtains one if needed
     */
	public Iterator iterator()
	{
        QueryByCriteria q = QueryFactory.newQuery(m_clazz, m_criteria);
        ExtentIterator itty = new ExtentIterator((OJBIterator) m_conn.getIteratorByQuery(q));
        m_iterators.add(itty);
		return itty;
	}

	public boolean hasSubclasses()
	{
		ClassDescriptor cld = m_conn.getDescriptorFor(m_clazz);
		return cld.isExtent();
	}

	public Class getCandidateClass()
	{
		return m_clazz;
	}

	public PersistenceManager getPersistenceManager()
	{
		return m_pm;
	}

	public void closeAll()
	{
		for (Iterator iterator = m_iterators.iterator(); iterator.hasNext();)
        {
            ExtentIterator itty = (ExtentIterator) iterator.next();
            itty.close();
            iterator.remove();
        }
	}

	public void close(Iterator iterator)
	{
		if (iterator instanceof ExtentIterator && m_iterators.contains(iterator))
        {
            m_iterators.remove(iterator);
            ((ExtentIterator)iterator).close();
        }
	}

    private class ExtentIterator implements Iterator
    {
        private OJBIterator itty;
        private boolean closed = false;

        ExtentIterator(OJBIterator itty)
        {
            this.itty = itty;
        }

        public boolean hasNext()
        {
            if (closed) return false;
            return itty.hasNext();
        }

        public Object next()
        {
            if (closed) throw new NoSuchElementException("Calling next() on closed JDO iterator");
            try
            {
            return itty.next();
            }
            catch (RsIterator.ResourceClosedException e)
            {
                throw new NoSuchElementException("Calling next() on closed JDO iterator");
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Operation Not Allowd");
        }

        private void close()
        {
            itty.releaseDbResources();
            closed = true;
        }
    }
}
