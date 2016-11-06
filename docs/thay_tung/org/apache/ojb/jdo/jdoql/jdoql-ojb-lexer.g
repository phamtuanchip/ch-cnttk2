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

class JDOQLLexer extends Lexer;

options
{
    k                   = 2;
    exportVocab         = JDOQL;
    // we ignore the fact that the text could contain the unicode EOF (0xffff)
    charVocabulary      = '\u0000'..'\uFFFE';
    // We test the keywords only when necessary (identifier rule)
	testLiterals        = false;
	defaultErrorHandler = false;
}

tokens
{
	// parser tokens
	PARAMETERS;
	PARAMETER;
	VARIABLES;
	VARIABLE;
	IMPORTS;
	IMPORT;
	ON_DEMAND_IMPORT;
	ORDERSPECS;
	ORDERSPEC;
	EXPR;
	CAST;
	METHOD_INVOCATION;
	ARG_LIST;
	NAME_EXPRESSION;
	PRIMITIVE_TYPE;
	NAME;
    LITERAL_IMPORT = "import";
    LITERAL_ASCENDING = "ascending";
    LITERAL_DESCENDING = "descending";
	LITERAL_TRUE = "true";
    LITERAL_FALSE = "false";
    LITERAL_NULL = "null";
    LITERAL_THIS = "this";
	LITERAL_BOOLEAN = "boolean";
	LITERAL_BYTE = "byte";
	LITERAL_SHORT = "short";
	LITERAL_INT = "int";
	LITERAL_LONG = "long";
	LITERAL_CHAR = "char";
	LITERAL_FLOAT = "float";
	LITERAL_DOUBLE = "double";


	// imaginary lexer tokens (without a rule)
	OP_GREATER_OR_EQUAL;
	OP_LOWER_OR_EQUAL;
	OP_NOT_EQUAL;
	OP_AND;
	OP_OR;
	OP_UNARY_MINUS;
	OP_UNARY_PLUS;
	SEP_DOT;
    FLOATING_POINT_LITERAL;
    INTEGER_LITERAL;
}

SEP_OPENING_PARENTHESIS :
    '('
    ;

SEP_CLOSING_PARENTHESIS :
    ')'
    ;

SEP_SEMICOLON :
    ';'
    ;

SEP_COMMA :
    ','
    ;

OP_EQUAL :
    "=="
    ;

OP_GREATER :
    '>'
    (
      '='
      {
          $setType(OP_GREATER_OR_EQUAL);
      }
    )?
    ;

OP_LOWER :
    '<'
    (
      '='
      {
          $setType(OP_LOWER_OR_EQUAL);
      }
    )?
    ;

OP_NOT :
    '!'
    ( '='
      {
          $setType(OP_NOT_EQUAL);
      }
    )?
    ;

OP_BITWISE_COMPLEMENT :
    '~'
    ;

OP_BITWISE_AND :
    '&'
    (
      '&'
      {
          $setType(OP_AND);
      }
    )?
    ;

OP_BITWISE_OR :
    '|'
    (
      '|'
      {
          $setType(OP_OR);
      }
    )?
    ;

OP_BITWISE_XOR :
    '^'
	;

OP_BINARY_PLUS :
    '+'
    ;

OP_BINARY_MINUS :
    '-'
    ;

OP_MULTIPLY :
    '*'
    ;

OP_DIVIDE :
	'/'
	;

WHITESPACE :
    (
      ' '
    | '\t'
    | '\f'
    | LINE_TERMINATOR
    )+
    {
        $setType(Token.SKIP);
	}
    ;

IDENTIFIER
	options
	{
	    testLiterals = true;
	}
    :
    IDENTIFIER_START ( IDENTIFIER_PART )*
    ;

CHARACTER_LITERAL :
    '\''!
    (
      ~( '\'' | '\\' | '\n' | '\r' )
    | ESCAPE_SEQUENCE
    )
    '\''!
    ;

STRING_LITERAL :
    '"'!
    (
      ~( '"' | '\\' | '\n' | '\r' )
    | ESCAPE_SEQUENCE
    )*
    '"'!
    ;

