package coop.rchain.lang.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.PsiTreeUtil

object PsiTreeHelpUtil {

  fun getPrevSiblingSkipWhiteSpacesAndComments(sibling: ASTNode?): ASTNode? {
    if (sibling == null) return null
    var treePrev: ASTNode? = sibling.treePrev
    while (treePrev != null && isWhitespaceOrComment(treePrev.psi)) {
      treePrev = treePrev.treePrev
    }
    return treePrev
  }

  /**
   * The difference of this method from getPrevSiblingSkipWhiteSpacesAndComments is that this one obtain the previous leaf node rather than sibling.
   * @param current
   * @return
   */
  fun getPrevLeafSkipWhiteSpacesAndComments(current: ASTNode?): PsiElement? {
    if (current == null) return null
    var treePrev = PsiTreeUtil.prevLeaf(current.psi)
    while (treePrev != null && isWhitespaceOrComment(treePrev)) {
      treePrev = PsiTreeUtil.prevLeaf(treePrev)
    }
    return treePrev
  }

  private fun isWhitespaceOrComment(psi: PsiElement): Boolean {
    return psi is PsiWhiteSpace || psi is PsiComment
  }
}
