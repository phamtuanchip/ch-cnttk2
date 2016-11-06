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

import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.InstanceCallbacks;
import javax.jdo.JDOFatalUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.ojb.broker.Identity;
import org.apache.ojb.broker.PBKey;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.core.proxy.CollectionProxyDefaultImpl;
import org.apache.ojb.broker.core.proxy.ProxyHelper;
import org.apache.ojb.otm.EditingContext;
import org.apache.ojb.otm.OTMConnection;
import org.apache.ojb.otm.OTMKit;
import org.apache.ojb.otm.kit.SimpleKit;
import org.apache.ojb.otm.lock.LockType;
import org.apache.ojb.otm.lock.LockingException;
import org.apache.ojb.otm.states.State;

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 * @author <a href="mailto:brianm@apache.org">Brian McCallister</a>
 */
public class PersistenceManagerImpl implements PersistenceManager
{
    private OTMConnection m_conn;
    private OTMKit m_kit;
    private boolean m_multiThreaded = false;
    private boolean m_ignoreCache = false;
    private PersistenceManagerFactory m_factory;
    private String m_userID;
    private String m_password;
    private String m_alias;
    private Object m_usersObject;
    private TransactionImpl m_tx;

    public PersistenceManagerImpl(PersistenceManagerFactory factory, String alias, String userid, String password)
    {
        m_factory = factory;
        m_userID = userid;
        m_password = password;
        m_alias = alias;
        m_kit = SimpleKit.getInstance();
        /**
         * if the alias is null, use the default.
         */
        if (null == m_alias)
        {
            m_conn = m_kit.acquireConnection(PersistenceBrokerFactory.getDefaultKey());
        }
        else
        {
            PBKey key = new PBKey(m_alias, m_userID, m_password);
            m_conn = m_kit.acquireConnection(key);
        }
        m_tx = new TransactionImpl(this, m_kit, m_conn);
    }

    OTMConnection getConnection()
    {
        return this.m_conn;
    }

    OTMKit getKit()
    {
        return this.m_kit;
    }

    public boolean isClosed()
    {
        return m_conn.isClosed();
    }

