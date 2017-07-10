package coop.rchain.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import coop.rchain.lang.RhoFileType
import coop.rchain.lang.RhoLanguage

import javax.swing.*

class RhoFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RhoLanguage.Companion.INSTANCE) {

  override fun getFileType(): FileType {
    return RhoFileType.Companion.INSTANCE
  }

  override fun toString(): String {
    return "RHOlang File"
  }

  override fun getIcon(flags: Int): Icon? {
    return super.getIcon(flags)
  }
}
