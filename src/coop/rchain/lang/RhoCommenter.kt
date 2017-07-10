package coop.rchain.lang

import com.intellij.lang.Commenter
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType
import coop.rchain.lang.psi.RhoTypes

class RhoCommenter : Commenter {
  override fun getLineCommentPrefix(): String? {
    return "//"
  }

  override fun getBlockCommentPrefix(): String? {
    return "/*"
  }

  override fun getBlockCommentSuffix(): String? {
    return "*/"
  }

  override fun getCommentedBlockCommentPrefix(): String? {
    return null
  }

  override fun getCommentedBlockCommentSuffix(): String? {
    return null
  }

  val lineCommentTokenType: IElementType?
    get() = RhoTypes.LINE_COMMENT

  val blockCommentTokenType: IElementType?
    get() = RhoTypes.BLOCK_COMMENT

  val documentationCommentTokenType: IElementType?
    get() = RhoTypes.BLOCK_DOC_COMMENT

  val documentationCommentPrefix: String?
    get() = "/**"

  val documentationCommentLinePrefix: String?
    get() = "*"

  val documentationCommentSuffix: String?
    get() = "*/"

  fun isDocumentationComment(element: PsiComment): Boolean {
    val prefix = documentationCommentPrefix
    return prefix != null && element.text.startsWith(prefix)
  }
}
