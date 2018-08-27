package coop.rchain.lang.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.javadoc.PsiDocComment
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import coop.rchain.lang.psi.RhoFile
import coop.rchain.lang.psi.RhoProc
import coop.rchain.lang.psi.RhoTypes
import coop.rchain.lang.psi.impl.RhoProcBlockImpl

import java.util.ArrayList

class RholangFoldingBuilder : FoldingBuilder, DumbAware {

  override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
    val descriptors = ArrayList<FoldingDescriptor>()
    appendDescriptors(node.psi, descriptors, document)

    return descriptors.toTypedArray()
  }

  private fun appendDescriptors(psi: PsiElement, descriptors: MutableList<FoldingDescriptor>, document: Document) {
    if (isSingleLine(psi, document)) { // don't fold when  text is single line
      return
    }

    val elementType = psi.node.elementType

    if (elementType === RhoTypes.PROC_BLOCK) {
      val textRange = psi.node.textRange
      val textRange1 = TextRange(textRange.startOffset + 1, textRange.endOffset - 1)
      descriptors.add(FoldingDescriptor(psi.node, textRange1))
    }

    var child: PsiElement? = psi.firstChild
    while (child != null) {
      appendDescriptors(child, descriptors, document)
      child = child.nextSibling
    }
  }


  private fun isSingleLine(element: PsiElement, document: Document): Boolean {
    val textRange = element.textRange
    return document.getLineNumber(textRange.startOffset) == document.getLineNumber(textRange.endOffset)
  }

  override fun getPlaceholderText(node: ASTNode): String? {
    return "..."
  }

  override fun isCollapsedByDefault(node: ASTNode): Boolean {
    return false
  }
}
