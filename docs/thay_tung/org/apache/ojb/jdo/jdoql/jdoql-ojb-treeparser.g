header
{
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

	import java.util.*;
}

class JDOQLTreeParser extends TreeParser;

options
{
    k                   = 1;
    importVocab         = JDOQL;
    buildAST            = false;
    defaultErrorHandler = false;
}

// order follows BNF from the JDO spec (chapter 23)


// 23.2 Parameter Declarations

// This is a top-level rule
declareParameters returns [Map result] :
	{
		LocalVariable varDecl;

		result = new HashMap();
	}
	#( PARAMETERS
       (
         varDecl=parameter
         {
      	    result.put(varDecl.getName(), varDecl);
         }
       )*
    )
    ;

parameter returns [LocalVariable varDecl]:
	{
		Type varType = null;
	}
	#( PARAMETER varType=type name:IDENTIFIER )
    {
  	    varDecl = new LocalVariable(varType, name.getText());
  	    varDecl.setPosition(varType.getLine(), varType.getColumn());
    }
    ;


// 23.3 Variable Declarations

// This is a top-level rule
declareVariables returns [Map result] :
	{
		LocalVariable varDecl;

		result = new HashMap();
	}
	#( VARIABLES
       (
         varDecl=variable
         {
      	    result.put(varDecl.getName(), varDecl);
         }
       )*
    )
    ;

variable returns [LocalVariable varDecl]:
	{
		Type varType = null;
	}
	#( VARIABLE varType=type name:IDENTIFIER )
    {
  	    varDecl = new LocalVariable(varType, name.getText());
  	    varDecl.setPosition(varType.getLine(), varType.getColumn());
    }
    ;


// 23.4 Import Declarations

// This is a top-level rule
declareImports returns [List result] :
	{
		Import importDecl;

		result = new ArrayList();
	}
	#( IMPORTS
       (
         importDecl=importDeclaration
         {
      	    result.add(importDecl);
         }
       )*
    )
    ;

importDeclaration returns [Import importDecl]:
	(
	  #( i1:IMPORT name1:NAME )
	  {
	  	  importDecl = new Import(name1.getText(), false);
	  	  importDecl.setPosition(i1.getLine(), i1.getColumn());
	  }
	| #( i2:ON_DEMAND_IMPORT name2:NAME )
	  {
	  	  importDecl = new Import(name2.getText(), true);
	  	  importDecl.setPosition(i2.getLine(), i2.getColumn());
	  }
	)
    ;


// 23.3 Ordering Specifications

setOrdering returns [List result]:
	{
		Ordering orderingDecl;

		result = new ArrayList();
	}
	#( ORDERSPECS
       (
         orderingDecl=orderSpecification
         {
      	    result.add(orderingDecl);
         }
       )*
    )
    ;

orderSpecification returns [Ordering orderingDecl]:
	{
		Expression expr = null;
		boolean    isAscending;
	}
	#( o:ORDERSPEC expr=expression
      (
        LITERAL_ASCENDING
        {
      	    isAscending = true;
        }
      | LITERAL_DESCENDING
        {
      	    isAscending = false;
        }
      )
    )
	{
		orderingDecl = new Ordering(expr, isAscending);
		orderingDecl.setPosition(o.getLine(), o.getColumn());
	}
    ;

// 23.3 Filter Expression

// This is a top-level rule
expression returns [Expression expr]:
    (
      expr=literal
    | expr=nullLiteral
    | expr=thisExpression
    | expr=binaryExpression
    | expr=unaryExpression
    | expr=methodInvocation
    | expr=nameExpression
    | expr=postfixExpression
    )
    ;

literal returns [Literal literal]:
	(
	  l1:LITERAL_TRUE
	  {
	  	  literal = new Literal(boolean.class, l1.getText());
		  literal.setPosition(l1.getLine(), l2.getColumn());
	  }
    | l2:LITERAL_FALSE
	  {
	  	  literal = new Literal(boolean.class, l2.getText());
		  literal.setPosition(l2.getLine(), l2.getColumn());
	  }
    | l3:INTEGER_LITERAL
	  {
	  	  literal = new Literal(long.class, l3.getText());
		  literal.setPosition(l3.getLine(), l3.getColumn());
	  }
    | l4:FLOATING_POINT_LITERAL
	  {
	  	  literal = new Literal(double.class, l4.getText());
		  literal.setPosition(l4.getLine(), l4.getColumn());
	  }
    | l5:CHARACTER_LITERAL
	  {
	  	  literal = new Literal(char.class, l5.getText());
		  literal.setPosition(l5.getLine(), l5.getColumn());
	  }
    | l6:STRING_LITERAL
	  {
	  	  literal = new Literal(String.class, l6.getText());
		  literal.setPosition(l6.getLine(), l6.getColumn());
	  }
	)
	;

nullLiteral returns [NullLiteral literal]:
    l:LITERAL_NULL
	{
	  	literal = new NullLiteral();
		literal.setPosition(l.getLine(), l.getColumn());
	}
	;

thisExpression returns [ThisExpression expr]:
    l:LITERAL_THIS
    {
        expr = new ThisExpression();
		expr.setPosition(l.getLine(), l.getColumn());
    }
    ;

