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
 * Base class for nodes of the query trees.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public abstract class QueryTreeNode implements Acceptor
{
    /** The line where the source of this node started. */
    private int _line   = 0;
    /** The column where the source of this node started. */
    private int _column = 0;

    /**
     * Sets the position of this node in the source.
     * 
     * @param line   The line
     * @param column The column
     */
    public void setPosition(int line, int column)
    {
        _line   = line;
        _column = column;
    }
    
    /**
     * Returns the column where the source of this node started.
     * 
     * @return The column
     */
    public int getColumn()
    {
        return _column;
    }

    /**
     * Returns the line where the source of this node started.
     * 
     * @return The line
     */
    public int getLine()
    {
        return _line;
    }
}
