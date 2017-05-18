grammar Filter ;

r
    : filter
    | pair
    ;


pair
    : not? IDENT '=' VALUE ':'
    ;

filter
  : '(' filtercomp ')'
  ;

filtercomp
  : and
  | or
  | not
  | item
  ;

and
  : '&' filterlist
  ;

or
  : '|' filterlist
  ;

not
  : '!' filterlist
  ;

filterlist
  : filter+
  ;

item
  : substring
  | simple
  | present
  ;


simple
  : IDENT filtertype VALUE
  ;

present
  : IDENT '=*'
  ;


substring
  : IDENT equal VALUE
  ;

filtertype
  : equal
  | approx
  | greater
  | less
  ;

filter
  :  '(' filtercomp filter+ ')'
  ;

filterlist : filter+ ;





filter
    : '(' op filter+ ')'
    | '(' pair ')'
    ;

not
    : '!'
    ;

op
    : '|'
    | not
    | '&'
    | '>'
    | '<'
    | '>='
    | '<='
    ;


IDENT
    : ('_' | 'a'..'z'| 'A'..'Z' | '\u0100'..'\ufffe' )
      ('_' | '-' | 'a'..'z'| 'A'..'Z' | '\u0100'..'\ufffe' | '0'..'9')*
    ;


VALUE
	: ( ESC | . )*
    ;


STRING
   : '"' (ESC | ~ ["\\])* '"'
   ;

fragment MATCHER
    : '*'
    ;

/*

(	\28	 	)	\29	 	&	\26	 	|	\7c	 	=	\3d
>	\3e	 	<	\3c	 	~	\7e	 	*	\2a	 	/	\2f
\	\5c
*/

fragment ESC
    : '\\' ['28''29''26''7c''3d''3e''3c''73''2a''2f''5c']
    ;

/*
fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
*/

fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;

fragment HEX
   : [0-9a-fA-F]
   ;


WS
   : [ \t\n\r] + -> skip
   ;