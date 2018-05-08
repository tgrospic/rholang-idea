package coop.rchain.lang

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import coop.rchain.lang.psi.RhoTypes.*
import java.io.Reader

class RhoSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
  override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter = RhoSyntaxHighlighter()
}

class RhoSyntaxHighlighter : SyntaxHighlighterBase() {

  override fun getHighlightingLexer(): Lexer = FlexAdapter(RhoLexer(null as Reader?))

  override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
    pack(map(tokenType)?.textAttributesKey)

  companion object {
    fun map(tokenType: IElementType?): RhoColor? = when (tokenType) {
      TRUE, FALSE,
      CONTRACT, SELECT, MATCH, TOTAL, CASE, FOR, LET, NIL, NEW,
      SUM, IF, IN, WITH, DEF,
      UNDERSCORE -> RhoColor.KEYWORD

      VAR -> RhoColor.IDENTIFIER

      CHAR_LIT, STRING_LIT, RAW_STRING_LIT -> RhoColor.STRING
      DEC_LIT, OCT_LIT, HEX_LIT, BIN_LIT -> RhoColor.NUMBER


      LINE_COMMENT -> RhoColor.LINE_COMMENT
      BLOCK_COMMENT -> RhoColor.BLOCK_COMMENT
      LINE_DOC_COMMENT,
      BLOCK_DOC_COMMENT -> RhoColor.DOC_COMMENT

      OPEN_PAREN, CLOSE_PAREN -> RhoColor.PARENTHESIS
      OPEN_BRACE, CLOSE_BRACE -> RhoColor.BRACES
      OPEN_SQUARE_BRACKET, CLOSE_SQUARE_BRACKET -> RhoColor.BRACKETS

      SEMICOLON -> RhoColor.SEMICOLON
      COMMA -> RhoColor.COMMA

    // Operators
      HASH, DOLLAR, AT -> RhoColor.OPERATORS
      TRIPLE_DOT -> RhoColor.OPERATORS
      DOUBLE_DOT -> RhoColor.OPERATORS
      ASSIGN_RIGHT_SHIFT -> RhoColor.OPERATORS
      ASSIGN_LEFT_SHIFT -> RhoColor.OPERATORS
      FAT_ARROW -> RhoColor.OPERATORS
      THIN_ARROW -> RhoColor.OPERATORS
      DOT -> RhoColor.OPERATORS
      BITWISE_AND -> RhoColor.OPERATORS
      BITWISE_OR -> RhoColor.OPERATORS
      BITWISE_XOR -> RhoColor.OPERATORS
      DOUBLE_COLON -> RhoColor.OPERATORS
      COLON -> RhoColor.OPERATORS
      PLUS -> RhoColor.OPERATORS
      MINUS -> RhoColor.OPERATORS
      MULTIPLY -> RhoColor.OPERATORS
      DIVIDE -> RhoColor.OPERATORS
      REMAINDER -> RhoColor.OPERATORS
      EQUAL -> RhoColor.OPERATORS
      ASSIGN -> RhoColor.OPERATORS
      NOT_EQUAL -> RhoColor.OPERATORS
      LESS_THAN_OR_EQUAL -> RhoColor.OPERATORS
      LESS_THAN -> RhoColor.OPERATORS
      GREATER_THAN_OR_EQUAL -> RhoColor.OPERATORS
      GREATER_THAN -> RhoColor.OPERATORS
      NOT -> RhoColor.OPERATORS
      BOX -> RhoColor.OPERATORS
      SINGLE_QUOTE -> RhoColor.OPERATORS
      OPEN_SQUARE_BRACKET -> RhoColor.OPERATORS
      CLOSE_SQUARE_BRACKET -> RhoColor.OPERATORS
      OPEN_PAREN -> RhoColor.OPERATORS
      CLOSE_PAREN -> RhoColor.OPERATORS
      OPEN_BRACE -> RhoColor.OPERATORS
      CLOSE_BRACE -> RhoColor.OPERATORS
      COMMA -> RhoColor.OPERATORS
      SEMICOLON -> RhoColor.OPERATORS

      else -> null
    }
  }
}
