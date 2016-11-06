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

import antlr.CommonAST;
import antlr.Token;
import antlr.collections.AST;

/**
 * An AST node that also contains position info (for error handling).
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class ASTWithPositionInfo extends CommonAST
{
    /** The line where the source of this node started. */
    private int _line   = 0;
    /** The column where the source of this node started. */
    private int _column = 0;

    /**
     * Creates a new node.
     */
    public ASTWithPositionInfo()
    {
        super();
    }

    /**
     * Creates a new node for the given token.
     * 
     * @param tok The token
     */
    public ASTWithPositionInfo(Token tok)
    {
        super(tok);
    }

    /**
     * Returns the column where the source of this node started.
     * 
     * @return The column
     * @see antlr.collections.AST#getColumn()
     */
    public int getColumn()
    {
        return _column;
    }

    /**
     * Returns the line where the source of this node started.
     * 
     * @return The line
     * @see antlr.collections.AST#getLine()
     */
    public int getLine()
    {
        return _line;
    }

    /* (non-Javadoc)
     * @see antlr.collections.AST#initialize(antlr.collections.AST)
     */
    public void initialize(AST ast)
    {
        super.initialize(ast);
        _line   = ast.getLine();
        _column = ast.getColumn();
    }

    /* (non-Javadoc)
     * @see antlr.collections.AST#initialize(antlr.Token)
     */
    public void initialize(Token tok)
    {
        super.initialize(tok);
        _line   = tok.getLine();
        _column = tok.getColumn();
    }

    /* (non-Javadoc)
     * @see antlr.collections.AST#addChild(antlr.collections.AST)
     */
    public void addChild(AST ast)
    {
        if ((ast != null) && (down == null) && (_line == 0) && (_column == 0))
        {
            _line   = ast.getLine();
            _column = ast.getColumn();
        }
        super.addChild(ast);
    }

    /* (non-Javadoc)
     * @see antlr.collections.AST#setFirstChild(antlr.collections.AST)
     */
    public void setFirstChild(AST ast)
    {
        if ((ast != null) && (_line == 0) && (_column == 0))
        {
            _line   = ast.getLine();
            _column = ast.getColumn();
        }
        super.setFirstChild(ast);
    }
}