INT_OR_FLOAT_LITERAL_OR_DOT
{
    boolean canBeOctal = true;
}
    :
    {
        // initially we assume an int
        $setType(INTEGER_LITERAL);
    }
    (
      '0'
      (
        ( 'x' | 'X' ) ( HEX_DIGIT )+ ( INTEGER_TYPE_SUFFIX )?
      | (
          OCTAL_DIGIT
        | '8'..'9' { canBeOctal = false; }
        )*
        (
          FLOAT_TYPE_SUFFIX
          {
              $setType(FLOATING_POINT_LITERAL);
          }
        | '.' ( DIGIT )* ( EXPONENT_PART )? ( FLOAT_TYPE_SUFFIX )?
          {
              $setType(FLOATING_POINT_LITERAL);
          }
        | EXPONENT_PART ( FLOAT_TYPE_SUFFIX )?
          {
              $setType(FLOATING_POINT_LITERAL);
          }
        | { canBeOctal }? ( INTEGER_TYPE_SUFFIX )?
        )
      )
    | '.'
      ( ( DIGIT )+ ( EXPONENT_PART )? ( FLOAT_TYPE_SUFFIX )?
        {
            $setType(FLOATING_POINT_LITERAL);
        }
      |
        {
            $setType(SEP_DOT);
        }
      )
    | '1'..'9' ( DIGIT )*
      (
        INTEGER_TYPE_SUFFIX
      | FLOAT_TYPE_SUFFIX
        {
            $setType(FLOATING_POINT_LITERAL);
        }
      | '.' ( DIGIT )* ( EXPONENT_PART )? ( FLOAT_TYPE_SUFFIX )?
        {
            $setType(FLOATING_POINT_LITERAL);
        }
      | EXPONENT_PART ( FLOAT_TYPE_SUFFIX )?
        {
            $setType(FLOATING_POINT_LITERAL);
        }
      )?
    )
    ;

// helper rules

protected LINE_TERMINATOR :
    ( ( '\r' )? '\n' )
    {
        newline();
        $setType(Token.SKIP);
    }
    ;

protected ESCAPE_SEQUENCE :
    (
      '\\'
      (
        '"'
      | '\''
      | '\\'
      | 'b'
      | 'f'
      | 'n'
      | 'r'
      | 't'
      )
    )
    | UNICODE_ESCAPE
    | OCTAL_ESCAPE
    ;

// unicode escape sequences are handled in the lexer
protected UNICODE_ESCAPE :
    '\\' ( 'u' )+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

protected OCTAL_ESCAPE :
    '\\'
    (
      '0'..'3'
      (
        options
        {
            warnWhenFollowAmbig = false;
        } :
        '0'..'7'
        (
          options
          {
              warnWhenFollowAmbig = false;
          } :
          '0'..'7'
        )?
      )?
    | '4'..'7'
      (
        options
        {
            warnWhenFollowAmbig = false;
        } :
        '0'..'7'
      )?
    )
    ;

protected OCTAL_DIGIT :
    '0'..'7'
    ;

protected DIGIT :
    '0'..'9'
    ;

protected HEX_DIGIT :
    ( '0'..'9' | 'a'..'f' | 'A'..'F' )
    ;

protected EXPONENT_PART :
    ( 'e' | 'E' ) ( '+' | '-' )? ( DIGIT )+
    ;

protected INTEGER_TYPE_SUFFIX :
    ( 'l' | 'L' )
    ;

protected FLOAT_TYPE_SUFFIX :
    ( 'f' | 'F' | 'd' | 'D' )
    ;

