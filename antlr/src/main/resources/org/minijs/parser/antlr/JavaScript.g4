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
    :   expression '.' IDENTIFIER                               #propertyExpression
    |   expression '[' expression ']'                           #indexorExpression
    |   'new' IDENTIFIER '(' expressionList? ')'                #newExpression
    |   expression '(' expressionList? ')'                      #functionCallExpression
    |   primaryExpression                                       #primaryExpression2
    |   expression (INC | DEC)                                  #postIncDecExpression
    |   unaryExpression                                         #unaryExpression2
    |   expression ('*' | '/' | '%') expression                 #mulExpression
    |   expression ('+' | '-') expression                       #plusExpression
    |   expression ('<' | '<=' | '>' | '>=' |
                    'in' | 'instanceof') expression             #relationalExpression
    |   expression ('==' | '!=' | '===' | '!==') expression     #loginEqualityExpression
    |   expression '&&' expression                              #loginAndExpression
    |   expression '||' expression                              #logicOrExpression
    |   expression '?' expression ':' expression                #conditionalExpression
    ;

unaryExpression
    :   (INC | DEC | '!' | '~' | '+' | '-' | 'delete') expression
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
NEW:        'new';
DELETE:     'delete';
IN:         'in';
INSTANCEOF: 'instanceof';

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
PLUS:       '+';
MINUS:      '-';
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
NEQ:        '!=';
NOT:        '!';
INC:        '++';
DEC:        '--';
EXACT_EQ:   '===';
EXACT_NEQ:  '!==';
BITWISE_NOT: '~';


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