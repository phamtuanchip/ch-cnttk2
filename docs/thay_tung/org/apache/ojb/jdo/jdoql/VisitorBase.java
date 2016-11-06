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

import java.util.Iterator;
import java.util.List;

/**
 * Convenience base class for visitors that provides basic traversal capabilities.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public class VisitorBase implements Visitor
{
    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visitFilter(org.apache.ojb.jdo.jdoql.Expression)
     */
    public void visitFilter(Expression filterExpr)
    {
        filterExpr.accept(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visitOrderings(java.util.List)
     */
    public void visitOrderings(List orderings)
    {
        for (Iterator it = orderings.iterator(); it.hasNext();)
        {
            ((Acceptor)it.next()).accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visitParameters(java.util.List)
     */
    public void visitParameters(List params)
    {
        for (Iterator it = params.iterator(); it.hasNext();)
        {
            ((Acceptor)it.next()).accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visitVariables(java.util.List)
     */
    public void visitVariables(List variables)
    {
        for (Iterator it = variables.iterator(); it.hasNext();)
        {
            ((Acceptor)it.next()).accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.BinaryExpression)
     */
    public void visit(BinaryExpression binExpr)
    {
        binExpr.getLeftSide().accept(this);
        binExpr.getRightSide().accept(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.FieldAccess)
     */
    public void visit(FieldAccess fieldAccess)
    {
        if (fieldAccess.hasBaseExpression())
        {
            fieldAccess.getBaseExpression().accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.Import)
     */
    public void visit(Import importSpec)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.Literal)
     */
    public void visit(Literal literal)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.LocalVariable)
     */
    public void visit(LocalVariable localVar)
    {
        localVar.getType().accept(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.LocalVariableAccess)
     */
    public void visit(LocalVariableAccess localVarAccess)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.MethodInvocation)
     */
    public void visit(MethodInvocation methodInvoc)
    {
        if (methodInvoc.hasBaseExpression())
        {
            methodInvoc.getBaseExpression().accept(this);
        }
        for (Iterator it = methodInvoc.getArguments().iterator(); it.hasNext();)
        {
            ((Acceptor)it.next()).accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.NullLiteral)
     */
    public void visit(NullLiteral nullLit)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.Ordering)
     */
    public void visit(Ordering ordering)
    {
        ordering.getExpression().accept(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.ThisExpression)
     */
    public void visit(ThisExpression thisExpr)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.Type)
     */
    public void visit(Type type)
    {
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.TypeAccess)
     */
    public void visit(TypeAccess typeAccess)
    {
        if (typeAccess.hasBaseExpression())
        {
            typeAccess.getBaseExpression().accept(this);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.UnaryExpression)
     */
    public void visit(UnaryExpression unaryExpr)
    {
        if (unaryExpr.getOperator() == UnaryExpression.OPERATOR_CAST)
        {
            unaryExpr.getCastType().accept(this);
        }
        unaryExpr.getInnerExpression().accept(this);
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.jdo.jdoql.Visitor#visit(org.apache.ojb.jdo.jdoql.NameExpression)
     */
    public void visit(NameExpression nameExpr)
    {
        if (nameExpr.hasBaseExpression())
        {
            nameExpr.getBaseExpression().accept(this);
        }
    }

}
