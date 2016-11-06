// $ANTLR 2.7.5 (20050128): "jdoql-ojb-treeparser.g" -> "JDOQLTreeParser.java"$

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

	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import antlr.NoViableAltException;
import antlr.RecognitionException;
import antlr.collections.AST;
import antlr.collections.impl.BitSet;


public class JDOQLTreeParser extends antlr.TreeParser       implements JDOQLTreeParserTokenTypes
 {
public JDOQLTreeParser() {
	tokenNames = _tokenNames;
}

	public final Map  declareParameters(AST _t) throws RecognitionException {
		Map result;
		
		AST declareParameters_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				LocalVariable varDecl;
		
				result = new HashMap();
			
		AST __t2 = _t;
		AST tmp1_AST_in = _t;
		match(_t,PARAMETERS);
		_t = _t.getFirstChild();
		{
		_loop4:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==PARAMETER)) {
				varDecl=parameter(_t);
				_t = _retTree;
				
					    result.put(varDecl.getName(), varDecl);
				
			}
			else {
				break _loop4;
			}
			
		} while (true);
		}
		_t = __t2;
		_t = _t.getNextSibling();
		_retTree = _t;
		return result;
	}
	
	public final LocalVariable  parameter(AST _t) throws RecognitionException {
		LocalVariable varDecl;
		
		AST parameter_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST name = null;
		
		
				Type varType = null;
			
		AST __t6 = _t;
		AST tmp2_AST_in = _t;
		match(_t,PARAMETER);
		_t = _t.getFirstChild();
		varType=type(_t);
		_t = _retTree;
		name = _t;
		match(_t,IDENTIFIER);
		_t = _t.getNextSibling();
		_t = __t6;
		_t = _t.getNextSibling();
		
			    varDecl = new LocalVariable(varType, name.getText());
			    varDecl.setPosition(varType.getLine(), varType.getColumn());
		
		_retTree = _t;
		return varDecl;
	}
	
	public final Type  type(AST _t) throws RecognitionException {
		Type result;
		
		AST type_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST primitiveTypeName = null;
		AST typeName = null;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case PRIMITIVE_TYPE:
		{
			primitiveTypeName = _t;
			match(_t,PRIMITIVE_TYPE);
			_t = _t.getNextSibling();
			
				      result = new Type(primitiveTypeName.getText(), true);
				  	  result.setPosition(primitiveTypeName.getLine(), primitiveTypeName.getColumn());
				
			break;
		}
		case NAME:
		{
			typeName = _t;
			match(_t,NAME);
			_t = _t.getNextSibling();
			
				      result = new Type(typeName.getText(), false);
				  	  result.setPosition(typeName.getLine(), typeName.getColumn());
				
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return result;
	}
	
	public final Map  declareVariables(AST _t) throws RecognitionException {
		Map result;
		
		AST declareVariables_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				LocalVariable varDecl;
		
				result = new HashMap();
			
		AST __t8 = _t;
		AST tmp3_AST_in = _t;
		match(_t,VARIABLES);
		_t = _t.getFirstChild();
		{
		_loop10:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==VARIABLE)) {
				varDecl=variable(_t);
				_t = _retTree;
				
					    result.put(varDecl.getName(), varDecl);
				
			}
			else {
				break _loop10;
			}
			
		} while (true);
		}
		_t = __t8;
		_t = _t.getNextSibling();
		_retTree = _t;
		return result;
	}
	
	public final LocalVariable  variable(AST _t) throws RecognitionException {
		LocalVariable varDecl;
		
		AST variable_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST name = null;
		
		
				Type varType = null;
			
		AST __t12 = _t;
		AST tmp4_AST_in = _t;
		match(_t,VARIABLE);
		_t = _t.getFirstChild();
		varType=type(_t);
		_t = _retTree;
		name = _t;
		match(_t,IDENTIFIER);
		_t = _t.getNextSibling();
		_t = __t12;
		_t = _t.getNextSibling();
		
			    varDecl = new LocalVariable(varType, name.getText());
			    varDecl.setPosition(varType.getLine(), varType.getColumn());
		
		_retTree = _t;
		return varDecl;
	}
	
	public final List  declareImports(AST _t) throws RecognitionException {
		List result;
		
		AST declareImports_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				Import importDecl;
		
				result = new ArrayList();
			
		AST __t14 = _t;
		AST tmp5_AST_in = _t;
		match(_t,IMPORTS);
		_t = _t.getFirstChild();
		{
		_loop16:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==IMPORT||_t.getType()==ON_DEMAND_IMPORT)) {
				importDecl=importDeclaration(_t);
				_t = _retTree;
				
					    result.add(importDecl);
				
			}
			else {
				break _loop16;
			}
			
		} while (true);
		}
		_t = __t14;
		_t = _t.getNextSibling();
		_retTree = _t;
		return result;
	}
	
	public final Import  importDeclaration(AST _t) throws RecognitionException {
		Import importDecl;
		
		AST importDeclaration_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST i1 = null;
		AST name1 = null;
		AST i2 = null;
		AST name2 = null;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case IMPORT:
		{
			AST __t19 = _t;
			i1 = _t==ASTNULL ? null :(AST)_t;
			match(_t,IMPORT);
			_t = _t.getFirstChild();
			name1 = _t;
			match(_t,NAME);
			_t = _t.getNextSibling();
			_t = __t19;
			_t = _t.getNextSibling();
			
				  	  importDecl = new Import(name1.getText(), false);
				  	  importDecl.setPosition(i1.getLine(), i1.getColumn());
				
			break;
		}
		case ON_DEMAND_IMPORT:
		{
			AST __t20 = _t;
			i2 = _t==ASTNULL ? null :(AST)_t;
			match(_t,ON_DEMAND_IMPORT);
			_t = _t.getFirstChild();
			name2 = _t;
			match(_t,NAME);
			_t = _t.getNextSibling();
			_t = __t20;
			_t = _t.getNextSibling();
			
				  	  importDecl = new Import(name2.getText(), true);
				  	  importDecl.setPosition(i2.getLine(), i2.getColumn());
				
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return importDecl;
	}
	
	public final List  setOrdering(AST _t) throws RecognitionException {
		List result;
		
		AST setOrdering_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				Ordering orderingDecl;
		
				result = new ArrayList();
			
		AST __t22 = _t;
		AST tmp6_AST_in = _t;
		match(_t,ORDERSPECS);
		_t = _t.getFirstChild();
		{
		_loop24:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==ORDERSPEC)) {
				orderingDecl=orderSpecification(_t);
				_t = _retTree;
				
					    result.add(orderingDecl);
				
			}
			else {
				break _loop24;
			}
			
		} while (true);
		}
		_t = __t22;
		_t = _t.getNextSibling();
		_retTree = _t;
		return result;
	}
	
	public final Ordering  orderSpecification(AST _t) throws RecognitionException {
		Ordering orderingDecl;
		
		AST orderSpecification_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST o = null;
		
		
				Expression expr = null;
				boolean    isAscending;
			
		AST __t26 = _t;
		o = _t==ASTNULL ? null :(AST)_t;
		match(_t,ORDERSPEC);
		_t = _t.getFirstChild();
		expr=expression(_t);
		_t = _retTree;
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case LITERAL_ASCENDING:
		{
			AST tmp7_AST_in = _t;
			match(_t,LITERAL_ASCENDING);
			_t = _t.getNextSibling();
			
				    isAscending = true;
			
			break;
		}
		case LITERAL_DESCENDING:
		{
			AST tmp8_AST_in = _t;
			match(_t,LITERAL_DESCENDING);
			_t = _t.getNextSibling();
			
				    isAscending = false;
			
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t26;
		_t = _t.getNextSibling();
		
				orderingDecl = new Ordering(expr, isAscending);
				orderingDecl.setPosition(o.getLine(), o.getColumn());
			
		_retTree = _t;
		return orderingDecl;
	}
	
	public final Expression  expression(AST _t) throws RecognitionException {
		Expression expr;
		
		AST expression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case LITERAL_TRUE:
		case LITERAL_FALSE:
		case FLOATING_POINT_LITERAL:
		case INTEGER_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		{
			expr=literal(_t);
			_t = _retTree;
			break;
		}
		case LITERAL_NULL:
		{
			expr=nullLiteral(_t);
			_t = _retTree;
			break;
		}
		case LITERAL_THIS:
		{
			expr=thisExpression(_t);
			_t = _retTree;
			break;
		}
		case OP_GREATER_OR_EQUAL:
		case OP_LOWER_OR_EQUAL:
		case OP_NOT_EQUAL:
		case OP_AND:
		case OP_OR:
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
			expr=binaryExpression(_t);
			_t = _retTree;
			break;
		}
		case CAST:
		case OP_UNARY_MINUS:
		case OP_UNARY_PLUS:
		case OP_NOT:
		case OP_BITWISE_COMPLEMENT:
		{
			expr=unaryExpression(_t);
			_t = _retTree;
			break;
		}
		case METHOD_INVOCATION:
		{
			expr=methodInvocation(_t);
			_t = _retTree;
			break;
		}
		case NAME_EXPRESSION:
		{
			expr=nameExpression(_t);
			_t = _retTree;
			break;
		}
		case SEP_DOT:
		{
			expr=postfixExpression(_t);
			_t = _retTree;
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return expr;
	}
	
	public final Literal  literal(AST _t) throws RecognitionException {
		Literal literal;
		
		AST literal_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST l1 = null;
		AST l2 = null;
		AST l3 = null;
		AST l4 = null;
		AST l5 = null;
		AST l6 = null;
		
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case LITERAL_TRUE:
		{
			l1 = _t;
			match(_t,LITERAL_TRUE);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(boolean.class, l1.getText());
					  literal.setPosition(l1.getLine(), l2.getColumn());
				
			break;
		}
		case LITERAL_FALSE:
		{
			l2 = _t;
			match(_t,LITERAL_FALSE);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(boolean.class, l2.getText());
					  literal.setPosition(l2.getLine(), l2.getColumn());
				
			break;
		}
		case INTEGER_LITERAL:
		{
			l3 = _t;
			match(_t,INTEGER_LITERAL);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(long.class, l3.getText());
					  literal.setPosition(l3.getLine(), l3.getColumn());
				
			break;
		}
		case FLOATING_POINT_LITERAL:
		{
			l4 = _t;
			match(_t,FLOATING_POINT_LITERAL);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(double.class, l4.getText());
					  literal.setPosition(l4.getLine(), l4.getColumn());
				
			break;
		}
		case CHARACTER_LITERAL:
		{
			l5 = _t;
			match(_t,CHARACTER_LITERAL);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(char.class, l5.getText());
					  literal.setPosition(l5.getLine(), l5.getColumn());
				
			break;
		}
		case STRING_LITERAL:
		{
			l6 = _t;
			match(_t,STRING_LITERAL);
			_t = _t.getNextSibling();
			
				  	  literal = new Literal(String.class, l6.getText());
					  literal.setPosition(l6.getLine(), l6.getColumn());
				
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return literal;
	}
	
	public final NullLiteral  nullLiteral(AST _t) throws RecognitionException {
		NullLiteral literal;
		
		AST nullLiteral_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST l = null;
		
		l = _t;
		match(_t,LITERAL_NULL);
		_t = _t.getNextSibling();
		
			  	literal = new NullLiteral();
				literal.setPosition(l.getLine(), l.getColumn());
			
		_retTree = _t;
		return literal;
	}
	
	public final ThisExpression  thisExpression(AST _t) throws RecognitionException {
		ThisExpression expr;
		
		AST thisExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST l = null;
		
		l = _t;
		match(_t,LITERAL_THIS);
		_t = _t.getNextSibling();
		
		expr = new ThisExpression();
				expr.setPosition(l.getLine(), l.getColumn());
		
		_retTree = _t;
		return expr;
	}
	
	public final BinaryExpression  binaryExpression(AST _t) throws RecognitionException {
		BinaryExpression expr;
		
		AST binaryExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST o1 = null;
		AST o2 = null;
		AST o3 = null;
		AST o4 = null;
		AST o5 = null;
		AST o6 = null;
		AST o7 = null;
		AST o8 = null;
		AST o9 = null;
		AST o10 = null;
		AST o11 = null;
		AST o12 = null;
		AST o13 = null;
		AST o14 = null;
		AST o15 = null;
		
		
				Expression left  = null;
				Expression right = null;
			
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case OP_MULTIPLY:
		{
			AST __t53 = _t;
			o1 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_MULTIPLY);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t53;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_MULTIPLY, right);
					  expr.setPosition(o1.getLine(), o1.getColumn());
				
			break;
		}
		case OP_DIVIDE:
		{
			AST __t54 = _t;
			o2 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_DIVIDE);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t54;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_DIVIDE, right);
					  expr.setPosition(o2.getLine(), o2.getColumn());
				
			break;
		}
		case OP_BINARY_PLUS:
		{
			AST __t55 = _t;
			o3 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BINARY_PLUS);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t55;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_PLUS, right);
					  expr.setPosition(o3.getLine(), o3.getColumn());
				
			break;
		}
		case OP_BINARY_MINUS:
		{
			AST __t56 = _t;
			o4 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BINARY_MINUS);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t56;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_MINUS, right);
					  expr.setPosition(o4.getLine(), o4.getColumn());
				
			break;
		}
		case OP_LOWER:
		{
			AST __t57 = _t;
			o5 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_LOWER);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t57;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_LOWER, right);
					  expr.setPosition(o5.getLine(), o5.getColumn());
				
			break;
		}
		case OP_GREATER:
		{
			AST __t58 = _t;
			o6 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_GREATER);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t58;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_GREATER, right);
					  expr.setPosition(o6.getLine(), o6.getColumn());
				
			break;
		}
		case OP_LOWER_OR_EQUAL:
		{
			AST __t59 = _t;
			o7 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_LOWER_OR_EQUAL);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t59;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_LOWER_OR_EQUAL, right);
					  expr.setPosition(o7.getLine(), o7.getColumn());
				
			break;
		}
		case OP_GREATER_OR_EQUAL:
		{
			AST __t60 = _t;
			o8 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_GREATER_OR_EQUAL);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t60;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_GREATER_OR_EQUAL, right);
					  expr.setPosition(o8.getLine(), o8.getColumn());
				
			break;
		}
		case OP_EQUAL:
		{
			AST __t61 = _t;
			o9 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_EQUAL);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t61;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_EQUAL, right);
					  expr.setPosition(o9.getLine(), o9.getColumn());
				
			break;
		}
		case OP_NOT_EQUAL:
		{
			AST __t62 = _t;
			o10 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_NOT_EQUAL);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t62;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_NOT_EQUAL, right);
					  expr.setPosition(o10.getLine(), o10.getColumn());
				
			break;
		}
		case OP_BITWISE_AND:
		{
			AST __t63 = _t;
			o11 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BITWISE_AND);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t63;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_AND, right);
					  expr.setPosition(o11.getLine(), o11.getColumn());
				
			break;
		}
		case OP_BITWISE_XOR:
		{
			AST __t64 = _t;
			o12 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BITWISE_XOR);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t64;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_XOR, right);
					  expr.setPosition(o12.getLine(), o12.getColumn());
				
			break;
		}
		case OP_BITWISE_OR:
		{
			AST __t65 = _t;
			o13 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BITWISE_OR);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t65;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_OR, right);
					  expr.setPosition(o13.getLine(), o13.getColumn());
				
			break;
		}
		case OP_AND:
		{
			AST __t66 = _t;
			o14 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_AND);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t66;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_AND, right);
					  expr.setPosition(o14.getLine(), o14.getColumn());
				
			break;
		}
		case OP_OR:
		{
			AST __t67 = _t;
			o15 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_OR);
			_t = _t.getFirstChild();
			left=expression(_t);
			_t = _retTree;
			right=expression(_t);
			_t = _retTree;
			_t = __t67;
			_t = _t.getNextSibling();
			
				  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_OR, right);
					  expr.setPosition(o15.getLine(), o15.getColumn());
				
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return expr;
	}
	
	public final UnaryExpression  unaryExpression(AST _t) throws RecognitionException {
		UnaryExpression expr;
		
		AST unaryExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST o1 = null;
		AST o2 = null;
		AST o3 = null;
		AST o4 = null;
		AST o5 = null;
		
		
				Expression inner    = null;
				Type       castType = null;
			
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case OP_UNARY_MINUS:
		{
			AST __t46 = _t;
			o1 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_UNARY_MINUS);
			_t = _t.getFirstChild();
			inner=expression(_t);
			_t = _retTree;
			_t = __t46;
			_t = _t.getNextSibling();
			
				  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_MINUS, inner);
					  expr.setPosition(o1.getLine(), o1.getColumn());
				
			break;
		}
		case OP_UNARY_PLUS:
		{
			AST __t47 = _t;
			o2 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_UNARY_PLUS);
			_t = _t.getFirstChild();
			inner=expression(_t);
			_t = _retTree;
			_t = __t47;
			_t = _t.getNextSibling();
			
				  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_PLUS, inner);
					  expr.setPosition(o2.getLine(), o2.getColumn());
				
			break;
		}
		case OP_BITWISE_COMPLEMENT:
		{
			AST __t48 = _t;
			o3 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_BITWISE_COMPLEMENT);
			_t = _t.getFirstChild();
			inner=expression(_t);
			_t = _retTree;
			_t = __t48;
			_t = _t.getNextSibling();
			
				  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_BITWISE_COMPLEMENT, inner);
					  expr.setPosition(o3.getLine(), o3.getColumn());
				
			break;
		}
		case OP_NOT:
		{
			AST __t49 = _t;
			o4 = _t==ASTNULL ? null :(AST)_t;
			match(_t,OP_NOT);
			_t = _t.getFirstChild();
			inner=expression(_t);
			_t = _retTree;
			_t = __t49;
			_t = _t.getNextSibling();
			
				  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_NOT, inner);
					  expr.setPosition(o4.getLine(), o4.getColumn());
				
			break;
		}
		case CAST:
		{
			AST __t50 = _t;
			o5 = _t==ASTNULL ? null :(AST)_t;
			match(_t,CAST);
			_t = _t.getFirstChild();
			castType=type(_t);
			_t = _retTree;
			inner=expression(_t);
			_t = _retTree;
			_t = __t50;
			_t = _t.getNextSibling();
			
				  	  expr = new UnaryExpression(castType, inner);
					  expr.setPosition(o5.getLine(), o5.getColumn());
				
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_retTree = _t;
		return expr;
	}
	
	public final MethodInvocation  methodInvocation(AST _t) throws RecognitionException {
		MethodInvocation methodInvoc;
		
		AST methodInvocation_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST m = null;
		
		
				List args = null;
			
		AST __t35 = _t;
		m = _t==ASTNULL ? null :(AST)_t;
		match(_t,METHOD_INVOCATION);
		_t = _t.getFirstChild();
		args=argList(_t);
		_t = _retTree;
		_t = __t35;
		_t = _t.getNextSibling();
		
				methodInvoc = new MethodInvocation(null, m.getText(), args);
				methodInvoc.setPosition(m.getLine(), m.getColumn());
			
		_retTree = _t;
		return methodInvoc;
	}
	
	public final NameExpression  nameExpression(AST _t) throws RecognitionException {
		NameExpression nameExpr;
		
		AST nameExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST expr = null;
		
		expr = _t;
		match(_t,NAME_EXPRESSION);
		_t = _t.getNextSibling();
		
				nameExpr = new NameExpression(null, expr.getText());
			    nameExpr.setPosition(expr.getLine(), expr.getColumn());
			
		_retTree = _t;
		return nameExpr;
	}
	
	public final PostfixExpression  postfixExpression(AST _t) throws RecognitionException {
		PostfixExpression expr;
		
		AST postfixExpression_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				Expression        baseExpr     = null;
				PostfixExpression trailingExpr = null;
			
		AST __t42 = _t;
		AST tmp9_AST_in = _t;
		match(_t,SEP_DOT);
		_t = _t.getFirstChild();
		baseExpr=expression(_t);
		_t = _retTree;
		{
		if (_t==null) _t=ASTNULL;
		switch ( _t.getType()) {
		case NAME_EXPRESSION:
		{
			trailingExpr=nameExpression(_t);
			_t = _retTree;
			break;
		}
		case METHOD_INVOCATION:
		{
			trailingExpr=methodInvocation(_t);
			_t = _retTree;
			break;
		}
		default:
		{
			throw new NoViableAltException(_t);
		}
		}
		}
		_t = __t42;
		_t = _t.getNextSibling();
		
				expr = trailingExpr;
				expr.setBaseExpression(baseExpr);
			
		_retTree = _t;
		return expr;
	}
	
	public final List  argList(AST _t) throws RecognitionException {
		List args;
		
		AST argList_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		
				Expression argExpr = null;
		
				args = new ArrayList();
			
		AST __t37 = _t;
		AST tmp10_AST_in = _t;
		match(_t,ARG_LIST);
		_t = _t.getFirstChild();
		{
		_loop39:
		do {
			if (_t==null) _t=ASTNULL;
			if ((_tokenSet_0.member(_t.getType()))) {
				argExpr=expression(_t);
				_t = _retTree;
				
					     args.add(argExpr);
				
			}
			else {
				break _loop39;
			}
			
		} while (true);
		}
		_t = __t37;
		_t = _t.getNextSibling();
		_retTree = _t;
		return args;
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
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -6918056827456143360L, 1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	}
	
