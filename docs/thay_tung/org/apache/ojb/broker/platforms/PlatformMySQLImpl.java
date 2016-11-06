package org.apache.ojb.broker.platforms;

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

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ojb.broker.query.LikeCriteria;

/**
 * @version 1.0
 * @author jakob bräuchi
 * @version $Id: PlatformMySQLImpl.java,v 1.15.2.1 2005/12/21 22:26:40 tomdz Exp $
 */
public class PlatformMySQLImpl extends PlatformDefaultImpl
{
    private static final String LAST_INSERT = "SELECT LAST_INSERT_ID() FROM ";
    private static final String LIMIT = " LIMIT 1";
    
    /*
	 * @see Platform#setObjectForStatement(PreparedStatement, int, Object, int)
	 */
    public void setObjectForStatement(PreparedStatement ps, int index, Object value, int sqlType) throws SQLException
    {
        switch (sqlType)
        {
            case Types.BIT :
                ps.setObject(index, value);
                break;

            case Types.BLOB :
            case Types.LONGVARBINARY :
            case Types.VARBINARY :
                if (value instanceof byte[])
                {
                    byte buf[] = (byte[]) value;
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
                    ps.setBinaryStream(index, inputStream, buf.length);

                    break;
                }

            case Types.CLOB :
                Reader reader = null;
                int length = 0;

                if (value instanceof String)
                {
                    reader = new StringReader((String) value);
                    length = (((String) value)).length();
                }
                else if (value instanceof char[])
                {
                    String string = new String((char[])value);
                    reader = new StringReader(string);
                    length = string.length();
                }
                else if (value instanceof byte[])
                {
                    byte buf[] = (byte[]) value;
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
                    reader = new InputStreamReader(inputStream);
                }

                ps.setCharacterStream(index, reader, length);
                break;

            default :
                super.setObjectForStatement(ps, index, value, sqlType);

        }
    }
    /**
	 * Get join syntax type for this RDBMS - one on of the constants from
	 * JoinSyntaxType interface
	 */
    public byte getJoinSyntaxType()
    {
        return SQL92_NOPAREN_JOIN_SYNTAX;
    }

    public String getLastInsertIdentityQuery(String tableName)
    {
        return LAST_INSERT + tableName + LIMIT;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ojb.broker.platforms.Platform#addPagingSql(java.lang.StringBuffer)
	 */
    public void addPagingSql(StringBuffer anSqlString)
    {
        anSqlString.append(" LIMIT ?,?");
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ojb.broker.platforms.Platform#supportsPaging()
	 */
    public boolean supportsPaging()
    {
        return true;
    }

    /**
     * @see org.apache.ojb.broker.platforms.Platform#concatenate(java.lang.String[])
     */
    public String concatenate(String[] theColumns)
    {
        if (theColumns.length == 1)
        {
            return theColumns[0];
        }
        
        StringBuffer buf = new StringBuffer();
        
        buf.append("concat(");
        for (int i = 0; i < theColumns.length; i++)
        {
            if (i > 0)
            {
                buf.append(",");
            }
            buf.append(theColumns[i]);
        }

        buf.append(")");
        return buf.toString();
    }    
    
    /**
     * @see org.apache.ojb.broker.platforms.Platform#getEscapeClause(org.apache.ojb.broker.query.LikeCriteria)
     */
    public String getEscapeClause(LikeCriteria aCriteria)
    {
        if (LikeCriteria.getEscapeCharacter() != LikeCriteria.DEFAULT_ESCPAPE_CHARACTER)  
        {
            // the default escape character is \, so there's no need for an escape clause
            return super.getEscapeClause(aCriteria);
        }
        else
        {
            return "";
        }
    }    
}
