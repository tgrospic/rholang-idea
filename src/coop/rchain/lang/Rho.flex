package coop.rchain.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static coop.rchain.lang.psi.RhoTypes.*;

%%

%{
  public _RhoLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _RhoLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SPACE=[ \t\n\x0B\f\r]+
COMMENT="//".*
INTEGER=[0-9]+(\.[0-9]*)?
ID_NAME=([a-zA-Z'][a-zA-Z_0-9']*)|([_a-zA-Z0-9']+)
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
URI=`([^`\\]|\\[`\\])*`

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "contract"         { return CONTRACT; }
  "select"           { return SELECT; }
  "match"            { return MATCH; }
  "total"            { return TOTAL; }
  "case"             { return CASE; }
  "with"             { return WITH; }
  "def"              { return DEF; }
  "for"              { return FOR; }
  "let"              { return LET; }
  "new"              { return NEW; }
  "sum"              { return SUM; }
  "if"               { return IF; }
  "in"               { return IN; }
  "false"            { return FALSE; }
  "true"             { return TRUE; }
  "Nil"              { return NIL; }
  "=>"               { return FAT_ARROW; }
  "->"               { return THIN_ARROW; }
  "."                { return DOT; }
  "&"                { return BITWISE_AND; }
  "|"                { return BITWISE_OR; }
  "^"                { return BITWISE_XOR; }
  "..."              { return TRIPLE_DOT; }
  ".."               { return DOUBLE_DOT; }
  "::"               { return DOUBLE_COLON; }
  ":"                { return COLON; }
  "+"                { return PLUS; }
  "-"                { return MINUS; }
  "*"                { return MULTIPLY; }
  "/"                { return DIVIDE; }
  "%"                { return REMAINDER; }
  "<<="              { return ASSIGN_LEFT_SHIFT; }
  ">>="              { return ASSIGN_RIGHT_SHIFT; }
  "=="               { return EQUAL; }
  "="                { return ASSIGN; }
  "!="               { return NOT_EQUAL; }
  "<="               { return LESS_THAN_OR_EQUAL; }
  "<"                { return LESS_THAN; }
  ">="               { return GREATER_THAN_OR_EQUAL; }
  ">"                { return GREATER_THAN; }
  "!"                { return NOT; }
  "~"                { return BOX; }
  "@"                { return AT; }
  "'"                { return SINGLE_QUOTE; }
  "$"                { return DOLLAR; }
  "#"                { return HASH; }
  "["                { return OPEN_SQUARE_BRACKET; }
  "]"                { return CLOSE_SQUARE_BRACKET; }
  "("                { return OPEN_PAREN; }
  ")"                { return CLOSE_PAREN; }
  "{"                { return OPEN_BRACE; }
  "}"                { return CLOSE_BRACE; }
  ","                { return COMMA; }
  ";"                { return SEMICOLON; }
  "_"                { return UNDERSCORE; }
  "BIN_LIT"          { return BIN_LIT; }
  "OCT_LIT"          { return OCT_LIT; }
  "HEX_LIT"          { return HEX_LIT; }
  "DEC_LIT"          { return DEC_LIT; }
  "BLOCK_COMMENT"    { return BLOCK_COMMENT; }
  "LINE_COMMENT"     { return LINE_COMMENT; }

  {SPACE}            { return SPACE; }
  {COMMENT}          { return COMMENT; }
  {INTEGER}          { return INTEGER; }
  {ID_NAME}          { return ID_NAME; }
  {STRING}           { return STRING; }
  {URI}              { return URI; }

}

[^] { return BAD_CHARACTER; }
