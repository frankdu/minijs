grammar JavaScript;

@header {
}

program
    :   statement+
    ;

variableDeclarators
    :   'var'? variableDeclarator (COMMA variableDeclarator)*
    ;


variableDeclarator
    :   IDENTIFIER ('=' variableInitializer)?
    ;

variableInitializer
    :   expression
    ;


statement
    :   SEMI
    |   'break'
    |   'continue'
    |   expression
    |   variableDeclarators
    |   '{' statement* '}'
    |   'if' parenthesizedExpression statement
        'for' '(' forControl ')' statement
    |   'while' parenthesizedExpression statement
    |   'do' statement 'while' parenthesizedExpression
    |   'return' expression
    ;

forControl
    :   forInit? ';' expression? ';' forUpdate?
    ;

forInit
    :   variableDeclarators
    ;

forUpdate
    :   expressionList
    ;

parenthesizedExpression
    :   LPAREN expression RPAREN
    ;

expression
    :   primaryExpression
    |   ('+' | '-' | INC | DEC) expression        
    |   NOT expression
    |   expression '.' IDENTIFIER
    |   expression BINARY_OPEATOR expression
    |   expression '[' expression ']'
    |   expression '(' expressionList? ')'
    |   expression (INC | DEC)
    |   expression '?' expression ':' expression
    ;

expressionList
    :   expression (',' expression)*
    ;

primaryExpression
    :   IDENTIFIER
    |   literal
    |   parenthesizedExpression
    |   arrayLiteral
    ;

arrayLiteral
    :   '[' expressionList? ']'
    ;

literal
    :   NUMBER
    |   BOOLEAN_LITERAL
    |   NULL_LITERAL
    |   UNDEFINED_LITERAL
    |   STRING_LITERAL
    ;

BOOLEAN_LITERAL
    :   'true'
    |   'false'
    ;

STRING_LITERAL
    :   '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    |   '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    ;

UNDEFINED_LITERAL
    :   'undefined'
    ;

NULL_LITERAL
    :   'null'
    ;

IDENTIFIER
    :   [a-zA-Z$_] [a-zA-Z0-9$_]*
    ;

NUMBER
    :   SIGN? INT+ ('.' INT+)? EXPONENT?
    ;

BINARY_OPEATOR
    :   ADD
    |   SUB
    |   MUL
    |   DIV
    |   MOD
    |   AND
    |   OR
    |   GT
    |   LT
    |   GE
    |   LE
    |   EQ
    |   NOTEQUAL
    ;

// Keywords
VAR:        'var';
IF:         'if';
WHILE:      'while';
DO:         'do';
FOR:        'for';
CONTINUE:   'continue';
BREAK:      'break';
RETURN:     'return';
FUNCTION:   'function';

// Separators
LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';
QUESTION        : '?';
COLON           : ':';

// Operators
ADD:        '+';
SUB:        '-';
MUL:        '*';
DIV:        '/';
MOD:        '%';
ASSIGN:     '=';
AND:        '&&';
OR:         '||';
GT:         '>';
LT:         '<';
GE:         '>=';
LE:         '<=';
EQ:         '==';
NOTEQUAL:   '!=';
NOT:        '!';
INC:        '++';
DEC:        '--';


fragment
SIGN
    :   [+-]
    ;

fragment
INT
    :   [0-9]+
    ;

fragment
EXPONENT
    : ('e'|'E') ('+'|'-')? [0-9]+
    ;

fragment
ESC_SEQ
    : '\\' ('\"'|'\\'|'/'|'b'|'f'|'n'|'r'|'t')
    | UNICODE_ESC
    ;

fragment
UNICODE_ESC
    : '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

fragment
HEX_DIGIT
    : [0-9a-fA-F]
    ;

//
// Whitespace and comments
//

WS
    :   [ \t\r\n]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;