package coop.rchain.lang.formatter

import com.intellij.formatting.Block
import com.intellij.formatting.Spacing
import com.intellij.lang.ASTNode
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import coop.rchain.lang.psi.RhoTypes
import coop.rchain.lang.util.PsiTreeHelpUtil

class RholangSpaceProcessor(private val myNode: ASTNode, private val mySettings: CommonCodeStyleSettings) {

  fun getSpacing(child1: Block?, child2: Block): Spacing? {
    if (child1 !is AbstractBlock || child2 !is AbstractBlock) {
      return null
    }

    val elementType = myNode.elementType
    val parentType = if (myNode.treeParent == null) null else myNode.treeParent.elementType
    val node1 = child1.node
    val type1 = node1.elementType
    val node2 = child2.node
    val type2 = node2.elementType

    if (type1 === RhoTypes.OPEN_BRACE && (type2 === RhoTypes.PROC || type2 === RhoTypes.CASE)) {
      if(parentType !== RhoTypes.NAME_) {
        return Spacing.createSpacing(1, 1, 1, false, 0)
      }
    }
    if (type1 === RhoTypes.BITWISE_OR && myNode.treeParent.treeParent.elementType !== RhoTypes.NAME_) {
      return Spacing.createSpacing(1, 1, 1, false, 0)
    }

    if(type1 === RhoTypes.CASE_ || type2 === RhoTypes.CASE_){
      return Spacing.createSpacing(1, 1, 1, false, 0)
    }

    if(type2 === RhoTypes.CLOSE_BRACE && parentType === RhoTypes.NAME_){
      return null
    }

    return if (type2 === RhoTypes.CLOSE_BRACE) {
      Spacing.createSpacing(1, 1, 1, false, 0)
    } else null
  }
}
