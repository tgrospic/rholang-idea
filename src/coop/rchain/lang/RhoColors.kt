package coop.rchain.lang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DefaultColor
import com.intellij.ide.highlighter.JavaHighlightingColors
import com.intellij.openapi.editor.HighlighterColors

/**
 * See [RhoColorSettingsPage] and [RhoSyntaxHighlighter]
 */
enum class RhoColor(humanName: String, default: TextAttributesKey) {
  FUNCTION("Function declaration",DefaultColor.FUNCTION_DECLARATION),
  CONSTRUCTOR("Constructor", DefaultColor.FUNCTION_CALL),
  TYPE_PARAMETER("Type parameter", JavaHighlightingColors.TYPE_PARAMETER_NAME_ATTRIBUTES),
  BIND_PARAMETER("Bind parameter", JavaHighlightingColors.LOCAL_VARIABLE_ATTRIBUTES),
  PARAMETER("Parameter", JavaHighlightingColors.INSTANCE_FIELD_ATTRIBUTES),

  KEYWORD("Keyword", DefaultColor.KEYWORD),
  IDENTIFIER("Identifier", DefaultColor.IDENTIFIER),
  BLOCK_COMMENT("Comment block", DefaultColor.BLOCK_COMMENT),
  LINE_COMMENT("Comment line", DefaultColor.LINE_COMMENT),
  DOC_COMMENT("Comment documentation", DefaultColor.DOC_COMMENT),
  STRING("String", DefaultColor.STRING),
  NUMBER("Number", DefaultColor.NUMBER),
  BRACKETS("Brackets", DefaultColor.BRACKETS),
  BRACES("Braces", DefaultColor.BRACES),
  PARENTHESIS("Parenthesis", DefaultColor.PARENTHESES),
  COMMA("Comma", DefaultColor.COMMA),
  SEMICOLON("Semicolon", DefaultColor.SEMICOLON),
  OPERATORS("Operators", DefaultColor.OPERATION_SIGN),
  BAD_CHAR("Bad character", HighlighterColors.BAD_CHARACTER),
  RHO_NAME("RhoName", DefaultColor.CONSTANT),
  ;

  val textAttributesKey = TextAttributesKey.createTextAttributesKey("coop.rchain.rholang.$name", default)
  val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}
