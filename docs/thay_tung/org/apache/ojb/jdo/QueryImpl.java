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

import javax.jdo.Extent;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.ojb.jdo.jdoql.Expression;
import org.apache.ojb.jdo.jdoql.LocalVariable;
import org.apache.ojb.jdo.jdoql.QueryParsingHelper;
import org.apache.ojb.jdo.jdoql.QueryTreeResolver;

import java.util.*;

/**
 * Not Really Functional Yet:
 * <p>
 * Consider making this a front end for a State system where the
 * compiled query is a different State The big list of args should/could
 * be collected into something that knows how to compile and and
 * apply the arguments passed at execute time.
 * <p>
 * Consider also, if do above, clone returns compiled query. Compiled
 * probably needs to be able to uncompile itself if this the case,
 * so continuing to return uncompiled may be best
 *
 * @author <a href="mailto:mattbaird@yahoo.com">Matthew Baird</a>
 * @author <a href="mailto:brianm@apache.org">Brian McCallister</a>
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */

public class QueryImpl implements Query
{
    /** The persistence manager this query is bound to */
    private PersistenceManagerImpl _persistenceManager;
    /** The searched class */
    private Class _searchedClass;
    /** Candidate instances */
    private Collection _candidateInstances;
    /** The original filter string */
    private String _filterString;
    /** The parsed filter expression */
    private Expression _filterExpression;
    /** The original imports string */
    private String _importString;
    /** The imports */
    private Collection _imports;
    /** The original parameter string */
    private String _parameterString;
    /** The parameters */
    private Map _parameters;
    /** The original variables string */
    private String _variableString;
    /** The variables */
    private Map _variables;
    /** The original ordering string */
    private String _orderingString;
    /** The orderings */
    private Collection _orderings;
    /** Whether to ignore the cache while processing this query */
    private boolean _ignoreCache;
    /** Whether this query must be resolved and compiled first */
    private boolean _needsCompilation = true;

    /**
     * Creates a new query that uses the given persistence manager.
     * 
     * @param pm The persistence manager to use
     */
    public QueryImpl(PersistenceManagerImpl pm)
    {
        _persistenceManager = pm;
        _candidateInstances = null;
    }

    /**
     * Returns the persistence manager that this query uses.
     * 
     * @return The persistence manager
     */
    public PersistenceManager getPersistenceManager()
    {
        return _persistenceManager;
    }

    /**
     * Sets the class whose objects this query searches for.
     * 
     * @param searchedClass The class of the searched objects
     */
    public void setClass(Class searchedClass)
    {
        _searchedClass    = searchedClass;
        _needsCompilation = true;
    }

    /**
     * Returns the class of the searched objects.
     * 
     * @return The class of the searched objects
     */
    public Class getSearchedClass()
    {
        return _searchedClass;
    }
    
    /**
     * Simply ovewrites the setClass(..) value
     */
    public void setCandidates(Extent extent)
    {
        _searchedClass    = ((ExtentImpl)extent).ojbGetClass();
        _needsCompilation = true;
    }

    public void setCandidates(Collection candidates)
    {
        _candidateInstances = candidates;
        _needsCompilation   = true;
    }

    /**
     * Sets the filter of this query.
     * 
     * @param filter The filter expression
     */
    public void setFilter(String filter) throws JDOUserException
    {
        _filterString     = filter;
        _filterExpression = new QueryParsingHelper().parseFilter(filter);
        _needsCompilation = true;
    }

    /**
     * Returns the filter expression.
     * 
     * @return The filter expression
     */
    public Expression getFilterExpression()
    {
        return _filterExpression;
    }

    /**
     * Specifies the classes/packages imported by this query for use in the filter
     * and ordering expressions.
     * 
     * @param imports The import declarations
     */
    public void declareImports(String imports) throws JDOUserException
    {
        _importString     = imports;
        _imports          = new QueryParsingHelper().parseImports(imports);
        _needsCompilation = true;
    }

    /**
     * Returns the imports of this query.
     * 
     * @return The imports, a collection of {@link org.apache.ojb.jdo.jdoql.Import} objects
     */
    public Collection getImports()
    {
        return _imports;
    }

