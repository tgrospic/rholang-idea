package coop.rchain.lang

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory
import com.intellij.openapi.fileTypes.LanguageFileType

import javax.swing.*

class RhoFileTypeFactory : FileTypeFactory() {
  override fun createFileTypes(fileTypeConsumer: FileTypeConsumer) {
    fileTypeConsumer.consume(RhoFileType.INSTANCE, "rho")
  }
}

class RhoFileType private constructor() : LanguageFileType(RhoLanguage.INSTANCE) {

  override fun getName(): String {
    return "Rholang file"
  }

  override fun getDescription(): String {
    return name
  }

  override fun getDefaultExtension(): String {
    return "rho"
  }

  override fun getIcon(): Icon? {
    return RhoIcons.DEFAULT
  }

  companion object {
    val INSTANCE = RhoFileType()
  }
}
