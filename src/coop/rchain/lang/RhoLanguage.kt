package coop.rchain.lang

import com.intellij.lang.Language

class RhoLanguage private constructor() : Language("RHOlang") {
  override fun isCaseSensitive() = true

  override fun getDisplayName() = "RHOlang"

  companion object {
    val INSTANCE = RhoLanguage()
  }
}