    /**
     * Sets the parameters of this query.
     * 
     * @param params The parameter declarations
     */
    public void declareParameters(String params) throws JDOUserException
    {
        _parameterString  = params;
        _parameters       = new QueryParsingHelper().parseParameters(params);
        _needsCompilation = true;
    }

    /**
     * Returns the parameters of this query.
     * 
     * @return The parameters, a map of {@link org.apache.ojb.jdo.jdoql.LocalVariable} objects
     *         indexed by their names
     */
    public Map getParameters()
    {
        return _parameters;
    }

    /**
     * Returns the parameter of the given name if it exists.
     * 
     * @param name The parameter name
     * @return The parameter
     */
    public LocalVariable getParameter(String name)
    {
        return (LocalVariable)_variables.get(name);
    }

    /**
     * Declares the variables used in the filter expression of this query.
     * 
     * @param variables The variable declarations
     */
    public void declareVariables(String variables) throws JDOUserException
    {
        _variableString   = variables;
        _variables        = new QueryParsingHelper().parseVariables(variables);
        _needsCompilation = true;
    }

    /**
     * Returns the variables of this query.
     * 
     * @return The variables, a map of {@link org.apache.ojb.jdo.jdoql.LocalVariable} objects
     *         indexed by their names
     */
    public Map getVariables()
    {
        return _variables;
    }

    /**
     * Returns the variable of the given name if it exists.
     * 
     * @param name The variable name
     * @return The variable
     */
    public LocalVariable getVariable(String name)
    {
        return (LocalVariable)_variables.get(name);
    }
    
    /**
     * Defines the ordering of this query.
     * 
     * @param orderings The ordering specifications
     */
    public void setOrdering(String orderings) throws JDOUserException
    {
        _orderingString   = orderings;
        _orderings        = new QueryParsingHelper().parseOrderings(orderings);
        _needsCompilation = true;
    }

    /**
     * Returns the orderings of this query.
     * 
     * @return The orderings, a collection of {@link org.apache.ojb.jdo.jdoql.Ordering} objects
     */
    public Collection getOrderings()
    {
        return _orderings;
    }

    /**
     * Specifies whether the query should ignore any objects in the cache.
     * 
     * @param shouldIgnoreCache Whether to ignore the cache
     */
    public void setIgnoreCache(boolean shouldIgnoreCache)
    {
        _ignoreCache = shouldIgnoreCache;
    }

    /**
     * Determines whether this query ignores objects in the cache.
     * 
     * @return <code>true</code> if this query ignores cached objects
     */
    public boolean getIgnoreCache()
    {
        return _ignoreCache;
    }

    /**
     * Compiles the query. In effect this resolves the various expressions and
     * declarations against each other, checks that they are valid, and enhances
     * the filter and ordering expressions with executable ojb queries.
     */
    public void compile()
    {
        if (_needsCompilation)
        {
            // first we resolve this query 
            new QueryTreeResolver().resolveAndCheck(this);
            // TODO: next the filter and ordering expressions are enhanced with
            //       actual database queries, e.g. like this:
            // new QueryCompiler().compile(this);
            //       which adds ojb queries to the filter expressions
            //       (including the ordering) 
            throw new UnsupportedOperationException("Not yet implemented");

            // _needsCompilation = false;
        }
    }

    /**
     * Performs this query.
     * 
     * @return The query result
     */
    public Object execute()
    {
        if (_needsCompilation)
        {
            compile();
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @todo implement
     */
    public Object execute(Object o)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public Object execute(Object o, Object o1)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public Object execute(Object o, Object o1, Object o2)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public Object executeWithMap(Map map)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    public Object executeWithArray(Object[] objects)
    {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    /**
     * @param o is a Collection returned from execute()
     */
    public void close(Object o)
    {
    }

    public void closeAll()
    {
    }

    /**
     * Creates an uncompiled deep clone of this query.
     * 
     * @return The clone
     */
    QueryImpl ojbClone()
    {
        QueryImpl query = new QueryImpl(_persistenceManager);

        query.setClass(_searchedClass);
        query.setCandidates(_candidateInstances);
        query.declareImports(_importString);
        query.declareParameters(_parameterString);
        query.declareVariables(_variableString);
        query.setFilter(_filterString);
        query.setOrdering(_orderingString);

        return query;
    }

}
