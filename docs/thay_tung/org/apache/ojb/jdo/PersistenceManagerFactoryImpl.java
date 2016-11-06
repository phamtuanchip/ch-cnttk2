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

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 */

public class PersistenceManagerFactoryImpl implements PersistenceManagerFactory
{
	/**
	 * flags
	 */
	private boolean m_optimistic = true;
	private boolean m_retainValues = true;
	private boolean m_restoreValues = true;
	private boolean m_nonTransactionalRead = true;
	private boolean m_nonTransactionalWrite = false;
	private boolean m_ignoreCache = true;
	private boolean m_multiThreaded = false;

	/**
	 * pool information
	 */
	private int m_minPool = 1;
	private int m_maxPool = 1;
	private int m_msWait = 0;

	/**
	 * connection factory information
	 */
	private Object m_connectionFactory;
	private String m_connectionFactoryName;

	private Object m_connectionFactory2;
	private String m_connectionFactory2Name;

	private String m_connectionDriverName;
	private String m_connectionURL;

	private String m_connectionPassword;
	private String m_connectionName;

	private String m_alias;

	/**
	 * support options
	 */
	private final String[] m_implementedOptions = new String[]{
		"javax.jdo.option.TransientTransactional",
		"javax.jdo.option.NontransactionalRead",
		"javax.jdo.option.NontransactionalWrite",
		"javax.jdo.option.RetainValues",
		"javax.jdo.option.RestoreValues",
		"javax.jdo.option.Optimistic",
		"javax.jdo.option.ApplicationIdentity",
		"javax.jdo.option.DatastoreIdentity",
		"javax.jdo.option.NonDatastoreIdentity",
		"javax.jdo.option.ArrayList",
		"javax.jdo.option.HashMap",
		"javax.jdo.option.Hashtable",
		"javax.jdo.option.LinkedList",
		"javax.jdo.option.TreeMap",
		"javax.jdo.option.TreeSet",
		"javax.jdo.option.Vector",
		"javax.jdo.option.Map",
		"javax.jdo.option.List",
		"javax.jdo.option.Array",
		"javax.jdo.option.NullCollection",
		"javax.jdo.query.JDOQL"
	};

	/**
	 * properties
	 */
	private static Properties PROPERTIES = new Properties();

	static
	{
		PROPERTIES.put("VendorName", "Apache ObjectRelationalBridge");
		PROPERTIES.put("VersionNumber", "1.0");
	}