    /**
     * Close database resources and
     * Rollback transaction if there is one in progress
     */
    public void close()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("close()"));
        }
        if (m_tx.isActive()) m_tx.rollback();
        m_conn.close();
    }

    public Transaction currentTransaction()
    {
        return m_tx;
    }

    /**
     * evict all persistent-clean instances from the editing context cache
     *
     * @param o
     */
    public void evict(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("evict(Object)"));
        }
        if (null != o)
        {
            try
            {
                Identity oid = m_conn.getIdentity(o);
                State state = m_conn.getEditingContext().lookupState(oid);
                /**
                 * if the object is PersistentClean or non transactional, evict it.
                 */
                if (State.PERSISTENT_CLEAN == state)
                {
                    /**
                     * spec says call the jdoPreClear if it's InstanceCallbacks aware
                     */
                    if (o instanceof InstanceCallbacks)
                    {
                        ((InstanceCallbacks) o).jdoPreClear();
                    }
                    m_conn.invalidate(m_conn.getIdentity(o));
                    /**
                     * set all fields to their default values
                     */

                    /**
                     * set state to hollow
                     */
                }
                if (null == state)
                {
                    /**
                     * not in the editing context, evict it from the global cache
                     */
                    m_conn.serviceObjectCache().remove(m_conn.getIdentity(o));
                }
            }
            catch (LockingException e)
            {
                /**
                 * shouldn't happen, we're only dealing with persistentClean and non transactional (aka non-locked)
                 * objects.
                 */
            }
        }
    }

    public void evictAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("evictAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("evictAll(Object[]) was passed a null Array.");
        }
        int length = objects.length;
        for (int i = 0; i < length; i++)
        {
            evict(objects[i]);
        }
    }

    public void evictAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("evictAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("evictAll(Collection) was passed a null Collection.");
        }
        Iterator it = collection.iterator();
        while (it.hasNext())
        {
            evict(it.next());
        }
    }

    public void evictAll()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("evictAll()"));
        }
        EditingContext ctx = m_conn.getEditingContext();
        if (ctx != null)
        {
            for (Iterator i = ctx.getAllObjectsInContext().iterator(); i.hasNext();)
            {
                evict(i.next());
            }
        }
        /**
         * clear the rest of the global cache
         */
        m_conn.serviceObjectCache().clear();
    }

    public void refresh(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("refresh(Object)"));
        }
        m_conn.refresh(o);
        if (o instanceof InstanceCallbacks)
        {
            ((InstanceCallbacks) o).jdoPostLoad();
        }
    }

    public void refreshAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("refreshAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("refreshAll(Object[]) was passed a null Array.");
        }
        int length = objects.length;
        for (int i = 0; i < length; i++)
        {
            refresh(objects[i]);
        }
    }

    public void refreshAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("refreshAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("refreshAll(Collection) was passed a null Collection.");
        }
        Iterator it = collection.iterator();
        while (it.hasNext())
        {
            refresh(it.next());
        }
    }

    public void refreshAll()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("refreshAll()"));
        }
        if (currentTransaction().isActive())
        {
            Collection collection = m_conn.getEditingContext().getAllObjectsInContext();
            Iterator it = collection.iterator();
            while (it.hasNext())
            {
                refresh(it.next());
            }
        }
    }

    public Query newQuery()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery()"));
        }

        return new QueryImpl(this);
    }

    public Query newQuery(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Object)"));
        }
        try
        {
            return ((QueryImpl) o).ojbClone();
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("newQuery(Object) must be passed a Query instance");
        }
    }

    public Query newQuery(String s, Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(String, Object)"));
        }

        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public Query newQuery(Class aClass)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Class)"));
        }
        Query query = new QueryImpl(this);
        query.setClass(aClass);
        return query;
    }

    public Query newQuery(Extent extent)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Extent)"));
        }

        Query query = new QueryImpl(this);
        query.setCandidates(extent);
        return query;
    }

    public Query newQuery(Class aClass, Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Class, Collection)"));
        }
        Query query = new QueryImpl(this);
        query.setCandidates(collection);
        query.setClass(aClass);
        return query;
    }

    public Query newQuery(Class aClass, String s)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Class, String)"));
        }
        Query query = new QueryImpl(this);
        query.setClass(aClass);
        query.setFilter(s);
        return query;
    }

    public Query newQuery(Class aClass, Collection collection, String s)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Class, Collection, String)"));
        }
        Query query = new QueryImpl(this);
        query.setCandidates(collection);
        query.setClass(aClass);
        query.setFilter(s);
        return query;
    }

    public Query newQuery(Extent extent, String s)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newQuery(Extent, String)"));
        }
        Query query = new QueryImpl(this);
        query.setCandidates(extent);
        query.setFilter(s);
        return query;
    }

    /**
     * @param aClass top level class
     * @param include_extent      include subclasses, presently ignored
     * @return
     * @todo figure out how to implement, may have to query all and filter objects
     */
    public Extent getExtent(Class aClass, boolean include_extent)
    {
        if (!include_extent) throw new UnsupportedOperationException("Not yet implemented!");
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getExtent(Class, boolean)"));
        }
        return new ExtentImpl(aClass, m_conn, this, include_extent);
    }

    public Object getObjectById(Object o, boolean validate)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getObjectById(Object, boolean)"));
        }
        Object retval = null;
        try
        {
            retval = m_conn.getObjectByIdentity((Identity) o);
        }
        catch (LockingException e)
        {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Object passed as id is not an id: " + o);
        }
        return retval;
    }

    /**
     * @todo verify that Identity meets the requirements of the JDO spec
     */
    public Object getObjectId(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getObjectId(Object)"));
        }
        return m_conn.getIdentity(o);
    }

    public Object getTransactionalObjectId(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getTransactionalObjectId(Object)"));
        }
        return m_conn.getIdentity(o);
    }

    public Object newObjectIdInstance(Class aClass, String s)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("newObjectIdInstance(Class, String)"));
        }
        return null;
    }

    public void makePersistent(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makePersistent(Object)"));
        }
        try
        {
            m_conn.makePersistent(o);
        }
        catch (LockingException e)
        {
            // throw runtime exception
        }
    }

    public void makePersistentAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makePersistentAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("makePersistentAll(Object[]) was passed a null Array.");
        }
        int length = objects.length;
        for (int i = 0; i < length; i++)
        {
            makePersistent(objects[i]);
        }
    }

    public void makePersistentAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makePersistentAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("makePersistentAll(Collection) was passed a null Collection.");
        }
        Iterator it = collection.iterator();
        while (it.hasNext())
        {
            makePersistent(it.next());
        }
    }

    public void deletePersistent(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("deletePersistent(Object)"));
        }
        try
        {
            m_conn.deletePersistent(o);
        }
        catch (LockingException e)
        {
            handleLockingException(e);
        }
    }

    public void deletePersistentAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("deletePersistentAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("deletePersistentAll(Object[]) was passed a null Array.");
        }
        int length = objects.length;
        for (int i = 0; i < length; i++)
        {
            deletePersistent(objects[i]);
        }
    }

    public void deletePersistentAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("deletePersistentAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("deletePersistentAll(Collection) was passed a null Collection.");
        }
        Iterator it = collection.iterator();
        while (it.hasNext())
        {
            deletePersistent(it.next());
        }
    }

    /**
     * Right now this makes the object non-transactional.
     *
     * @todo figure out what this is supposed to really do
     */
    public void makeTransient(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransient(Object)"));
        }
        m_conn.getEditingContext().remove(m_conn.getIdentity(o));
        m_conn.serviceObjectCache().remove(m_conn.getIdentity(o));
    }

    public void makeTransientAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransientAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("makeTransientAll(Object[]) was passed a null Array.");
        }
        for (int i = 0; i < objects.length; i++)
        {
            this.makeTransient(objects[i]);
        }
    }

    public void makeTransientAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransientAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("makeTransientAll(Collection) was passed a null Collection.");
        }
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            this.makeTransient(iterator.next());
        }
    }

    public void makeTransactional(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransactional(Object)"));
        }
        try
        {
            m_conn.getEditingContext().insert(m_conn.getIdentity(o), o, LockType.READ_LOCK);
        }
        catch (LockingException e)
        {
            handleLockingException(e);
        }
    }

    public void makeTransactionalAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransactionalAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("makeTransactionalAll(Object[]) was passed a null Array.");
        }
        for (int i = 0; i < objects.length; i++)
        {
            this.makeTransactional(objects[i]);
        }
    }

    public void makeTransactionalAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeTransactionalAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("makeTransactionalAll(Collection) was passed a null Collection.");
        }
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            this.makeTransactional(iterator.next());
        }
    }

    /**
     * @todo What is the difference between this and makeTransient?
     */
    public void makeNontransactional(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeNontransactional(Object)"));
        }
        this.makeTransient(o);
    }

    public void makeNontransactionalAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeNontransactionalAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("makeNontransactionalAll(Object[]) was passed a null Array.");
        }
        for (int i = 0; i < objects.length; i++)
        {
            this.makeNontransactional(objects[i]);
        }
    }

    public void makeNontransactionalAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("makeNontransactionalAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("makeNontransactionalAll(Collection) was passed a null Collection.");
        }
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            this.makeNontransactional(iterator.next());
        }
    }

    /**
     * Force materialization of real object
     * @param o
     */
    public void retrieve(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("retrieve(Object)"));
        }
        ProxyHelper.getRealObject(o);
    }

    /**
     * Force materialization of all items in collection
     * @param collection
     */
    public void retrieveAll(Collection collection)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("retrieveAll(Collection)"));
        }
        if (null == collection)
        {
            throw new NullPointerException("retrieveAll(Collection) was passed a null Collection.");
        }

        /* @todo I consider this a hack as CPDI is configurable now! */
        if (collection instanceof CollectionProxyDefaultImpl)
        {
            CollectionProxyDefaultImpl cp = (CollectionProxyDefaultImpl) collection;
            cp.getData();
        }
    }

    public void retrieveAll(Object[] objects)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("retrieveAll(Object[])"));
        }
        if (null == objects)
        {
            throw new NullPointerException("retrieveAll(Object[]) was passed a null Array.");
        }
        // We don't proxy arrays
    }

    public void setUserObject(Object o)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("setUserObject(Object)"));
        }
        m_usersObject = o;
    }

    public Object getUserObject()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getUserObject()"));
        }
        return m_usersObject;
    }

    public PersistenceManagerFactory getPersistenceManagerFactory()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getPersistenceManagerFactory()"));
        }
        return this.m_factory;
    }

    /**
     * @todo This will need to change when we have a JDO compliant Identity implementation
     */
    public Class getObjectIdClass(Class aClass)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getObjectIdClass(Class)"));
        }
        return Identity.class;
    }

    public void setMultithreaded(boolean b)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("setMultithreaded(boolean)"));
        }
        m_multiThreaded = b;
    }

    public boolean getMultithreaded()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getMultithreaded()"));
        }
        return m_multiThreaded;
    }

    public void setIgnoreCache(boolean b)
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("setIgnoreCache(boolean)"));
        }
        m_ignoreCache = b;
    }

    public boolean getIgnoreCache()
    {
        if (isClosed())
        {
            throw new JDOFatalUserException(generateIsClosedErrorMessage("getIgnoreCache()"));
        }
        return m_ignoreCache;
    }

    /**
     * TODO i18n these messages
     *
     * @param methodSignature
     * @return
     */
    private static final String generateIsClosedErrorMessage(String methodSignature)
    {
        return "PersistenceManager already closed, cannot call '" + methodSignature + "'. Obtain a new PersistenceBroker and retry.";
    }

    private void handleLockingException(LockingException e)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    /**
     * TODO i18n these messages
     *
     * @param methodSignature
     * @param type
     * @return
     */
    private static final String generateNullParameterErrorMessage(String methodSignature, String type)
    {
        return methodSignature + " was passed a null " + type + ".";
    }

    /**
     * @todo what does this do?
     * @see javax.jdo.PersistenceManager#retrieveAll(java.util.Collection, boolean)
     */
    public void retrieveAll(Collection arg0, boolean arg1)
    {
        // TODO Auto-generated method stub

    }

    /**
     * @todo what does this do?
     * @see javax.jdo.PersistenceManager#retrieveAll(java.lang.Object[], boolean)
     */
    public void retrieveAll(Object[] arg0, boolean arg1)
    {
        // TODO Auto-generated method stub
    }
}
