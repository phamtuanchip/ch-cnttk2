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
 * A unary expression (arithmetic, logical, or a cast).
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class UnaryExpression extends Expression
{
    public static final int OPERATOR_MINUS              = 0;
    public static final int OPERATOR_PLUS               = 1;
    public static final int OPERATOR_BITWISE_COMPLEMENT = 2;
    public static final int OPERATOR_NOT                = 3;
    public static final int OPERATOR_CAST               = 4;

    /** The unary operator (one of the above constants) */
    private int        _operator;
    /** The inner expression */
    private Expression _inner;
    /** The type that the inner expression is cast to */
    private Type       _castType;
    /** The type of this expression */
    private Class  _type;

    /**
     * Creates a new unary expression object.
     * 
     * @param operator The unary operator
     * @param inner    The inner expression
     */
    public UnaryExpression(int operator, Expression inner)
    {
        _operator = operator;
        _inner    = inner;
    }

    /**
     * Creates a new cast expression object.
     * 
     * @param castType The cast-to type
     * @param inner    The inner expression
     */
    public UnaryExpression(Type castType, Expression inner)
    {
        _operator = OPERATOR_CAST;
        _castType = castType;
        _inner    = inner;
    }

    /**
     * Returns the operator.
     * 
     * @return The operator
     */
    public int getOperator()
    {
        return _operator;
    }

    /**
     * Returns the inner expression.
     * 
     * @return The inner expression 
     */
    public Expression getInnerExpression()
    {
        return _inner;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#replaceChild(org.apache.ojb.jdo.jdoql.Expression, org.apache.ojb.jdo.jdoql.Expression)
     */
    public void replaceChild(Expression oldChild, Expression newChild)
    {
        _inner.setParent(null);
        _inner = newChild;
        _inner.setParent(this);
    }

    /**
     * Returns the target type of this cast expression.
     * 
     * @return The type name of the cast type, or <code>null</code> if this is no
     *         cast expression
     */
    public Type getCastType()
    {
        return _castType;
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
        StringBuffer result = new StringBuffer();

        result.append("(");
        switch (_operator)
        {
            case OPERATOR_PLUS :
                result.append("+");
                break;
            case OPERATOR_MINUS :
                result.append("-");
                break;
            case OPERATOR_BITWISE_COMPLEMENT :
                result.append("~");
                break;
            case OPERATOR_NOT :
                result.append("!");
                break;
            case OPERATOR_CAST :
                result.append("(");
                result.append(_castType.toString());
                result.append(")");
                break;
        }
        result.append(_inner.toString());
        result.append(")");
        return result.toString();
    }

    /**
     * Sets the type of this unary expression.
     * 
     * @param type The type
     */
    public void setType(Class type)
    {
        _type = type;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#getType()
     */
    public Class getType()
    {
        return _type;
    }
}
