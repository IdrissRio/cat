package AST; // The generated parser will belong to package AST

import AST.StateMachineParser.Terminals; // The terminals are implicitly defined in the parser

%%

// define the signature for the generated scanner
%public
%final
%class StateMachineScanner 
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%type beaver.Symbol 
%function nextToken 
%yylexthrow beaver.Scanner.Exception

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

WhiteSpace = [ ] | \t | \f | \n | \r | \r\n
Identifier = [:jletter:][:jletterdigit:]*

%%

// discard whitespace information
{WhiteSpace}  { }

// token definitions
"state"       { return sym(Terminals.STATE); }
"trans"       { return sym(Terminals.TRANS); }
{Identifier}  { return sym(Terminals.IDENTIFIER); }
";"           { return sym(Terminals.SEMI); }
":"           { return sym(Terminals.COLON); }
"->"          { return sym(Terminals.ARROW); }
<<EOF>>       { return sym(Terminals.EOF); }
