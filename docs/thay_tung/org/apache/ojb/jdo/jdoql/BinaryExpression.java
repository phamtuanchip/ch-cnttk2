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
 * A binary expression (arithmetic or logical).
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class BinaryExpression extends Expression
{
    public static final int OPERATOR_MULTIPLY        = 0;
    public static final int OPERATOR_DIVIDE          = 1;
    public static final int OPERATOR_PLUS            = 2;
    public static final int OPERATOR_MINUS           = 3;
    public static final int OPERATOR_LOWER           = 4;
    public static final int OPERATOR_GREATER         = 5;
    public static final int OPERATOR_LOWER_OR_EQUAL  = 6;
    public static final int OPERATOR_GREATER_OR_EQUAL = 7;
    public static final int OPERATOR_EQUAL           = 8;
    public static final int OPERATOR_NOT_EQUAL       = 9;
    public static final int OPERATOR_BITWISE_AND     = 10;
    public static final int OPERATOR_BITWISE_XOR     = 11;
    public static final int OPERATOR_BITWISE_OR      = 12;
    public static final int OPERATOR_AND             = 13;
    public static final int OPERATOR_OR              = 14;

    /** The left side expression */
    private Expression _left;
    /** The binary operator (one of the above constants) */
    private int        _operator;
    /** The right side expression */
    private Expression _right;
    /** The type of this expression */
    private Class      _type;

    /**
     * Creates a new binary expression object.
     * 
     * @param left     The left side of the expression
     * @param operator The binary operator
     * @param right    The right side of the expression
     */
    public BinaryExpression(Expression left, int operator, Expression right)
    {
        _left     = left;
        _operator = operator;
        _right    = right;
    }

    /**
     * Returns the left side expression.
     * 
     * @return The left expression 
     */
    public Expression getLeftSide()
    {
        return _left;
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
     * Returns the right side expression.
     * 
     * @return The right expression 
     */
    public Expression getRightSide()
    {
        return _right;
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Expression#replaceChild(org.apache.ojb.jdo.jdoql.Expression, org.apache.ojb.jdo.jdoql.Expression)
     */
    public void replaceChild(Expression oldChild, Expression newChild)
    {
        if (oldChild == _left)
        {
            _left.setParent(null);
            _left = newChild;
            _left.setParent(this);
        }
        else if (oldChild == _right)
        {
            _right.setParent(null);
            _right = newChild;
            _right.setParent(this);
        }
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
        result.append(_left.toString());
        result.append(" ");
        switch (_operator)
        {
            case OPERATOR_MULTIPLY :
                result.append("* ");
                break;
            case OPERATOR_DIVIDE :
                result.append("/ ");
                break;
            case OPERATOR_PLUS :
                result.append("+ ");
                break;
            case OPERATOR_MINUS :
                result.append("- ");
                break;
            case OPERATOR_LOWER :
                result.append("< ");
                break;
            case OPERATOR_GREATER :
                result.append("> ");
                break;
            case OPERATOR_LOWER_OR_EQUAL :
                result.append("<= ");
                break;
            case OPERATOR_GREATER_OR_EQUAL :
                result.append(">= ");
                break;
            case OPERATOR_EQUAL :
                result.append("== ");
                break;
            case OPERATOR_NOT_EQUAL :
                result.append("!= ");
                break;
            case OPERATOR_BITWISE_AND :
                result.append("& ");
                break;
            case OPERATOR_BITWISE_XOR :
                result.append("^ ");
                break;
            case OPERATOR_BITWISE_OR :
                result.append("| ");
                break;
            case OPERATOR_AND :
                result.append("&& ");
                break;
            case OPERATOR_OR :
                result.append("|| ");
                break;
        }
        result.append(_right.toString());
        result.append(")");
        return result.toString();
    }

    /**
     * Sets the type of this binary expression.
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
