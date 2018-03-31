package coop.rchain.lang.formatter.settings

import com.intellij.lang.Language
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import coop.rchain.lang.RhoLanguage

class RholangCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
  override fun getLanguage(): Language {
    return RhoLanguage.INSTANCE
  }

  override fun getCodeSample(settingsType: LanguageCodeStyleSettingsProvider.SettingsType): String? {
    return null
  }

  override fun getDefaultCommonSettings(): CommonCodeStyleSettings? {
    val defaultSettings = CommonCodeStyleSettings(language)
    val indentOptions = defaultSettings.initIndentOptions()
    indentOptions.INDENT_SIZE = 4
    indentOptions.CONTINUATION_INDENT_SIZE = 2
    return defaultSettings
  }
}
