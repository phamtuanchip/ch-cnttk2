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

import java.io.StringReader;
import java.util.*;

import javax.jdo.JDOUserException;

import antlr.RecognitionException;
import antlr.TokenStreamException;

/**
 * Helper class for building the actual query structure from a JDOQL query definition.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class QueryParsingHelper
{
    /**
     * Parses the given parameter declaration string.
     *
     * @return The parameters indexed by their names
     */
    public Map parseParameters(String declaration) throws JDOUserException
    {
        JDOQLLexer      lexer      = new JDOQLLexer(new StringReader(declaration));
        JDOQLParser     parser     = new JDOQLParser(lexer);
        JDOQLTreeParser treeParser = new JDOQLTreeParser();
        
        parser.setASTNodeClass(ASTWithPositionInfo.class.getName());
        try
        {
            parser.declareParameters();
            return treeParser.declareParameters(parser.getAST());
        }
        catch (RecognitionException ex)
        {
            throw new JDOUserException("Error in parameter declaration at line "+ex.getLine()+", column "+ex.getColumn());
        }
        catch (TokenStreamException ex)
        {
            throw new JDOUserException("Could not parse the parameter declaration");
        }
    }

    /**
     * Parses the given declaration string.
     *
     * @return The variables indexed by their names
     */
    public Map parseVariables(String declaration) throws JDOUserException
    {
        JDOQLLexer      lexer      = new JDOQLLexer(new StringReader(declaration));
        JDOQLParser     parser     = new JDOQLParser(lexer);
        JDOQLTreeParser treeParser = new JDOQLTreeParser();
        
        parser.setASTNodeClass(ASTWithPositionInfo.class.getName());
        try
        {
            parser.declareVariables();
            return treeParser.declareVariables(parser.getAST());
        }
        catch (RecognitionException ex)
        {
            throw new JDOUserException("Error in variable declaration at line "+ex.getLine()+", column "+ex.getColumn());
        }
        catch (TokenStreamException ex)
        {
            throw new JDOUserException("Could not parse the variable declaration");
        }
    }

    /**
     * Parses the given imports declaration string.
     *
     * @return The imports list
     */
    public List parseImports(String declaration) throws JDOUserException
    {
        JDOQLLexer      lexer      = new JDOQLLexer(new StringReader(declaration));
        JDOQLParser     parser     = new JDOQLParser(lexer);
        JDOQLTreeParser treeParser = new JDOQLTreeParser();

        parser.setASTNodeClass(ASTWithPositionInfo.class.getName());
        try
        {
            parser.declareImports();
            return treeParser.declareImports(parser.getAST());
        }
        catch (RecognitionException ex)
        {
            throw new JDOUserException("Error in import specification at line "+ex.getLine()+", column "+ex.getColumn());
        }
        catch (TokenStreamException ex)
        {
            throw new JDOUserException("Could not parse the import specification");
        }
    }

    /**
     * Parses the given orderings declaration string.
     *
     * @return The orderings list
     */
    public List parseOrderings(String declaration) throws JDOUserException
    {
        JDOQLLexer      lexer      = new JDOQLLexer(new StringReader(declaration));
        JDOQLParser     parser     = new JDOQLParser(lexer);
        JDOQLTreeParser treeParser = new JDOQLTreeParser();
        
        parser.setASTNodeClass(ASTWithPositionInfo.class.getName());
        try
        {
            parser.setOrdering();
            return treeParser.setOrdering(parser.getAST());
        }
        catch (RecognitionException ex)
        {
            throw new JDOUserException("Error in ordering specification at line "+ex.getLine()+", column "+ex.getColumn());
        }
        catch (TokenStreamException ex)
        {
            throw new JDOUserException("Could not parse the ordering specification");
        }
    }

    /**
     * Parses the given filter expression string.
     *
     * @return The filter expression
     */
    public Expression parseFilter(String expression) throws JDOUserException
    {
        JDOQLLexer      lexer      = new JDOQLLexer(new StringReader(expression));
        JDOQLParser     parser     = new JDOQLParser(lexer);
        JDOQLTreeParser treeParser = new JDOQLTreeParser();
        
        parser.setASTNodeClass(ASTWithPositionInfo.class.getName());
        try
        {
            parser.expression();
            return treeParser.expression(parser.getAST());
        }
        catch (RecognitionException ex)
        {
            throw new JDOUserException("Error in filter expression at line "+ex.getLine()+", column "+ex.getColumn());
        }
        catch (TokenStreamException ex)
        {
            throw new JDOUserException("Could not parse the filter expression");
        }
    }

    public static void main(String[] args) throws JDOUserException
    {
        if (args.length < 2)
        {
            System.out.println("Usage:\n\njava "+QueryParsingHelper.class.getName()+" [type] [text]\n");
            System.out.println("where type specifies what kind of text to parse");
            System.out.println("(imports, parameters, variables, orderings, filter)");
            System.out.println("and text gives the actual text to parse");
            return;
        }

        QueryParsingHelper helper = new QueryParsingHelper();
        
        if ("imports".equals(args[0]))
        {
            List imports = helper.parseImports(args[1]);

            for (int idx = 0; idx < imports.size(); idx++)
            {
                System.out.println("("+idx+"): "+imports.get(idx).toString());
            }
        }
        else if ("parameters".equals(args[0]))
        {
            Map params = helper.parseParameters(args[1]);

            for (Iterator it = params.values().iterator(); it.hasNext();)
            {
                System.out.println(it.toString());
            }
        }
        else if ("variables".equals(args[0]))
        {
            Map vars = helper.parseVariables(args[1]);

            for (Iterator it = vars.values().iterator(); it.hasNext();)
            {
                System.out.println(it.toString());
            }
        }
        else if ("orderings".equals(args[0]))
        {
            List orderings = helper.parseOrderings(args[1]);

            for (int idx = 0; idx < orderings.size(); idx++)
            {
                System.out.println("("+idx+"): "+orderings.get(idx).toString());
            }
        }
        else if ("filter".equals(args[0]))
        {
            Expression filterExpr = helper.parseFilter(args[1]);

            System.out.println(filterExpr.toString());
        }
        else
        {
            System.out.println("Unknown kind of text; allowed are: imports, parameters, variables, orderings, filter");
        }
    }
}
