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

import java.util.List;

/**
 * Denotes types that visit the jdoql expression trees.
 * 
 * @author <a href="mailto:tomdz@apache.org">Thomas Dudziak</a>
 */
public interface Visitor
{
    /**
     * Processes the variable declarations.
     * 
     * @param variables The variables
     */
    public void visitVariables(List variables);

    /**
     * Processes the parameter declarations.
     * 
     * @param params The parameters
     */
    public void visitParameters(List params);

    /**
     * Processes the orderings.
     * 
     * @param orderings The orderings
     */
    public void visitOrderings(List orderings);

    /**
     * Processes the filter expression.
     * 
     * @param filterExpr The filter expression
     */
    public void visitFilter(Expression filterExpr);

    /**
     * Processes the given binary expression.
     * 
     * @param binExpr The binary expression to process
     */
    void visit(BinaryExpression binExpr);

    /**
     * Processes the given field access expression.
     * 
     * @param fieldAccess The field access to process
     */
    void visit(FieldAccess fieldAccess);

    /**
     * Processes the given import specification.
     * 
     * @param nullLit The import specification to process
     */
    void visit(Import importSpec);

    /**
     * Processes the given literal.
     * 
     * @param literal The literal to process
     */
    void visit(Literal literal);

    /**
     * Processes the given local variable declaration.
     * 
     * @param localVar The local variable declaration to process
     */
    void visit(LocalVariable localVar);

    /**
     * Processes the given local variable access expression.
     * 
     * @param localVarAccess The local variable access expression to process
     */
    void visit(LocalVariableAccess localVarAccess);

    /**
     * Processes the given method invocation expression.
     * 
     * @param methodInvoc The method invocation expression to process
     */
    void visit(MethodInvocation methodInvoc);

    /**
     * Processes the given name expression.
     * 
     * @param varAccess The name expression to process
     */
    void visit(NameExpression nameExpr);

    /**
     * Processes the given <code>null</code> literal.
     * 
     * @param nullLit The <code>null</code> literal to process
     */
    void visit(NullLiteral nullLit);

    /**
     * Processes the given ordering.
     * 
     * @param ordering The ordering to process
     */
    void visit(Ordering ordering);

    /**
     * Processes the given <code>this</code> expression.
     * 
     * @param thisExpr The <code>this</code> expression to process
     */
    void visit(ThisExpression thisExpr);
    
    /**
     * Processes the given type.
     * 
     * @param type The type to process
     */
    void visit(Type type);

    /**
     * Processes the given type access expression.
     * 
     * @param type The type access expression to process
     */
    void visit(TypeAccess typeAccess);

    /**
     * Processes the given unary expression.
     * 
     * @param unaryExpr The unary expression to process
     */
    void visit(UnaryExpression unaryExpr);
}
