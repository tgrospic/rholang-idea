package coop.rchain.lang

import com.intellij.lang.Language

class RhoLanguage private constructor() : Language("Rholang") {
  override fun isCaseSensitive() = true

  override fun getDisplayName() = "Rholang"

  companion object {
    val INSTANCE = RhoLanguage()
  }
}