methodInvocation returns [MethodInvocation methodInvoc]:
	{
		List args = null;
	}
	#( m:METHOD_INVOCATION args=argList )
	{
		methodInvoc = new MethodInvocation(null, m.getText(), args);
		methodInvoc.setPosition(m.getLine(), m.getColumn());
	}
	;

argList returns [List args]:
	{
		Expression argExpr = null;

		args = new ArrayList();
	}
	#( ARG_LIST
	   (
	     argExpr=expression
	     {
       	     args.add(argExpr);
         }
	   )*
	)
	;

nameExpression returns [NameExpression nameExpr]:
	expr:NAME_EXPRESSION
	{
		nameExpr = new NameExpression(null, expr.getText());
	    nameExpr.setPosition(expr.getLine(), expr.getColumn());
	}
	;

postfixExpression returns [PostfixExpression expr]:
	{
		Expression        baseExpr     = null;
		PostfixExpression trailingExpr = null;
	}
	#( SEP_DOT baseExpr=expression ( trailingExpr=nameExpression | trailingExpr=methodInvocation ) )
	{
		expr = trailingExpr;
		expr.setBaseExpression(baseExpr);
	}
	;

unaryExpression returns [UnaryExpression expr]:
	{
		Expression inner    = null;
		Type       castType = null;
	}
	(
	  #( o1:OP_UNARY_MINUS inner=expression )
	  {
	  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_MINUS, inner);
		  expr.setPosition(o1.getLine(), o1.getColumn());
	  }
	| #( o2:OP_UNARY_PLUS inner=expression )
	  {
	  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_PLUS, inner);
		  expr.setPosition(o2.getLine(), o2.getColumn());
	  }
	| #( o3:OP_BITWISE_COMPLEMENT inner=expression )
	  {
	  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_BITWISE_COMPLEMENT, inner);
		  expr.setPosition(o3.getLine(), o3.getColumn());
	  }
	| #( o4:OP_NOT inner=expression )
	  {
	  	  expr = new UnaryExpression(UnaryExpression.OPERATOR_NOT, inner);
		  expr.setPosition(o4.getLine(), o4.getColumn());
	  }
	| #( o5:CAST castType=type inner=expression )
	  {
	  	  expr = new UnaryExpression(castType, inner);
		  expr.setPosition(o5.getLine(), o5.getColumn());
	  }
	)
    ;

binaryExpression returns [BinaryExpression expr]:
	{
		Expression left  = null;
		Expression right = null;
	}
	(
	  #( o1:OP_MULTIPLY left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_MULTIPLY, right);
		  expr.setPosition(o1.getLine(), o1.getColumn());
	  }
	| #( o2:OP_DIVIDE left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_DIVIDE, right);
		  expr.setPosition(o2.getLine(), o2.getColumn());
	  }
	| #( o3:OP_BINARY_PLUS left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_PLUS, right);
		  expr.setPosition(o3.getLine(), o3.getColumn());
	  }
	| #( o4:OP_BINARY_MINUS left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_MINUS, right);
		  expr.setPosition(o4.getLine(), o4.getColumn());
	  }
	| #( o5:OP_LOWER left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_LOWER, right);
		  expr.setPosition(o5.getLine(), o5.getColumn());
	  }
	| #( o6:OP_GREATER left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_GREATER, right);
		  expr.setPosition(o6.getLine(), o6.getColumn());
	  }
	| #( o7:OP_LOWER_OR_EQUAL left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_LOWER_OR_EQUAL, right);
		  expr.setPosition(o7.getLine(), o7.getColumn());
	  }
	| #( o8:OP_GREATER_OR_EQUAL left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_GREATER_OR_EQUAL, right);
		  expr.setPosition(o8.getLine(), o8.getColumn());
	  }
	| #( o9:OP_EQUAL left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_EQUAL, right);
		  expr.setPosition(o9.getLine(), o9.getColumn());
	  }
	| #( o10:OP_NOT_EQUAL left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_NOT_EQUAL, right);
		  expr.setPosition(o10.getLine(), o10.getColumn());
	  }
	| #( o11:OP_BITWISE_AND left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_AND, right);
		  expr.setPosition(o11.getLine(), o11.getColumn());
	  }
	| #( o12:OP_BITWISE_XOR left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_XOR, right);
		  expr.setPosition(o12.getLine(), o12.getColumn());
	  }
	| #( o13:OP_BITWISE_OR left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_BITWISE_OR, right);
		  expr.setPosition(o13.getLine(), o13.getColumn());
	  }
	| #( o14:OP_AND left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_AND, right);
		  expr.setPosition(o14.getLine(), o14.getColumn());
	  }
	| #( o15:OP_OR left=expression right=expression )
	  {
	  	  expr = new BinaryExpression(left, BinaryExpression.OPERATOR_OR, right);
		  expr.setPosition(o15.getLine(), o15.getColumn());
	  }
	)
	;

type returns [Type result]:
	(
	  primitiveTypeName:PRIMITIVE_TYPE
	  {
	      result = new Type(primitiveTypeName.getText(), true);
	  	  result.setPosition(primitiveTypeName.getLine(), primitiveTypeName.getColumn());
	  }
	| typeName:NAME
	  {
	      result = new Type(typeName.getText(), false);
	  	  result.setPosition(typeName.getLine(), typeName.getColumn());
	  }
	)
	;