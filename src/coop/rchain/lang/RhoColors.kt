package coop.rchain.lang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default
import com.intellij.ide.highlighter.JavaHighlightingColors
import com.intellij.openapi.editor.HighlighterColors

/**
 * See [RhoColorSettingsPage] and [RhoSyntaxHighlighter]
 */
enum class RhoColor(humanName: String, default: TextAttributesKey) {
  CONTRACT("Contract declaration", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.FUNCTION_DECLARATION),
  FUNCTION("Function declaration", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.FUNCTION_DECLARATION),
//  FUNCTION_CALL("Function call", Default.FUNCTION_CALL),
  CONSTRUCTOR("Constructor", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.FUNCTION_CALL),
  TYPE_PARAMETER("Type parameter", JavaHighlightingColors.TYPE_PARAMETER_NAME_ATTRIBUTES),
  BIND_PARAMETER("Bind parameter", JavaHighlightingColors.LOCAL_VARIABLE_ATTRIBUTES),
  PARAMETER("Parameter", JavaHighlightingColors.INSTANCE_FIELD_ATTRIBUTES),

  KEYWORD("Keyword", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.KEYWORD),
  IDENTIFIER("Identifier", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.IDENTIFIER),
  BLOCK_COMMENT("Comment block", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BLOCK_COMMENT),
  LINE_COMMENT("Comment line", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.LINE_COMMENT),
  DOC_COMMENT("Comment documentation", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.DOC_COMMENT),
  STRING("String", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.STRING),
  NUMBER("Number", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.NUMBER),
  BRACKETS("Brackets", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BRACKETS),
  BRACES("Braces", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BRACES),
  PARENTHESIS("Parenthesis", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.PARENTHESES),
  COMMA("Comma", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.COMMA),
  SEMICOLON("Semicolon", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.SEMICOLON),
  OPERATORS("Operators", com.intellij.openapi.editor.DefaultLanguageHighlighterColors.OPERATION_SIGN),
  BAD_CHAR("Bad character", HighlighterColors.BAD_CHARACTER),
  ;

  val textAttributesKey = TextAttributesKey.createTextAttributesKey("coop.rchain.rholang.$name", default)
  val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}
