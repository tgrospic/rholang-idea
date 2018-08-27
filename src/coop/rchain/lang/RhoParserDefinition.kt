package coop.rchain.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import coop.rchain.lang.RhoParser
import coop.rchain.lang.psi.RhoFile
import coop.rchain.lang.psi.RhoTypes

import java.io.Reader

class RhoParserDefinition : ParserDefinition {

  override fun createLexer(project: Project): Lexer {
    return FlexAdapter(RhoLexer(null as Reader?))
  }

  override fun getWhitespaceTokens(): TokenSet {
    return WHITE_SPACES
  }

  override fun getCommentTokens(): TokenSet {
    return COMMENTS
  }

  override fun getStringLiteralElements(): TokenSet {
    return TokenSet.EMPTY
  }

  override fun createParser(project: Project): PsiParser {
    return RhoParser()
  }

  override fun getFileNodeType(): IFileElementType {
    return FILE
  }

  override fun createFile(viewProvider: FileViewProvider): PsiFile {
    return RhoFile(viewProvider)
  }

  override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements {
    return ParserDefinition.SpaceRequirements.MAY
  }

  override fun createElement(node: ASTNode): PsiElement {
    return RhoTypes.Factory.createElement(node)
  }

  companion object {
    val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
    val COMMENTS = TokenSet.create(RhoTypes.LINE_COMMENT)
    val FILE = IFileElementType(RhoLanguage.Companion.INSTANCE)
  }
}
