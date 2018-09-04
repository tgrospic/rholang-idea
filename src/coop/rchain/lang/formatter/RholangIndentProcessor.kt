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

//    if (grandParentType === RhoTypes.BIND_PAREN) {
//      return Indent.getContinuationIndent()
//    }

//    if (elementType === RhoTypes.PM_BRANCH) {
//      return Indent.getNormalIndent()
//    }

    if (parentType === RhoTypes.PROC_PAREN_OPTION ) {
      return if (elementType === RhoTypes.OPEN_PAREN || elementType === RhoTypes.CLOSE_PAREN) {
        Indent.getNoneIndent()
      } else Indent.getContinuationIndent()
    }

    if (parentType === RhoTypes.PROC_BLOCK || parentType === RhoTypes.PROC_BLOCK || parentType === RhoTypes.CASE_BLOCK ) {
      return if (elementType === RhoTypes.OPEN_BRACE || elementType === RhoTypes.CLOSE_BRACE) {
        Indent.getNoneIndent()
      } else Indent.getNormalIndent()
    }

//    return if (grandParentType === RhoTypes.QUANTITY_ && parentType === RhoTypes.PROC) {
//      Indent.getContinuationIndent()
//    } else Indent.getNoneIndent()
    return Indent.getNoneIndent()

  }

}