// These character codes were determined using the Character.isIdentifierStart method
// These are fast enough (antlr generates bitsets for them), though could perhaps be replaced
// by something easier ?
protected IDENTIFIER_START :
    (
      '\u0024'
    | '\u0041'..'\u005a'
    | '\u005f'
    | '\u0061'..'\u007a'
    | '\u00a2'..'\u00a5'
    | '\u00aa'
    | '\u00b5'
    | '\u00ba'
    | '\u00c0'..'\u00d6'
    | '\u00d8'..'\u00f6'
    | '\u00f8'..'\u01f5'
    | '\u01fa'..'\u0217'
    | '\u0250'..'\u02a8'
    | '\u02b0'..'\u02b8'
    | '\u02bb'..'\u02c1'
    | '\u02d0'..'\u02d1'
    | '\u02e0'..'\u02e4'
    | '\u037a'
    | '\u0386'
    | '\u0388'..'\u038a'
    | '\u038c'
    | '\u038e'..'\u03a1'
    | '\u03a3'..'\u03ce'
    | '\u03d0'..'\u03d6'
    | '\u03da'
    | '\u03dc'
    | '\u03de'
    | '\u03e0'
    | '\u03e2'..'\u03f3'
    | '\u0401'..'\u040c'
    | '\u040e'..'\u044f'
    | '\u0451'..'\u045c'
    | '\u045e'..'\u0481'
    | '\u0490'..'\u04c4'
    | '\u04c7'..'\u04c8'
    | '\u04cb'..'\u04cc'
    | '\u04d0'..'\u04eb'
    | '\u04ee'..'\u04f5'
    | '\u04f8'..'\u04f9'
    | '\u0531'..'\u0556'
    | '\u0559'
    | '\u0561'..'\u0587'
    | '\u05d0'..'\u05ea'
    | '\u05f0'..'\u05f2'
    | '\u0621'..'\u063a'
    | '\u0640'..'\u064a'
    | '\u0671'..'\u06b7'
    | '\u06ba'..'\u06be'
    | '\u06c0'..'\u06ce'
    | '\u06d0'..'\u06d3'
    | '\u06d5'
    | '\u06e5'..'\u06e6'
    | '\u0905'..'\u0939'
    | '\u093d'
    | '\u0958'..'\u0961'
    | '\u0985'..'\u098c'
    | '\u098f'..'\u0990'
    | '\u0993'..'\u09a8'
    | '\u09aa'..'\u09b0'
    | '\u09b2'
    | '\u09b6'..'\u09b9'
    | '\u09dc'..'\u09dd'
    | '\u09df'..'\u09e1'
    | '\u09f0'..'\u09f3'
    | '\u0a05'..'\u0a0a'
    | '\u0a0f'..'\u0a10'
    | '\u0a13'..'\u0a28'
    | '\u0a2a'..'\u0a30'
    | '\u0a32'..'\u0a33'
    | '\u0a35'..'\u0a36'
    | '\u0a38'..'\u0a39'
    | '\u0a59'..'\u0a5c'
    | '\u0a5e'
    | '\u0a72'..'\u0a74'
    | '\u0a85'..'\u0a8b'
    | '\u0a8d'
    | '\u0a8f'..'\u0a91'
    | '\u0a93'..'\u0aa8'
    | '\u0aaa'..'\u0ab0'
    | '\u0ab2'..'\u0ab3'
    | '\u0ab5'..'\u0ab9'
    | '\u0abd'
    | '\u0ae0'
    | '\u0b05'..'\u0b0c'
    | '\u0b0f'..'\u0b10'
    | '\u0b13'..'\u0b28'
    | '\u0b2a'..'\u0b30'
    | '\u0b32'..'\u0b33'
    | '\u0b36'..'\u0b39'
    | '\u0b3d'
    | '\u0b5c'..'\u0b5d'
    | '\u0b5f'..'\u0b61'
    | '\u0b85'..'\u0b8a'
    | '\u0b8e'..'\u0b90'
    | '\u0b92'..'\u0b95'
    | '\u0b99'..'\u0b9a'
    | '\u0b9c'
    | '\u0b9e'..'\u0b9f'
    | '\u0ba3'..'\u0ba4'
    | '\u0ba8'..'\u0baa'
    | '\u0bae'..'\u0bb5'
    | '\u0bb7'..'\u0bb9'
    | '\u0c05'..'\u0c0c'
    | '\u0c0e'..'\u0c10'
    | '\u0c12'..'\u0c28'
    | '\u0c2a'..'\u0c33'
    | '\u0c35'..'\u0c39'
    | '\u0c60'..'\u0c61'
    | '\u0c85'..'\u0c8c'
    | '\u0c8e'..'\u0c90'
    | '\u0c92'..'\u0ca8'
    | '\u0caa'..'\u0cb3'
    | '\u0cb5'..'\u0cb9'
    | '\u0cde'
    | '\u0ce0'..'\u0ce1'
    | '\u0d05'..'\u0d0c'
    | '\u0d0e'..'\u0d10'
    | '\u0d12'..'\u0d28'
    | '\u0d2a'..'\u0d39'
    | '\u0d60'..'\u0d61'
    | '\u0e01'..'\u0e2e'
    | '\u0e30'
    | '\u0e32'..'\u0e33'
    | '\u0e3f'..'\u0e46'
    | '\u0e81'..'\u0e82'
    | '\u0e84'
    | '\u0e87'..'\u0e88'
    | '\u0e8a'
    | '\u0e8d'
    | '\u0e94'..'\u0e97'
    | '\u0e99'..'\u0e9f'
    | '\u0ea1'..'\u0ea3'
    | '\u0ea5'
    | '\u0ea7'
    | '\u0eaa'..'\u0eab'
    | '\u0ead'..'\u0eae'
    | '\u0eb0'
    | '\u0eb2'..'\u0eb3'
    | '\u0ebd'
    | '\u0ec0'..'\u0ec4'
    | '\u0ec6'
    | '\u0edc'..'\u0edd'
    | '\u0f40'..'\u0f47'
    | '\u0f49'..'\u0f69'
    | '\u10a0'..'\u10c5'
    | '\u10d0'..'\u10f6'
    | '\u1100'..'\u1159'
    | '\u115f'..'\u11a2'
    | '\u11a8'..'\u11f9'
    | '\u1e00'..'\u1e9b'
    | '\u1ea0'..'\u1ef9'
    | '\u1f00'..'\u1f15'
    | '\u1f18'..'\u1f1d'
    | '\u1f20'..'\u1f45'
    | '\u1f48'..'\u1f4d'
    | '\u1f50'..'\u1f57'
    | '\u1f59'
    | '\u1f5b'
    | '\u1f5d'
    | '\u1f5f'..'\u1f7d'
    | '\u1f80'..'\u1fb4'
    | '\u1fb6'..'\u1fbc'
    | '\u1fbe'
    | '\u1fc2'..'\u1fc4'
    | '\u1fc6'..'\u1fcc'
    | '\u1fd0'..'\u1fd3'
    | '\u1fd6'..'\u1fdb'
    | '\u1fe0'..'\u1fec'
    | '\u1ff2'..'\u1ff4'
    | '\u1ff6'..'\u1ffc'
    | '\u203f'..'\u2040'
    | '\u207f'
    | '\u20a0'..'\u20ac'
    | '\u2102'
    | '\u2107'
    | '\u210a'..'\u2113'
    | '\u2115'
    | '\u2118'..'\u211d'
    | '\u2124'
    | '\u2126'
    | '\u2128'
    | '\u212a'..'\u2131'
    | '\u2133'..'\u2138'
    | '\u2160'..'\u2182'
    | '\u3005'
    | '\u3007'
    | '\u3021'..'\u3029'
    | '\u3031'..'\u3035'
    | '\u3041'..'\u3094'
    | '\u309b'..'\u309e'
    | '\u30a1'..'\u30fa'
    | '\u30fc'..'\u30fe'
    | '\u3105'..'\u312c'
    | '\u3131'..'\u318e'
    | '\u4e00'..'\u9fa5'
    | '\uac00'..'\ud7a3'
    | '\uf900'..'\ufa2d'
    | '\ufb00'..'\ufb06'
    | '\ufb13'..'\ufb17'
    | '\ufb1f'..'\ufb28'
    | '\ufb2a'..'\ufb36'
    | '\ufb38'..'\ufb3c'
    | '\ufb3e'
    | '\ufb40'..'\ufb41'
    | '\ufb43'..'\ufb44'
    | '\ufb46'..'\ufbb1'
    | '\ufbd3'..'\ufd3d'
    | '\ufd50'..'\ufd8f'
    | '\ufd92'..'\ufdc7'
    | '\ufdf0'..'\ufdfb'
    | '\ufe33'..'\ufe34'
    | '\ufe4d'..'\ufe4f'
    | '\ufe69'
    | '\ufe70'..'\ufe72'
    | '\ufe74'
    | '\ufe76'..'\ufefc'
    | '\uff04'
    | '\uff21'..'\uff3a'
    | '\uff3f'
    | '\uff41'..'\uff5a'
    | '\uff66'..'\uffbe'
    | '\uffc2'..'\uffc7'
    | '\uffca'..'\uffcf'
    | '\uffd2'..'\uffd7'
    | '\uffda'..'\uffdc'
    | '\uffe0'..'\uffe1'
    | '\uffe5'..'\uffe6'
    )
    ;

