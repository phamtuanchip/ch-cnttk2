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
}

class JDOQLParser extends Parser;//("antlr.debug.ParseTreeDebugParser");

options
{
    k                   = 2;
    importVocab         = JDOQL;
    buildAST            = true;
    defaultErrorHandler = false;
}

// order follows BNF from the JDO spec (chapter 23)


// 23.2 Parameter Declarations

// This is a top-level rule
declareParameters :
    parameter ( SEP_COMMA! parameter )* ( SEP_COMMA! )?
	{ #declareParameters = #(#[PARAMETERS, "PARAMETERS"], #declareParameters); }
    ;

parameter :
	type identifier
	{ #parameter = #(#[PARAMETER, "PARAMETER"], #parameter); }
    ;


// 23.3 Variable Declarations

// This is a top-level rule
declareVariables :
    variable ( SEP_SEMICOLON! variable )* ( SEP_SEMICOLON! )?
	{ #declareVariables = #(#[VARIABLES, "VARIABLES"], #declareVariables); }
    ;

variable :
    type identifier
	{ #variable = #(#[VARIABLE, "VARIABLE"], #variable); }
    ;


// 23.4 Import Declarations

// This is a top-level rule
declareImports :
    importDeclaration ( SEP_SEMICOLON! importDeclaration )* ( SEP_SEMICOLON! )?
	{ #declareImports = #(#[IMPORTS, "IMPORTS"], #declareImports); }
    ;

importDeclaration :
    node:LITERAL_IMPORT^ name
    { #node.setType(IMPORT); }
    (
      SEP_DOT! OP_MULTIPLY!
      { #node.setType(ON_DEMAND_IMPORT); }
    )?
    ;


// 23.3 Ordering Specifications

// This is a top-level rule
setOrdering :
    orderSpecification ( SEP_COMMA! orderSpecification )* ( SEP_COMMA! )?
	{ #setOrdering = #(#[ORDERSPECS, "ORDERSPECS"], #setOrdering); }
    ;

orderSpecification :
    expression ( LITERAL_ASCENDING | LITERAL_DESCENDING )
	{ #orderSpecification = #(#[ORDERSPEC, "ORDERSPEC"], #orderSpecification); }
    ;


// 23.3 Filter Expression

primary :
    (
      literal
    | LITERAL_THIS^
    | SEP_OPENING_PARENTHESIS! expression SEP_CLOSING_PARENTHESIS!
    | directAccess
    )
    ;

directAccess :
    id:IDENTIFIER^
    ( 
      SEP_OPENING_PARENTHESIS! argList SEP_CLOSING_PARENTHESIS!
      { #directAccess.setType(METHOD_INVOCATION); }
    | /* empty */
      { #directAccess.setType(NAME_EXPRESSION); }
    )
	;

argList	:
	(
	  expression ( SEP_COMMA! expression )*
	  { #argList = #(#[ARG_LIST,"ARG_LIST"], #argList); }
	| /* empty */
	  { #argList = #[ARG_LIST,"ARG_LIST"]; }
	)
	;

postfixExpression :
	primary ( SEP_DOT^ directAccess )*
    ;

unaryExpression :
	(
      op1:OP_BINARY_MINUS^ unaryExpression
      {
      	  #op1.setType(OP_UNARY_MINUS);
      }
    | op2:OP_BINARY_PLUS^ unaryExpression
      {
      	  #op2.setType(OP_UNARY_PLUS);
      }
    | unaryExpressionNotPlusMinus
    )
    ;

unaryExpressionNotPlusMinus :
	(
      OP_BITWISE_COMPLEMENT^ unaryExpression
    | OP_NOT^ unaryExpression
    | ( SEP_OPENING_PARENTHESIS type SEP_CLOSING_PARENTHESIS unaryExpression )=>
      castExpression
    | postfixExpression
    )
    ;

castExpression :
	SEP_OPENING_PARENTHESIS! type SEP_CLOSING_PARENTHESIS! unaryExpression
	{ #castExpression = #(#[CAST, "CAST"], #castExpression); }
	;

multiplicativeExpression :
    unaryExpression ( ( OP_MULTIPLY^ | OP_DIVIDE^ ) unaryExpression )*
    ;

additiveExpression :
    multiplicativeExpression ( ( OP_BINARY_PLUS^ | OP_BINARY_MINUS^ ) multiplicativeExpression )*
    ;

relationalExpression :
    additiveExpression
    (
      (
        OP_LOWER^
      | OP_GREATER^
      | OP_LOWER_OR_EQUAL^
      | OP_GREATER_OR_EQUAL^
      )
      additiveExpression
    )*
    ;

equalityExpression :
    relationalExpression ( ( OP_EQUAL^ | OP_NOT_EQUAL^ ) relationalExpression )*
    ;

andExpression :
    equalityExpression ( OP_BITWISE_AND^ equalityExpression )*
    ;

exclusiveOrExpression :
    andExpression ( OP_BITWISE_XOR^ andExpression )*
    ;

inclusiveOrExpression :
    exclusiveOrExpression ( OP_BITWISE_OR^ exclusiveOrExpression )*
    ;

conditionalAndExpression :
    inclusiveOrExpression ( OP_AND^ inclusiveOrExpression)*
    ;

conditionalOrExpression :
    conditionalAndExpression ( OP_OR^ conditionalAndExpression )*
    ;

// This is a top-level rule
expression :
    conditionalOrExpression 
    ;

// dummy rule to prevent certain problems in the expression
dummyExpressionUsage :
	expression
	;

// 23.7 Types

type :
	primitiveType | name
    ;

// combined with numericType, integralType, floatingPointType
primitiveType :
    (
	  LITERAL_BOOLEAN
	| LITERAL_BYTE
	| LITERAL_SHORT
	| LITERAL_INT
	| LITERAL_LONG
	| LITERAL_CHAR
	| LITERAL_FLOAT
	| LITERAL_DOUBLE
	)
	{ #primitiveType.setType(PRIMITIVE_TYPE); }
	;


// 23.8 Literals

// combined with integerLiteral, floatingPointLiteral, booleanLiteral, characterLiteral, stringLiteral, nullLiteral
literal :
	  LITERAL_TRUE
    | LITERAL_FALSE
    | LITERAL_NULL
    | INTEGER_LITERAL
    | FLOATING_POINT_LITERAL
    | CHARACTER_LITERAL
    | STRING_LITERAL
    ;


// 23.9 Names

// combined with qualifiedName
name :
	identifier
	(
      options { warnWhenFollowAmbig = false; } :
	  dot:SEP_DOT! i:identifier!
	  {
  		  #name.setText(#name.getText() + #dot.getText() + #i.getText());
	  }
	)*
	{ #name.setType(NAME); }
	;

identifier :
	IDENTIFIER
	;