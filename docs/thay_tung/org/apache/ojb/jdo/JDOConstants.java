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

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 */
public interface JDOConstants
{
	public static final String PERSISTENCE_MANAGER_FACTORY_CLASS = "javax.jdo.PersistenceManagerFactoryClass";
	public static final String OPTIMISTIC = "javax.jdo.option.Optimistic";
	public static final String RETAIN_VALUES = "javax.jdo.option.RetainValues";
	public static final String RESTORE_VALUES = "javax.jdo.option.RestoreValues";
	public static final String IGNORE_CACHE = "javax.jdo.option.IgnoreCache";
	public static final String NON_TRANSACTIONAL_READ = "javax.jdo.option.NontransactionalRead";
	public static final String NON_TRANSACTIONAL_WRITE = "javax.jdo.option.NontransactionalWrite";
	public static final String MULTI_THREADED = "javax.jdo.option.Multithreaded";
	public static final String CONNECTION_USER_NAME = "javax.jdo.option.ConnectionUserName";
	public static final String CONNECTION_PASSWORD = "javax.jdo.option.ConnectionPassword";
	public static final String CONNECTION_URL = "javax.jdo.option.ConnectionURL";
	public static final String CONNECTION_FACTORY_NAME = "javax.jdo.option.ConnectionFactoryName";
	public static final String CONNECTION_FACTORY_2_NAME ="javax.jdo.option.ConnectionFactory2Name";
	public static final String ALIAS ="javax.jdo.option.OjbAlias";

}