	/**
	 * This method returns an instance of PersistenceManagerFactory based on the properties
	 * in the parameter. It is used by JDOHelper to construct an instance of PersistenceManagerFactory
	 * based on user-specified properties.
	 * The following are standard key values for the Properties:
	 * Java Data Objects1.0
	 * javax.jdo.PersistenceManagerFactoryClass --> Ignored, we only have one and that is PersistenceManagerFactoryImpl
	 * javax.jdo.option.Optimistic
	 * javax.jdo.option.RetainValues
	 * javax.jdo.option.RestoreValues
	 * javax.jdo.option.IgnoreCache
	 * javax.jdo.option.NontransactionalRead
	 * javax.jdo.option.NontransactionalWrite
	 * javax.jdo.option.Multithreaded
	 *
	 * javax.jdo.option.ConnectionUserName
	 * javax.jdo.option.ConnectionPassword
	 * javax.jdo.option.ConnectionURL
	 * javax.jdo.option.ConnectionFactoryName
	 * javax.jdo.option.ConnectionFactory2Name
	 * @see JDOConstants
	 * @param props
	 * @return the PersistenceManagerFactory instance with the appropriate Properties.
	 */
	public static PersistenceManagerFactory getPersistenceManagerFactory(Properties props)
	{
		PersistenceManagerFactoryImpl retval = new PersistenceManagerFactoryImpl();
		// parse and set the properties
		// boolean props
		retval.setOptimistic(Boolean.getBoolean(props.getProperty(JDOConstants.OPTIMISTIC, BooleanUtils.toStringTrueFalse(retval.getOptimistic()))));
		retval.setRetainValues(Boolean.getBoolean(props.getProperty(JDOConstants.RETAIN_VALUES, BooleanUtils.toStringTrueFalse(retval.getRetainValues()))));
		retval.setRestoreValues(Boolean.getBoolean(props.getProperty(JDOConstants.RESTORE_VALUES, BooleanUtils.toStringTrueFalse(retval.getRestoreValues()))));
		retval.setIgnoreCache(Boolean.getBoolean(props.getProperty(JDOConstants.IGNORE_CACHE, BooleanUtils.toStringTrueFalse(retval.getIgnoreCache()))));
		retval.setNontransactionalRead(Boolean.getBoolean(props.getProperty(JDOConstants.NON_TRANSACTIONAL_READ, BooleanUtils.toStringTrueFalse(retval.getNontransactionalRead()))));
		retval.setMultithreaded(Boolean.getBoolean(props.getProperty(JDOConstants.MULTI_THREADED, BooleanUtils.toStringTrueFalse(retval.getMultithreaded()))));
		retval.setOptimistic(Boolean.getBoolean(props.getProperty(JDOConstants.OPTIMISTIC, BooleanUtils.toStringTrueFalse(retval.getOptimistic()))));
		retval.setOptimistic(Boolean.getBoolean(props.getProperty(JDOConstants.OPTIMISTIC, BooleanUtils.toStringTrueFalse(retval.getOptimistic()))));

		// string props
		retval.setConnectionUserName(props.getProperty(JDOConstants.CONNECTION_USER_NAME, retval.getConnectionUserName()));
		retval.setConnectionPassword(props.getProperty(JDOConstants.CONNECTION_PASSWORD, null));
		retval.setConnectionURL(props.getProperty(JDOConstants.CONNECTION_URL, retval.getConnectionURL()));
		retval.setConnectionFactoryName(props.getProperty(JDOConstants.CONNECTION_FACTORY_NAME, retval.getConnectionFactoryName()));
		retval.setConnectionFactory2Name(props.getProperty(JDOConstants.CONNECTION_FACTORY_2_NAME, retval.getConnectionFactory2Name()));
		retval.setAlias(props.getProperty(JDOConstants.ALIAS, retval.getAlias()));
		return retval;
	}

	public PersistenceManager getPersistenceManager()
	{
		return getPersistenceManager(null, null, null);
	}

	public PersistenceManager getPersistenceManager(String userid, String password)
	{
		return getPersistenceManager(null, userid, password);
	}

	public PersistenceManager getPersistenceManager(String alias, String userid, String password)
	{
		return new PersistenceManagerImpl(this, alias, userid, password);
	}

	public String getAlias()
	{
		return m_alias;
	}

	public void setAlias(String alias)
	{
		m_alias = alias;
	}

	public void setConnectionUserName(String s)
	{
		m_connectionName = s;
	}

	public String getConnectionUserName()
	{
		return m_connectionName;
	}

	public void setConnectionPassword(String s)
	{
		m_connectionPassword = s;
	}

	public void setConnectionURL(String s)
	{
		m_connectionURL = s;
	}

	public String getConnectionURL()
	{
		return m_connectionURL;
	}

	public void setConnectionDriverName(String s)
	{
		m_connectionDriverName = s;
	}

	public String getConnectionDriverName()
	{
		return m_connectionDriverName;
	}

	public void setConnectionFactoryName(String s)
	{
		m_connectionFactoryName = s;
	}

	public String getConnectionFactoryName()
	{
		return m_connectionFactoryName;
	}

	public void setConnectionFactory(Object o)
	{
		m_connectionFactory = o;
	}

	public Object getConnectionFactory()
	{
		return m_connectionFactory;
	}

	public void setConnectionFactory2Name(String s)
	{
		m_connectionFactory2Name = s;
	}

	public String getConnectionFactory2Name()
	{
		return m_connectionFactory2Name;
	}

