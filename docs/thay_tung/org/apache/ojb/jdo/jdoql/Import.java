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
 * Represents a direct or on-demand import within a JDOQL query.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class Import extends QueryTreeNode
{
    /** The import spec, either a fully qualified class name or a qualified package name */
    private String _spec;
    /** Whether this import is direct (class) or on-demand (package) */
    private boolean _isOnDemand;
    /** If an direct import, this is the resolved class */
    private Class   _importedClass;

    /**
     * Creates a new import object.
     * 
     * @param spec       The import spec
     * @param isOnDemand Whether the import is on-demand or direct
     */
    public Import(String spec, boolean isOnDemand)
    {
        _spec       = spec;
        _isOnDemand = isOnDemand;
    }

    /**
     * Returns whether this import is direct (class) or on-demand (package).
     * 
     * @return <code>true</code> if this import is on-demand
     */
    public boolean isOnDemand()
    {
        return _isOnDemand;
    }

    /**
     * Returns the import spec which is either a fully qualified class name or
     * a package.
     * 
     * @return The spec
     */
    public String getSpec()
    {
        return _spec;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Acceptor#accept(org.apache.ojb.jdo.jdoql.Visitor)
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return _isOnDemand ? _spec + ".*" : _spec;
    }
}
