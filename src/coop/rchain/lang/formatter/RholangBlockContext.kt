package coop.rchain.lang.formatter

import com.intellij.formatting.FormattingMode
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import coop.rchain.lang.RhoLanguage

class RholangBlockContext(val settings: CodeStyleSettings, val mode: FormattingMode) {
  val rholangSettings: CommonCodeStyleSettings

  init {
    rholangSettings = settings.getCommonSettings(RhoLanguage.INSTANCE)
  }
}