// These character codes were determined using the Character.isIdentifierPart method
protected IDENTIFIER_PART :
      IDENTIFIER_START
    | (
      '\u0000'..'\u0008'
    | '\u000e'..'\u001b'
    | '\u0030'..'\u0039'
    | '\u007f'..'\u009f'
    | '\u0300'..'\u0345'
    | '\u0360'..'\u0361'
    | '\u0483'..'\u0486'
    | '\u0591'..'\u05a1'
    | '\u05a3'..'\u05b9'
    | '\u05bb'..'\u05bd'
    | '\u05bf'
    | '\u05c1'..'\u05c2'
    | '\u05c4'
    | '\u064b'..'\u0652'
    | '\u0660'..'\u0669'
    | '\u0670'
    | '\u06d6'..'\u06dc'
    | '\u06df'..'\u06e4'
    | '\u06e7'..'\u06e8'
    | '\u06ea'..'\u06ed'
    | '\u06f0'..'\u06f9'
    | '\u0901'..'\u0903'
    | '\u093c'
    | '\u093e'..'\u094d'
    | '\u0951'..'\u0954'
    | '\u0962'..'\u0963'
    | '\u0966'..'\u096f'
    | '\u0981'..'\u0983'
    | '\u09bc'
    | '\u09be'..'\u09c4'
    | '\u09c7'..'\u09c8'
    | '\u09cb'..'\u09cd'
    | '\u09d7'
    | '\u09e2'..'\u09e3'
    | '\u09e6'..'\u09ef'
    | '\u0a02'
    | '\u0a3c'
    | '\u0a3e'..'\u0a42'
    | '\u0a47'..'\u0a48'
    | '\u0a4b'..'\u0a4d'
    | '\u0a66'..'\u0a71'
    | '\u0a81'..'\u0a83'
    | '\u0abc'
    | '\u0abe'..'\u0ac5'
    | '\u0ac7'..'\u0ac9'
    | '\u0acb'..'\u0acd'
    | '\u0ae6'..'\u0aef'
    | '\u0b01'..'\u0b03'
    | '\u0b3c'
    | '\u0b3e'..'\u0b43'
    | '\u0b47'..'\u0b48'
    | '\u0b4b'..'\u0b4d'
    | '\u0b56'..'\u0b57'
    | '\u0b66'..'\u0b6f'
    | '\u0b82'..'\u0b83'
    | '\u0bbe'..'\u0bc2'
    | '\u0bc6'..'\u0bc8'
    | '\u0bca'..'\u0bcd'
    | '\u0bd7'
    | '\u0be7'..'\u0bef'
    | '\u0c01'..'\u0c03'
    | '\u0c3e'..'\u0c44'
    | '\u0c46'..'\u0c48'
    | '\u0c4a'..'\u0c4d'
    | '\u0c55'..'\u0c56'
    | '\u0c66'..'\u0c6f'
    | '\u0c82'..'\u0c83'
    | '\u0cbe'..'\u0cc4'
    | '\u0cc6'..'\u0cc8'
    | '\u0cca'..'\u0ccd'
    | '\u0cd5'..'\u0cd6'
    | '\u0ce6'..'\u0cef'
    | '\u0d02'..'\u0d03'
    | '\u0d3e'..'\u0d43'
    | '\u0d46'..'\u0d48'
    | '\u0d4a'..'\u0d4d'
    | '\u0d57'
    | '\u0d66'..'\u0d6f'
    | '\u0e31'
    | '\u0e34'..'\u0e3a'
    | '\u0e47'..'\u0e4e'
    | '\u0e50'..'\u0e59'
    | '\u0eb1'
    | '\u0eb4'..'\u0eb9'
    | '\u0ebb'..'\u0ebc'
    | '\u0ec8'..'\u0ecd'
    | '\u0ed0'..'\u0ed9'
    | '\u0f18'..'\u0f19'
    | '\u0f20'..'\u0f29'
    | '\u0f35'
    | '\u0f37'
    | '\u0f39'
    | '\u0f3e'..'\u0f3f'
    | '\u0f71'..'\u0f84'
    | '\u0f86'..'\u0f8b'
    | '\u0f90'..'\u0f95'
    | '\u0f97'
    | '\u0f99'..'\u0fad'
    | '\u0fb1'..'\u0fb7'
    | '\u0fb9'
    | '\u200c'..'\u200f'
    | '\u202a'..'\u202e'
    | '\u206a'..'\u206f'
    | '\u20d0'..'\u20dc'
    | '\u20e1'
    | '\u302a'..'\u302f'
    | '\u3099'..'\u309a'
    | '\ufb1e'
    | '\ufe20'..'\ufe23'
    | '\ufeff'
    | '\uff10'..'\uff19'
    )
    ;
