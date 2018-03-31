package coop.rchain.lang.formatter

import com.intellij.formatting.*
import com.intellij.formatting.templateLanguages.BlockWithParent
import com.intellij.lang.ASTNode
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.formatter.FormatterUtil
import com.intellij.psi.formatter.common.AbstractBlock

import java.util.ArrayList

class RholangBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?, private val mySettings: CodeStyleSettings, private val myContext: RholangBlockContext) : AbstractBlock(node, wrap, alignment), BlockWithParent {
  private val myIndentProcessor: RholangIndentProcessor
  private val myIndet: Indent
  private val mySpacingProcessor: RholangSpaceProcessor
  private var myParent: BlockWithParent? = null

  init {
    myIndentProcessor = RholangIndentProcessor(myContext.rholangSettings)
    myIndet = myIndentProcessor.getChildIndent(node, myContext.mode)
    mySpacingProcessor = RholangSpaceProcessor(node, myContext.rholangSettings)
  }

  override fun buildChildren(): List<Block> {
    if (isLeaf) {
      return AbstractBlock.EMPTY
    }

    val blockChildren = ArrayList<Block>()
    var childNode: ASTNode? = node.firstChildNode
    while (childNode != null) {
      if (!FormatterUtil.containsWhiteSpacesOnly(childNode)) {
        val childBlock = RholangBlock(childNode, Wrap.createWrap(WrapType.NONE, false), null, mySettings, myContext)
        childBlock.setParent(this)
        blockChildren.add(childBlock)
      }
      childNode = childNode.treeNext
    }
    return blockChildren
  }

  override fun getIndent(): Indent? {
    return myIndet
  }

  override fun getSpacing(child1: Block?, child2: Block): Spacing? {
    return mySpacingProcessor.getSpacing(child1, child2)
  }

  override fun isLeaf(): Boolean {
    return false
  }

  override fun getParent(): BlockWithParent? {
    return myParent
  }

  override fun setParent(newParent: BlockWithParent) {
    myParent = newParent
  }
}
