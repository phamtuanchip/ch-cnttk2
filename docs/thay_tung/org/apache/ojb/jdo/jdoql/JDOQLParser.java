// $ANTLR 2.7.5 (20050128): "jdoql-ojb-parser.g" -> "JDOQLParser.java"$

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

import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.AST;
import antlr.collections.impl.ASTArray;
import antlr.collections.impl.BitSet;

public class JDOQLParser extends antlr.LLkParser       implements JDOQLParserTokenTypes
 {

protected JDOQLParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public JDOQLParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected JDOQLParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public JDOQLParser(TokenStream lexer) {
  this(lexer,2);
}

public JDOQLParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void declareParameters() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declareParameters_AST = null;
		
		parameter();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop3:
		do {
			if ((LA(1)==SEP_COMMA) && (_tokenSet_0.member(LA(2)))) {
				match(SEP_COMMA);
				parameter();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop3;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case SEP_COMMA:
		{
			match(SEP_COMMA);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			declareParameters_AST = currentAST.root;
			declareParameters_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(PARAMETERS,"PARAMETERS")).add(declareParameters_AST));
			currentAST.root = declareParameters_AST;
			currentAST.child = declareParameters_AST!=null &&declareParameters_AST.getFirstChild()!=null ?
				declareParameters_AST.getFirstChild() : declareParameters_AST;
			currentAST.advanceChildToEnd();
		}
		declareParameters_AST = currentAST.root;
		returnAST = declareParameters_AST;
	}
	
	public final void parameter() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST parameter_AST = null;
		
		type();
		astFactory.addASTChild(currentAST, returnAST);
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			parameter_AST = currentAST.root;
			parameter_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(PARAMETER,"PARAMETER")).add(parameter_AST));
			currentAST.root = parameter_AST;
			currentAST.child = parameter_AST!=null &&parameter_AST.getFirstChild()!=null ?
				parameter_AST.getFirstChild() : parameter_AST;
			currentAST.advanceChildToEnd();
		}
		parameter_AST = currentAST.root;
		returnAST = parameter_AST;
	}
	
	public final void type() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST type_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_BOOLEAN:
		case LITERAL_BYTE:
		case LITERAL_SHORT:
		case LITERAL_INT:
		case LITERAL_LONG:
		case LITERAL_CHAR:
		case LITERAL_FLOAT:
		case LITERAL_DOUBLE:
		{
			primitiveType();
			astFactory.addASTChild(currentAST, returnAST);
			type_AST = currentAST.root;
			break;
		}
		case IDENTIFIER:
		{
			name();
			astFactory.addASTChild(currentAST, returnAST);
			type_AST = currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = type_AST;
	}
	
	public final void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		
		AST tmp3_AST = null;
		tmp3_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp3_AST);
		match(IDENTIFIER);
		identifier_AST = currentAST.root;
		returnAST = identifier_AST;
	}
	
	public final void declareVariables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declareVariables_AST = null;
		
		variable();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop8:
		do {
			if ((LA(1)==SEP_SEMICOLON) && (_tokenSet_0.member(LA(2)))) {
				match(SEP_SEMICOLON);
				variable();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop8;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case SEP_SEMICOLON:
		{
			match(SEP_SEMICOLON);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			declareVariables_AST = currentAST.root;
			declareVariables_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(VARIABLES,"VARIABLES")).add(declareVariables_AST));
			currentAST.root = declareVariables_AST;
			currentAST.child = declareVariables_AST!=null &&declareVariables_AST.getFirstChild()!=null ?
				declareVariables_AST.getFirstChild() : declareVariables_AST;
			currentAST.advanceChildToEnd();
		}
		declareVariables_AST = currentAST.root;
		returnAST = declareVariables_AST;
	}
	
	public final void variable() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variable_AST = null;
		
		type();
		astFactory.addASTChild(currentAST, returnAST);
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			variable_AST = currentAST.root;
			variable_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(VARIABLE,"VARIABLE")).add(variable_AST));
			currentAST.root = variable_AST;
			currentAST.child = variable_AST!=null &&variable_AST.getFirstChild()!=null ?
				variable_AST.getFirstChild() : variable_AST;
			currentAST.advanceChildToEnd();
		}
		variable_AST = currentAST.root;
		returnAST = variable_AST;
	}
	
	public final void declareImports() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST declareImports_AST = null;
		
		importDeclaration();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop13:
		do {
			if ((LA(1)==SEP_SEMICOLON) && (LA(2)==LITERAL_IMPORT)) {
				match(SEP_SEMICOLON);
				importDeclaration();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop13;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case SEP_SEMICOLON:
		{
			match(SEP_SEMICOLON);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			declareImports_AST = currentAST.root;
			declareImports_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(IMPORTS,"IMPORTS")).add(declareImports_AST));
			currentAST.root = declareImports_AST;
			currentAST.child = declareImports_AST!=null &&declareImports_AST.getFirstChild()!=null ?
				declareImports_AST.getFirstChild() : declareImports_AST;
			currentAST.advanceChildToEnd();
		}
		declareImports_AST = currentAST.root;
		returnAST = declareImports_AST;
	}
	
	public final void importDeclaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST importDeclaration_AST = null;
		Token  node = null;
		AST node_AST = null;
		
		node = LT(1);
		node_AST = astFactory.create(node);
		astFactory.makeASTRoot(currentAST, node_AST);
		match(LITERAL_IMPORT);
		name();
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			node_AST.setType(IMPORT);
		}
		{
		switch ( LA(1)) {
		case SEP_DOT:
		{
			match(SEP_DOT);
			match(OP_MULTIPLY);
			if ( inputState.guessing==0 ) {
				node_AST.setType(ON_DEMAND_IMPORT);
			}
			break;
		}
		case EOF:
		case SEP_SEMICOLON:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		importDeclaration_AST = currentAST.root;
		returnAST = importDeclaration_AST;
	}
	
	public final void name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST name_AST = null;
		Token  dot = null;
		AST dot_AST = null;
		AST i_AST = null;
		
		identifier();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop80:
		do {
			if ((LA(1)==SEP_DOT) && (LA(2)==IDENTIFIER)) {
				dot = LT(1);
				dot_AST = astFactory.create(dot);
				match(SEP_DOT);
				identifier();
				i_AST = returnAST;
				if ( inputState.guessing==0 ) {
					name_AST = currentAST.root;
					
							  name_AST.setText(name_AST.getText() + dot_AST.getText() + i_AST.getText());
						
				}
			}
			else {
				break _loop80;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			name_AST = currentAST.root;
			name_AST.setType(NAME);
		}
		name_AST = currentAST.root;
		returnAST = name_AST;
	}
	
	public final void setOrdering() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST setOrdering_AST = null;
		
		orderSpecification();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop19:
		do {
			if ((LA(1)==SEP_COMMA) && (_tokenSet_1.member(LA(2)))) {
				match(SEP_COMMA);
				orderSpecification();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop19;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case SEP_COMMA:
		{
			match(SEP_COMMA);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			setOrdering_AST = currentAST.root;
			setOrdering_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(ORDERSPECS,"ORDERSPECS")).add(setOrdering_AST));
			currentAST.root = setOrdering_AST;
			currentAST.child = setOrdering_AST!=null &&setOrdering_AST.getFirstChild()!=null ?
				setOrdering_AST.getFirstChild() : setOrdering_AST;
			currentAST.advanceChildToEnd();
		}
		setOrdering_AST = currentAST.root;
		returnAST = setOrdering_AST;
	}
	
	public final void orderSpecification() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST orderSpecification_AST = null;
		
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case LITERAL_ASCENDING:
		{
			AST tmp12_AST = null;
			tmp12_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp12_AST);
			match(LITERAL_ASCENDING);
			break;
		}
		case LITERAL_DESCENDING:
		{
			AST tmp13_AST = null;
			tmp13_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp13_AST);
			match(LITERAL_DESCENDING);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			orderSpecification_AST = currentAST.root;
			orderSpecification_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(ORDERSPEC,"ORDERSPEC")).add(orderSpecification_AST));
			currentAST.root = orderSpecification_AST;
			currentAST.child = orderSpecification_AST!=null &&orderSpecification_AST.getFirstChild()!=null ?
				orderSpecification_AST.getFirstChild() : orderSpecification_AST;
			currentAST.advanceChildToEnd();
		}
		orderSpecification_AST = currentAST.root;
		returnAST = orderSpecification_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expression_AST = null;
		
		conditionalOrExpression();
		astFactory.addASTChild(currentAST, returnAST);
		expression_AST = currentAST.root;
		returnAST = expression_AST;
	}
	
	public final void primary() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST primary_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_TRUE:
		case LITERAL_FALSE:
		case LITERAL_NULL:
		case FLOATING_POINT_LITERAL:
		case INTEGER_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		{
			literal();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LITERAL_THIS:
		{
			AST tmp14_AST = null;
			tmp14_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp14_AST);
			match(LITERAL_THIS);
			break;
		}
		case SEP_OPENING_PARENTHESIS:
		{
			match(SEP_OPENING_PARENTHESIS);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEP_CLOSING_PARENTHESIS);
			break;
		}
		case IDENTIFIER:
		{
			directAccess();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		primary_AST = currentAST.root;
		returnAST = primary_AST;
	}
	
	public final void literal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST literal_AST = null;
		
		switch ( LA(1)) {
		case LITERAL_TRUE:
		{
			AST tmp17_AST = null;
			tmp17_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp17_AST);
			match(LITERAL_TRUE);
			literal_AST = currentAST.root;
			break;
		}
		case LITERAL_FALSE:
		{
			AST tmp18_AST = null;
			tmp18_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp18_AST);
			match(LITERAL_FALSE);
			literal_AST = currentAST.root;
			break;
		}
		case LITERAL_NULL:
		{
			AST tmp19_AST = null;
			tmp19_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp19_AST);
			match(LITERAL_NULL);
			literal_AST = currentAST.root;
			break;
		}
		case INTEGER_LITERAL:
		{
			AST tmp20_AST = null;
			tmp20_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp20_AST);
			match(INTEGER_LITERAL);
			literal_AST = currentAST.root;
			break;
		}
		case FLOATING_POINT_LITERAL:
		{
			AST tmp21_AST = null;
			tmp21_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp21_AST);
			match(FLOATING_POINT_LITERAL);
			literal_AST = currentAST.root;
			break;
		}
		case CHARACTER_LITERAL:
		{
			AST tmp22_AST = null;
			tmp22_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp22_AST);
			match(CHARACTER_LITERAL);
			literal_AST = currentAST.root;
			break;
		}
		case STRING_LITERAL:
		{
			AST tmp23_AST = null;
			tmp23_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp23_AST);
			match(STRING_LITERAL);
			literal_AST = currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = literal_AST;
	}
	
	public final void directAccess() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST directAccess_AST = null;
		Token  id = null;
		AST id_AST = null;
		
		id = LT(1);
		id_AST = astFactory.create(id);
		astFactory.makeASTRoot(currentAST, id_AST);
		match(IDENTIFIER);
		{
		switch ( LA(1)) {
		case SEP_OPENING_PARENTHESIS:
		{
			match(SEP_OPENING_PARENTHESIS);
			argList();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEP_CLOSING_PARENTHESIS);
			if ( inputState.guessing==0 ) {
				directAccess_AST = currentAST.root;
				directAccess_AST.setType(METHOD_INVOCATION);
			}
			break;
		}
		case EOF:
		case LITERAL_ASCENDING:
		case LITERAL_DESCENDING:
		case OP_GREATER_OR_EQUAL:
		case OP_LOWER_OR_EQUAL:
		case OP_NOT_EQUAL:
		case OP_AND:
		case OP_OR:
		case SEP_DOT:
		case SEP_CLOSING_PARENTHESIS:
		case SEP_COMMA:
		case OP_EQUAL:
		case OP_GREATER:
		case OP_LOWER:
		case OP_BITWISE_AND:
		case OP_BITWISE_OR:
		case OP_BITWISE_XOR:
		case OP_BINARY_PLUS:
		case OP_BINARY_MINUS:
		case OP_MULTIPLY:
		case OP_DIVIDE:
		{
			if ( inputState.guessing==0 ) {
				directAccess_AST = currentAST.root;
				directAccess_AST.setType(NAME_EXPRESSION);
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		directAccess_AST = currentAST.root;
		returnAST = directAccess_AST;
	}
	
	public final void argList() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST argList_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_TRUE:
		case LITERAL_FALSE:
		case LITERAL_NULL:
		case LITERAL_THIS:
		case FLOATING_POINT_LITERAL:
		case INTEGER_LITERAL:
		case SEP_OPENING_PARENTHESIS:
		case OP_NOT:
		case OP_BITWISE_COMPLEMENT:
		case OP_BINARY_PLUS:
		case OP_BINARY_MINUS:
		case IDENTIFIER:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		{
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop30:
			do {
				if ((LA(1)==SEP_COMMA)) {
					match(SEP_COMMA);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop30;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				argList_AST = currentAST.root;
				argList_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(ARG_LIST,"ARG_LIST")).add(argList_AST));
				currentAST.root = argList_AST;
				currentAST.child = argList_AST!=null &&argList_AST.getFirstChild()!=null ?
					argList_AST.getFirstChild() : argList_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case SEP_CLOSING_PARENTHESIS:
		{
			if ( inputState.guessing==0 ) {
				argList_AST = currentAST.root;
				argList_AST = astFactory.create(ARG_LIST,"ARG_LIST");
				currentAST.root = argList_AST;
				currentAST.child = argList_AST!=null &&argList_AST.getFirstChild()!=null ?
					argList_AST.getFirstChild() : argList_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		argList_AST = currentAST.root;
		returnAST = argList_AST;
	}
	
	public final void postfixExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST postfixExpression_AST = null;
		
		primary();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop33:
		do {
			if ((LA(1)==SEP_DOT)) {
				AST tmp27_AST = null;
				tmp27_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp27_AST);
				match(SEP_DOT);
				directAccess();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop33;
			}
			
		} while (true);
		}
		postfixExpression_AST = currentAST.root;
		returnAST = postfixExpression_AST;
	}
	
	public final void unaryExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unaryExpression_AST = null;
		Token  op1 = null;
		AST op1_AST = null;
		Token  op2 = null;
		AST op2_AST = null;
		
		{
		switch ( LA(1)) {
		case OP_BINARY_MINUS:
		{
			op1 = LT(1);
			op1_AST = astFactory.create(op1);
			astFactory.makeASTRoot(currentAST, op1_AST);
			match(OP_BINARY_MINUS);
			unaryExpression();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					  op1_AST.setType(OP_UNARY_MINUS);
				
			}
			break;
		}
		case OP_BINARY_PLUS:
		{
			op2 = LT(1);
			op2_AST = astFactory.create(op2);
			astFactory.makeASTRoot(currentAST, op2_AST);
			match(OP_BINARY_PLUS);
			unaryExpression();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					  op2_AST.setType(OP_UNARY_PLUS);
				
			}
			break;
		}
		case LITERAL_TRUE:
		case LITERAL_FALSE:
		case LITERAL_NULL:
		case LITERAL_THIS:
		case FLOATING_POINT_LITERAL:
		case INTEGER_LITERAL:
		case SEP_OPENING_PARENTHESIS:
		case OP_NOT:
		case OP_BITWISE_COMPLEMENT:
		case IDENTIFIER:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		{
			unaryExpressionNotPlusMinus();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		unaryExpression_AST = currentAST.root;
		returnAST = unaryExpression_AST;
	}
	
	public final void unaryExpressionNotPlusMinus() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unaryExpressionNotPlusMinus_AST = null;
		
		{
		switch ( LA(1)) {
		case OP_BITWISE_COMPLEMENT:
		{
			AST tmp28_AST = null;
			tmp28_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp28_AST);
			match(OP_BITWISE_COMPLEMENT);
			unaryExpression();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case OP_NOT:
		{
			AST tmp29_AST = null;
			tmp29_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp29_AST);
			match(OP_NOT);
			unaryExpression();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
			boolean synPredMatched39 = false;
			if (((LA(1)==SEP_OPENING_PARENTHESIS) && (_tokenSet_0.member(LA(2))))) {
				int _m39 = mark();
				synPredMatched39 = true;
				inputState.guessing++;
				try {
					{
					match(SEP_OPENING_PARENTHESIS);
					type();
					match(SEP_CLOSING_PARENTHESIS);
					unaryExpression();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched39 = false;
				}
				rewind(_m39);
				inputState.guessing--;
			}
			if ( synPredMatched39 ) {
				castExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2)))) {
				postfixExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		unaryExpressionNotPlusMinus_AST = currentAST.root;
		returnAST = unaryExpressionNotPlusMinus_AST;
	}
	
	public final void castExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST castExpression_AST = null;
		
		match(SEP_OPENING_PARENTHESIS);
		type();
		astFactory.addASTChild(currentAST, returnAST);
		match(SEP_CLOSING_PARENTHESIS);
		unaryExpression();
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			castExpression_AST = currentAST.root;
			castExpression_AST = astFactory.make( (new ASTArray(2)).add(astFactory.create(CAST,"CAST")).add(castExpression_AST));
			currentAST.root = castExpression_AST;
			currentAST.child = castExpression_AST!=null &&castExpression_AST.getFirstChild()!=null ?
				castExpression_AST.getFirstChild() : castExpression_AST;
			currentAST.advanceChildToEnd();
		}
		castExpression_AST = currentAST.root;
		returnAST = castExpression_AST;
	}
	
	public final void multiplicativeExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST multiplicativeExpression_AST = null;
		
		unaryExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop44:
		do {
			if ((LA(1)==OP_MULTIPLY||LA(1)==OP_DIVIDE)) {
				{
				switch ( LA(1)) {
				case OP_MULTIPLY:
				{
					AST tmp32_AST = null;
					tmp32_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp32_AST);
					match(OP_MULTIPLY);
					break;
				}
				case OP_DIVIDE:
				{
					AST tmp33_AST = null;
					tmp33_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp33_AST);
					match(OP_DIVIDE);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				unaryExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop44;
			}
			
		} while (true);
		}
		multiplicativeExpression_AST = currentAST.root;
		returnAST = multiplicativeExpression_AST;
	}
	
	public final void additiveExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST additiveExpression_AST = null;
		
		multiplicativeExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop48:
		do {
			if ((LA(1)==OP_BINARY_PLUS||LA(1)==OP_BINARY_MINUS)) {
				{
				switch ( LA(1)) {
				case OP_BINARY_PLUS:
				{
					AST tmp34_AST = null;
					tmp34_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp34_AST);
					match(OP_BINARY_PLUS);
					break;
				}
				case OP_BINARY_MINUS:
				{
					AST tmp35_AST = null;
					tmp35_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp35_AST);
					match(OP_BINARY_MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				multiplicativeExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop48;
			}
			
		} while (true);
		}
		additiveExpression_AST = currentAST.root;
		returnAST = additiveExpression_AST;
	}
	
	public final void relationalExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST relationalExpression_AST = null;
		
		additiveExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop52:
		do {
			if ((_tokenSet_4.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case OP_LOWER:
				{
					AST tmp36_AST = null;
					tmp36_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp36_AST);
					match(OP_LOWER);
					break;
				}
				case OP_GREATER:
				{
					AST tmp37_AST = null;
					tmp37_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp37_AST);
					match(OP_GREATER);
					break;
				}
				case OP_LOWER_OR_EQUAL:
				{
					AST tmp38_AST = null;
					tmp38_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp38_AST);
					match(OP_LOWER_OR_EQUAL);
					break;
				}
				case OP_GREATER_OR_EQUAL:
				{
					AST tmp39_AST = null;
					tmp39_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp39_AST);
					match(OP_GREATER_OR_EQUAL);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				additiveExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop52;
			}
			
		} while (true);
		}
		relationalExpression_AST = currentAST.root;
		returnAST = relationalExpression_AST;
	}
	
	public final void equalityExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST equalityExpression_AST = null;
		
		relationalExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop56:
		do {
			if ((LA(1)==OP_NOT_EQUAL||LA(1)==OP_EQUAL)) {
				{
				switch ( LA(1)) {
				case OP_EQUAL:
				{
					AST tmp40_AST = null;
					tmp40_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp40_AST);
					match(OP_EQUAL);
					break;
				}
				case OP_NOT_EQUAL:
				{
					AST tmp41_AST = null;
					tmp41_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp41_AST);
					match(OP_NOT_EQUAL);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				relationalExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop56;
			}
			
		} while (true);
		}
		equalityExpression_AST = currentAST.root;
		returnAST = equalityExpression_AST;
	}
	
	public final void andExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST andExpression_AST = null;
		
		equalityExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop59:
		do {
			if ((LA(1)==OP_BITWISE_AND)) {
				AST tmp42_AST = null;
				tmp42_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp42_AST);
				match(OP_BITWISE_AND);
				equalityExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop59;
			}
			
		} while (true);
		}
		andExpression_AST = currentAST.root;
		returnAST = andExpression_AST;
	}
	
	public final void exclusiveOrExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST exclusiveOrExpression_AST = null;
		
		andExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop62:
		do {
			if ((LA(1)==OP_BITWISE_XOR)) {
				AST tmp43_AST = null;
				tmp43_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp43_AST);
				match(OP_BITWISE_XOR);
				andExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop62;
			}
			
		} while (true);
		}
		exclusiveOrExpression_AST = currentAST.root;
		returnAST = exclusiveOrExpression_AST;
	}
	
	public final void inclusiveOrExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST inclusiveOrExpression_AST = null;
		
		exclusiveOrExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop65:
		do {
			if ((LA(1)==OP_BITWISE_OR)) {
				AST tmp44_AST = null;
				tmp44_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp44_AST);
				match(OP_BITWISE_OR);
				exclusiveOrExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop65;
			}
			
		} while (true);
		}
		inclusiveOrExpression_AST = currentAST.root;
		returnAST = inclusiveOrExpression_AST;
	}
	
	public final void conditionalAndExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conditionalAndExpression_AST = null;
		
		inclusiveOrExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop68:
		do {
			if ((LA(1)==OP_AND)) {
				AST tmp45_AST = null;
				tmp45_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp45_AST);
				match(OP_AND);
				inclusiveOrExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop68;
			}
			
		} while (true);
		}
		conditionalAndExpression_AST = currentAST.root;
		returnAST = conditionalAndExpression_AST;
	}
	
	public final void conditionalOrExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST conditionalOrExpression_AST = null;
		
		conditionalAndExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop71:
		do {
			if ((LA(1)==OP_OR)) {
				AST tmp46_AST = null;
				tmp46_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp46_AST);
				match(OP_OR);
				conditionalAndExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop71;
			}
			
		} while (true);
		}
		conditionalOrExpression_AST = currentAST.root;
		returnAST = conditionalOrExpression_AST;
	}
	
	public final void dummyExpressionUsage() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dummyExpressionUsage_AST = null;
		
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		dummyExpressionUsage_AST = currentAST.root;
		returnAST = dummyExpressionUsage_AST;
	}
	
	public final void primitiveType() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST primitiveType_AST = null;
		
		{
		switch ( LA(1)) {
		case LITERAL_BOOLEAN:
		{
			AST tmp47_AST = null;
			tmp47_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp47_AST);
			match(LITERAL_BOOLEAN);
			break;
		}
		case LITERAL_BYTE:
		{
			AST tmp48_AST = null;
			tmp48_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp48_AST);
			match(LITERAL_BYTE);
			break;
		}
		case LITERAL_SHORT:
		{
			AST tmp49_AST = null;
			tmp49_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp49_AST);
			match(LITERAL_SHORT);
			break;
		}
		case LITERAL_INT:
		{
			AST tmp50_AST = null;
			tmp50_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp50_AST);
			match(LITERAL_INT);
			break;
		}
		case LITERAL_LONG:
		{
			AST tmp51_AST = null;
			tmp51_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp51_AST);
			match(LITERAL_LONG);
			break;
		}
		case LITERAL_CHAR:
		{
			AST tmp52_AST = null;
			tmp52_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp52_AST);
			match(LITERAL_CHAR);
			break;
		}
		case LITERAL_FLOAT:
		{
			AST tmp53_AST = null;
			tmp53_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp53_AST);
			match(LITERAL_FLOAT);
			break;
		}
		case LITERAL_DOUBLE:
		{
			AST tmp54_AST = null;
			tmp54_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp54_AST);
			match(LITERAL_DOUBLE);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			primitiveType_AST = currentAST.root;
			primitiveType_AST.setType(PRIMITIVE_TYPE);
		}
		primitiveType_AST = currentAST.root;
		returnAST = primitiveType_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"PARAMETERS",
		"PARAMETER",
		"VARIABLES",
		"VARIABLE",
		"IMPORTS",
		"IMPORT",
		"ON_DEMAND_IMPORT",
		"ORDERSPECS",
		"ORDERSPEC",
		"EXPR",
		"CAST",
		"METHOD_INVOCATION",
		"ARG_LIST",
		"NAME_EXPRESSION",
		"PRIMITIVE_TYPE",
		"NAME",
		"\"import\"",
		"\"ascending\"",
		"\"descending\"",
		"\"true\"",
		"\"false\"",
		"\"null\"",
		"\"this\"",
		"\"boolean\"",
		"\"byte\"",
		"\"short\"",
		"\"int\"",
		"\"long\"",
		"\"char\"",
		"\"float\"",
		"\"double\"",
		"OP_GREATER_OR_EQUAL",
		"OP_LOWER_OR_EQUAL",
		"OP_NOT_EQUAL",
		"OP_AND",
		"OP_OR",
		"OP_UNARY_MINUS",
		"OP_UNARY_PLUS",
		"SEP_DOT",
		"FLOATING_POINT_LITERAL",
		"INTEGER_LITERAL",
		"SEP_OPENING_PARENTHESIS",
		"SEP_CLOSING_PARENTHESIS",
		"SEP_SEMICOLON",
		"SEP_COMMA",
		"OP_EQUAL",
		"OP_GREATER",
		"OP_LOWER",
		"OP_NOT",
		"OP_BITWISE_COMPLEMENT",
		"OP_BITWISE_AND",
		"OP_BITWISE_OR",
		"OP_BITWISE_XOR",
		"OP_BINARY_PLUS",
		"OP_BINARY_MINUS",
		"OP_MULTIPLY",
		"OP_DIVIDE",
		"WHITESPACE",
		"IDENTIFIER",
		"CHARACTER_LITERAL",
		"STRING_LITERAL",
		"INT_OR_FLOAT_LITERAL_OR_DOT",
		"LINE_TERMINATOR",
		"ESCAPE_SEQUENCE",
		"UNICODE_ESCAPE",
		"OCTAL_ESCAPE",
		"OCTAL_DIGIT",
		"DIGIT",
		"HEX_DIGIT",
		"EXPONENT_PART",
		"INTEGER_TYPE_SUFFIX",
		"FLOAT_TYPE_SUFFIX",
		"IDENTIFIER_START",
		"IDENTIFIER_PART"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 4611686052652908544L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -4165768082540724224L, 1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -4611624445650403328L, 1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -2305987079464550398L, 1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 3377802799742976L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}