	public void setConnectionFactory2(Object o)
	{
		m_connectionFactory2 = o;
	}

	public Object getConnectionFactory2()
	{
		return m_connectionFactory2;
	}

	public void setMultithreaded(boolean b)
	{
		m_multiThreaded = b;
	}

	public boolean getMultithreaded()
	{
		return m_multiThreaded;
	}

	public void setOptimistic(boolean b)
	{
		m_optimistic = b;
	}

	public boolean getOptimistic()
	{
		return m_optimistic;
	}

	public void setRetainValues(boolean b)
	{
		m_retainValues = b;
	}

	public boolean getRetainValues()
	{
		return m_retainValues;
	}

	public void setRestoreValues(boolean b)
	{
		m_restoreValues = b;
	}

	public boolean getRestoreValues()
	{
		return m_restoreValues;
	}

	public void setNontransactionalRead(boolean b)
	{
		m_nonTransactionalRead = b;
	}

	public boolean getNontransactionalRead()
	{
		return m_nonTransactionalRead;
	}

	public void setNontransactionalWrite(boolean b)
	{
		m_nonTransactionalWrite = b;
	}

	public boolean getNontransactionalWrite()
	{
		return m_nonTransactionalWrite;
	}

	public void setIgnoreCache(boolean b)
	{
		m_ignoreCache = b;
	}

	public boolean getIgnoreCache()
	{
		return m_ignoreCache;
	}

	public int getMaxPool()
	{
		return m_maxPool;
	}

	public void setMaxPool(int i)
	{
		m_maxPool = i;
	}

	public int getMinPool()
	{
		return m_minPool;
	}

	public void setMinPool(int i)
	{
		m_minPool = i;
	}

	public int getMsWait()
	{
		return m_msWait;
	}

	public void setMsWait(int i)
	{
		m_msWait = i;
	}

	/**
	 * Return "static" properties of this PersistenceManagerFactory.
	 * Properties with keys VendorName and VersionNumber are required.  Other
	 * keys are optional.
	 * @return the non-operational properties of this PersistenceManagerFactory.
	 */
	public Properties getProperties()
	{
		return PROPERTIES;
	}

	/** The application can determine from the results of this
	 * method which optional features are supported by the
	 * JDO implementation.
	 * <P>Each supported JDO optional feature is represented by a
	 * String with one of the following values:
	 *
	 * <P>javax.jdo.option.TransientTransactional
	 * <P>javax.jdo.option.NontransactionalRead
	 * <P>javax.jdo.option.NontransactionalWrite
	 * <P>javax.jdo.option.RetainValues
	 * <P>javax.jdo.option.RestoreValues
	 * <P>javax.jdo.option.Optimistic
	 * <P>javax.jdo.option.ApplicationIdentity
	 * <P>javax.jdo.option.DatastoreIdentity
	 * <P>javax.jdo.option.NonDatastoreIdentity
	 * <P>javax.jdo.option.ArrayList
	 * <P>javax.jdo.option.HashMap
	 * <P>javax.jdo.option.Hashtable
	 * <P>javax.jdo.option.LinkedList
	 * <P>javax.jdo.option.TreeMap
	 * <P>javax.jdo.option.TreeSet
	 * <P>javax.jdo.option.Vector
	 * <P>javax.jdo.option.Map
	 * <P>javax.jdo.option.List
	 * <P>javax.jdo.option.Array
	 * <P>javax.jdo.option.NullCollection
	 *
	 *<P>The standard JDO query language is represented by a String:
	 *<P>javax.jdo.query.JDOQL
	 * @return the Set of String representing the supported Options
	 */
	public Collection supportedOptions()
	{
		return Collections.unmodifiableCollection(Arrays.asList(m_implementedOptions));
	}
	/* (non-Javadoc)
	 * @see javax.jdo.PersistenceManagerFactory#close()
	 */
	public void close()
	{
		// TODO Auto-generated method stub

	}

}
