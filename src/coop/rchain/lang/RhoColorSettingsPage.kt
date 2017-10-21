package coop.rchain.lang

import com.intellij.application.options.colors.InspectionColorSettingsPage
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable

class RhoColorSettingsPage : ColorSettingsPage, InspectionColorSettingsPage, DisplayPrioritySortable {
  private val ATTRS = RhoColor.values().map { it.attributesDescriptor }.toTypedArray()

  private val ANNOTATOR_TAGS = RhoColor.values().associateBy({ it.name }, { it.textAttributesKey })

  private val DEMO_TEXT by lazy {
"""/**
 * Rholang is a behaviorally typed, concurrent programming language,
 * with a focus on message-passing and formally modeled by the ρ-calculus,
 * a reflective, higher-order extension of the π-calculus.
*/
new helloworld in {
  contract <CONTRACT>helloworld</CONTRACT>( <BIND_PARAMETER>world</BIND_PARAMETER> ) = {
      for( <BIND_PARAMETER>msg</BIND_PARAMETER> <- <PARAMETER>world</PARAMETER> ) {
          // Hello from Rholang!
          <CONSTRUCTOR>print</CONSTRUCTOR>( msg )
      }
  } |
  /*
   * It is designed to be used to implement protocols and "smart contracts"
   * on a general-purpose blockchain, but could be used in other settings as well.
  */
  new world in {
      <PARAMETER>helloworld</PARAMETER>!( world ) |
      <PARAMETER>world</PARAMETER>!( "Hello World" )
  }
}
"""
  }

  override fun getDisplayName() = "Rholang"
  override fun getIcon() = RhoIcons.DEFAULT
  override fun getAttributeDescriptors() = ATTRS
  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
  override fun getHighlighter() = RhoSyntaxHighlighter()
  override fun getAdditionalHighlightingTagToDescriptorMap() = ANNOTATOR_TAGS
  override fun getDemoText() = DEMO_TEXT
  override fun getPriority(): DisplayPriority = DisplayPriority.KEY_LANGUAGE_SETTINGS
}
