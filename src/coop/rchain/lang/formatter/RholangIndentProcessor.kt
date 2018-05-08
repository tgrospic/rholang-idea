package coop.rchain.lang.formatter

import com.intellij.formatting.FormattingMode
import com.intellij.formatting.Indent
import com.intellij.lang.ASTNode
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import coop.rchain.lang.psi.RhoTypes
import coop.rchain.lang.util.PsiTreeHelpUtil

class RholangIndentProcessor(private val settings: CommonCodeStyleSettings) {

  fun getChildIndent(node: ASTNode, mode: FormattingMode): Indent {
    val elementType = node.elementType
    val parent = node.treeParent
    val parentType = parent?.elementType
    val grandParent = parent?.treeParent
    val grandParentType = grandParent?.elementType
    val prevSibling = PsiTreeHelpUtil.getPrevSiblingSkipWhiteSpacesAndComments(node)
    val prevSiblingType = prevSibling?.elementType
    if (parent == null || grandParent == null || grandParent.treeParent == null) {
      return Indent.getNoneIndent()
    }

    if (grandParentType === RhoTypes.BIND_PAREN) {
      return Indent.getContinuationIndent()
    }

    if (elementType === RhoTypes.PM_BRANCH) {
      return Indent.getNormalIndent()
    }

    if (parentType === RhoTypes.PROC_PAREN) {
      return if (elementType === RhoTypes.OPEN_PAREN || elementType === RhoTypes.CLOSE_PAREN) {
        Indent.getNoneIndent()
      } else Indent.getContinuationIndent()
    }

    if (parentType === RhoTypes.PROC_BLOCK) {
      return if (elementType === RhoTypes.OPEN_BRACE || elementType === RhoTypes.CLOSE_BRACE) {
        Indent.getNoneIndent()
      } else Indent.getNormalIndent()
    }

    if (parentType === RhoTypes.PROC_BRACKET) {
      return if (elementType === RhoTypes.OPEN_SQUARE_BRACKET || elementType === RhoTypes.CLOSE_SQUARE_BRACKET) {
        Indent.getNoneIndent()
      } else Indent.getContinuationIndent()
    }

    return if (grandParentType === RhoTypes.QUANTITY && parentType === RhoTypes.PROC) {
      Indent.getContinuationIndent()
    } else Indent.getNoneIndent()

  }

}
