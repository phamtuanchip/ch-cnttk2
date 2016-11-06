package org.apache.ojb.jdo.spi;

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

import javax.jdo.spi.StateManager;
import javax.jdo.spi.PersistenceCapable;
import javax.jdo.PersistenceManager;

/**
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 */

public class StateManagerImpl implements StateManager
{
	private PersistenceManager m_persistenceManager;

	public StateManagerImpl(PersistenceManager persistenceManager)
	{
		m_persistenceManager = persistenceManager;
	}

	public byte replacingFlags(PersistenceCapable persistenceCapable)
	{
		return 0;
	}

	public StateManager replacingStateManager(PersistenceCapable persistenceCapable, StateManager stateManager)
	{
		return null;
	}

	public boolean isDirty(PersistenceCapable persistenceCapable)
	{
		return false;
	}

	public boolean isTransactional(PersistenceCapable persistenceCapable)
	{
		return false;
	}

	public boolean isPersistent(PersistenceCapable persistenceCapable)
	{
		return false;
	}

	public boolean isNew(PersistenceCapable persistenceCapable)
	{
		return false;
	}

	public boolean isDeleted(PersistenceCapable persistenceCapable)
	{
		return false;
	}

	public PersistenceManager getPersistenceManager(PersistenceCapable persistenceCapable)
	{
		return null;
	}

	public void makeDirty(PersistenceCapable persistenceCapable, String s)
	{
	}

	public Object getObjectId(PersistenceCapable persistenceCapable)
	{
		return null;
	}

	public Object getTransactionalObjectId(PersistenceCapable persistenceCapable)
	{
		return null;
	}

	public boolean isLoaded(PersistenceCapable persistenceCapable, int i)
	{
		return false;
	}

	public void preSerialize(PersistenceCapable persistenceCapable)
	{
	}

	public boolean getBooleanField(PersistenceCapable persistenceCapable, int i, boolean b)
	{
		return false;
	}

	public char getCharField(PersistenceCapable persistenceCapable, int i, char c)
	{
		return 0;
	}

	public byte getByteField(PersistenceCapable persistenceCapable, int i, byte b)
	{
		return 0;
	}

	public short getShortField(PersistenceCapable persistenceCapable, int i, short i1)
	{
		return 0;
	}

	public int getIntField(PersistenceCapable persistenceCapable, int i, int i1)
	{
		return 0;
	}

	public long getLongField(PersistenceCapable persistenceCapable, int i, long l)
	{
		return 0;
	}

	public float getFloatField(PersistenceCapable persistenceCapable, int i, float v)
	{
		return 0;
	}

	public double getDoubleField(PersistenceCapable persistenceCapable, int i, double v)
	{
		return 0;
	}

	public String getStringField(PersistenceCapable persistenceCapable, int i, String s)
	{
		return null;
	}

	public Object getObjectField(PersistenceCapable persistenceCapable, int i, Object o)
	{
		return null;
	}

	public void setBooleanField(PersistenceCapable persistenceCapable, int i, boolean b, boolean b1)
	{
	}

	public void setCharField(PersistenceCapable persistenceCapable, int i, char c, char c1)
	{
	}

	public void setByteField(PersistenceCapable persistenceCapable, int i, byte b, byte b1)
	{
	}

	public void setShortField(PersistenceCapable persistenceCapable, int i, short i1, short i2)
	{
	}

	public void setIntField(PersistenceCapable persistenceCapable, int i, int i1, int i2)
	{
	}

	public void setLongField(PersistenceCapable persistenceCapable, int i, long l, long l1)
	{
	}

	public void setFloatField(PersistenceCapable persistenceCapable, int i, float v, float v1)
	{
	}

	public void setDoubleField(PersistenceCapable persistenceCapable, int i, double v, double v1)
	{
	}

	public void setStringField(PersistenceCapable persistenceCapable, int i, String s, String s1)
	{
	}

	public void setObjectField(PersistenceCapable persistenceCapable, int i, Object o, Object o1)
	{
	}

	public void providedBooleanField(PersistenceCapable persistenceCapable, int i, boolean b)
	{
	}

	public void providedCharField(PersistenceCapable persistenceCapable, int i, char c)
	{
	}

	public void providedByteField(PersistenceCapable persistenceCapable, int i, byte b)
	{
	}

	public void providedShortField(PersistenceCapable persistenceCapable, int i, short i1)
	{
	}

	public void providedIntField(PersistenceCapable persistenceCapable, int i, int i1)
	{
	}

	public void providedLongField(PersistenceCapable persistenceCapable, int i, long l)
	{
	}

	public void providedFloatField(PersistenceCapable persistenceCapable, int i, float v)
	{
	}

	public void providedDoubleField(PersistenceCapable persistenceCapable, int i, double v)
	{
	}

	public void providedStringField(PersistenceCapable persistenceCapable, int i, String s)
	{
	}

	public void providedObjectField(PersistenceCapable persistenceCapable, int i, Object o)
	{
	}

	public boolean replacingBooleanField(PersistenceCapable persistenceCapable, int i)
	{
		return false;
	}

	public char replacingCharField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public byte replacingByteField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public short replacingShortField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public int replacingIntField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public long replacingLongField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public float replacingFloatField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public double replacingDoubleField(PersistenceCapable persistenceCapable, int i)
	{
		return 0;
	}

	public String replacingStringField(PersistenceCapable persistenceCapable, int i)
	{
		return null;
	}

	public Object replacingObjectField(PersistenceCapable persistenceCapable, int i)
	{
		return null;
	}
}
