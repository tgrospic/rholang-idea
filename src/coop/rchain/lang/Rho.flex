package coop.rchain.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import coop.rchain.lang.psi.RhoTypes;

%%

%{
  public RhoLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class RhoLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%{
	private int start_comment;
	private int start_raw_string;
	private int raw_string_hashes;
	private int comment_depth;
	private boolean doc_comment;
%}

EOL_WS           = \n | \r | \r\n
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+
HEX_DIGIT = [a-fA-F0-9]
DOUBLE_QUOTE = \x22
SINGLE_QUOTE = \x27
COMMON_ESCAPE = ( [nrt0\n\r\\] | "x" {HEX_DIGIT} {2} | "u" {HEX_DIGIT} {4} | "U" {HEX_DIGIT} {8} )
CHAR = {SINGLE_QUOTE} (( [^'\\] | "\\" ( {SINGLE_QUOTE} | {COMMON_ESCAPE}) ) | [^\x20-\x7E]{1,2}) {SINGLE_QUOTE}
STRING = {DOUBLE_QUOTE} ( [^\"\\] | "\\" ( {DOUBLE_QUOTE} | {SINGLE_QUOTE} | {COMMON_ESCAPE}) )* {DOUBLE_QUOTE}
NUM_SUFFIX = {INT_SUFFIX} | {FLOAT_SUFFIX}
INT_SUFFIX = [ui] ( "8" | "16" | "32" | "64" )?
EXPONENT = [eE] [-+]? ([0-9] | "_" )+
FLOAT_SUFFIX = ( {EXPONENT} | "." [0-9_]+ {EXPONENT}?)? ("f" ("32" | "64")?)?
DEC_LIT = [0-9] [0-9_]* {NUM_SUFFIX}?
BIN_LIT = "0b" [01_]+ {INT_SUFFIX}?
OCT_LIT = "0o" [0-7_]+ {INT_SUFFIX}?
HEX_LIT = "0x" [a-fA-F0-9_]+ {INT_SUFFIX}?

%state IN_BLOCK_COMMENT
%state IN_RAW_STRING

%%

<YYINITIAL> {
  {WHITE_SPACE}                    { yybegin(YYINITIAL); return WHITE_SPACE; }

  // Keywords
  "contract"                       { yybegin(YYINITIAL); return RhoTypes.CONTRACT; }
  "select"                         { yybegin(YYINITIAL); return RhoTypes.SELECT; }
  "match"                          { yybegin(YYINITIAL); return RhoTypes.MATCH; }
  "total"                          { yybegin(YYINITIAL); return RhoTypes.TOTAL; }
  "case"                           { yybegin(YYINITIAL); return RhoTypes.CASE; }
  "with"                           { yybegin(YYINITIAL); return RhoTypes.WITH; }
  "def"                            { yybegin(YYINITIAL); return RhoTypes.DEF; }
  "for"                            { yybegin(YYINITIAL); return RhoTypes.FOR; }
  "let"                            { yybegin(YYINITIAL); return RhoTypes.LET; }
  "new"                            { yybegin(YYINITIAL); return RhoTypes.NEW; }
  "sum"                            { yybegin(YYINITIAL); return RhoTypes.SUM; }
  "if"                             { yybegin(YYINITIAL); return RhoTypes.IF; }
  "in"                             { yybegin(YYINITIAL); return RhoTypes.IN; }
  "false"                          { yybegin(YYINITIAL); return RhoTypes.FALSE; }
  "true"                           { yybegin(YYINITIAL); return RhoTypes.TRUE; }
  "Nil"                            { yybegin(YYINITIAL); return RhoTypes.NIL; }

  "/*" ("!"|"*"[^*/])              { yybegin(IN_BLOCK_COMMENT); start_comment = zzStartRead; doc_comment = true; comment_depth = 1; }
  "/**" / "/"                      { yybegin(IN_BLOCK_COMMENT); start_comment = zzStartRead; doc_comment = true; comment_depth = 1; }
  "/*"                             { yybegin(IN_BLOCK_COMMENT); start_comment = zzStartRead; doc_comment = false; comment_depth = 1; }
  "///" $                          { yybegin(YYINITIAL); return RhoTypes.LINE_DOC_COMMENT; }
  "//" ("!"|"/"[^/\n\r])[^\n\r]*   { yybegin(YYINITIAL); return RhoTypes.LINE_DOC_COMMENT; }
  "//" [^\n\r]*                    { yybegin(YYINITIAL); return RhoTypes.LINE_COMMENT; }
  // TODO: [tg] remove old comment token (--)
  "--" [^\n\r]*                    { yybegin(YYINITIAL); return RhoTypes.LINE_COMMENT; }
  {CHAR}                           { yybegin(YYINITIAL); return RhoTypes.CHAR_LIT; }
  {STRING}                         { yybegin(YYINITIAL); return RhoTypes.STRING_LIT; }
  "r" "#"* {DOUBLE_QUOTE}          { yybegin(IN_RAW_STRING); start_raw_string = zzStartRead; raw_string_hashes = yytext().length() - 1; }

  {BIN_LIT}                        { yybegin(YYINITIAL); return RhoTypes.BIN_LIT; }
	{OCT_LIT}                        { yybegin(YYINITIAL); return RhoTypes.OCT_LIT; }
 	{HEX_LIT}                        { yybegin(YYINITIAL); return RhoTypes.HEX_LIT; }
 	[0-9] [0-9_]* "." /[^\.0-9e]     { yybegin(YYINITIAL); return RhoTypes.DEC_LIT; }
 	{DEC_LIT}                        { yybegin(YYINITIAL); return RhoTypes.DEC_LIT; }
  [A-Z] [a-zA-Z0-9_']*             { yybegin(YYINITIAL); return RhoTypes.ID_NAME; }
  [a-z] [a-zA-Z0-9_']*             { yybegin(YYINITIAL); return RhoTypes.ID_VAR; }

  "..."                            { yybegin(YYINITIAL); return RhoTypes.TRIPLE_DOT; }
  ".."                             { yybegin(YYINITIAL); return RhoTypes.DOUBLE_DOT; }
  ">>="                            { yybegin(YYINITIAL); return RhoTypes.ASSIGN_RIGHT_SHIFT; }
  "<<="                            { yybegin(YYINITIAL); return RhoTypes.ASSIGN_LEFT_SHIFT; }
  "=>"                             { yybegin(YYINITIAL); return RhoTypes.FAT_ARROW; }
  "->"                             { yybegin(YYINITIAL); return RhoTypes.THIN_ARROW; }
  "."                              { yybegin(YYINITIAL); return RhoTypes.DOT; }

  "&"                              { yybegin(YYINITIAL); return RhoTypes.BITWISE_AND; }
  "|"                              { yybegin(YYINITIAL); return RhoTypes.BITWISE_OR; }
  "^"                              { yybegin(YYINITIAL); return RhoTypes.BITWISE_XOR; }

  "::"                             { yybegin(YYINITIAL); return RhoTypes.DOUBLE_COLON; }
  ":"                              { yybegin(YYINITIAL); return RhoTypes.COLON; }

  "+"                              { yybegin(YYINITIAL); return RhoTypes.PLUS; }
  "-"                              { yybegin(YYINITIAL); return RhoTypes.MINUS; }
  "*"                              { yybegin(YYINITIAL); return RhoTypes.MULTIPLY; }
  "/"                              { yybegin(YYINITIAL); return RhoTypes.DIVIDE; }
  "%"                              { yybegin(YYINITIAL); return RhoTypes.REMAINDER; }

  "=="                             { yybegin(YYINITIAL); return RhoTypes.EQUAL; }
  "="                              { yybegin(YYINITIAL); return RhoTypes.ASSIGN; }
  "!="                             { yybegin(YYINITIAL); return RhoTypes.NOT_EQUAL; }
  "<="                             { yybegin(YYINITIAL); return RhoTypes.LESS_THAN_OR_EQUAL; }
  "<"                              { yybegin(YYINITIAL); return RhoTypes.LESS_THAN; }
  ">="                             { yybegin(YYINITIAL); return RhoTypes.GREATER_THAN_OR_EQUAL; }
  ">"                              { yybegin(YYINITIAL); return RhoTypes.GREATER_THAN; }

  "!"                              { yybegin(YYINITIAL); return RhoTypes.NOT; }
  "~"                              { yybegin(YYINITIAL); return RhoTypes.BOX; }
  "@"                              { yybegin(YYINITIAL); return RhoTypes.AT; }
  "'"                              { yybegin(YYINITIAL); return RhoTypes.SINGLE_QUOTE; }
  "$"                              { yybegin(YYINITIAL); return RhoTypes.DOLLAR; }

  "#"                              { yybegin(YYINITIAL); return RhoTypes.HASH; }
  "["                              { yybegin(YYINITIAL); return RhoTypes.OPEN_SQUARE_BRACKET; }
  "]"                              { yybegin(YYINITIAL); return RhoTypes.CLOSE_SQUARE_BRACKET; }
  "("                              { yybegin(YYINITIAL); return RhoTypes.OPEN_PAREN; }
  ")"                              { yybegin(YYINITIAL); return RhoTypes.CLOSE_PAREN; }
  "{"                              { yybegin(YYINITIAL); return RhoTypes.OPEN_BRACE; }
  "}"                              { yybegin(YYINITIAL); return RhoTypes.CLOSE_BRACE; }
  ","                              { yybegin(YYINITIAL); return RhoTypes.COMMA; }
  ";"                              { yybegin(YYINITIAL); return RhoTypes.SEMICOLON; }
  "_"                              { yybegin(YYINITIAL); return RhoTypes.UNDERSCORE; }

  .                                { yybegin(YYINITIAL); return BAD_CHARACTER; }
}

<IN_BLOCK_COMMENT> {
	"*/"        {
		if (--comment_depth == 0) {
			yybegin(YYINITIAL);
			zzStartRead = start_comment;
			return doc_comment ? RhoTypes.BLOCK_DOC_COMMENT : RhoTypes.BLOCK_COMMENT;
		} else {
			yybegin(IN_BLOCK_COMMENT);
		}
	}
	"/*"        { yybegin(IN_BLOCK_COMMENT); ++comment_depth; }
	[^*/]+      { yybegin(IN_BLOCK_COMMENT); }
	<<EOF>>     { yybegin(YYINITIAL); zzStartRead = start_comment; return RhoTypes.BLOCK_COMMENT; }
	.           { yybegin(IN_BLOCK_COMMENT); }
}

<IN_RAW_STRING> {
	{DOUBLE_QUOTE} "#"*   {
		if (yytext().length() >= raw_string_hashes) {
			// Greedily ate too many #'s ... lets rewind a sec.
			if (yytext().length() > raw_string_hashes) {
				yypushback(yytext().length() - raw_string_hashes);
			}
			yybegin(YYINITIAL);
			zzStartRead = start_raw_string;
			return RhoTypes.RAW_STRING_LIT;
		} else {
			yybegin(IN_RAW_STRING);
		}
	}
	[^\"]   { yybegin(IN_RAW_STRING); }
	<<EOF>> { yybegin(YYINITIAL); zzStartRead = start_raw_string; return RhoTypes.RAW_STRING_LIT; }
	.       { yybegin(IN_RAW_STRING); }
}
