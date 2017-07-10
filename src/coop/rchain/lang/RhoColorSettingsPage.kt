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
contract <CONTRACT>HelloWorld</CONTRACT>( <BIND_PARAMETER>world1</BIND_PARAMETER> : <TYPE_PARAMETER>Channel<World></TYPE_PARAMETER>, <BIND_PARAMETER>world2</BIND_PARAMETER> ) = {
  /*
   * It is designed to be used to implement protocols and "smart contracts"
   * on a general-purpose blockchain, but could be used in other settings as well.
  */
  for( <BIND_PARAMETER>msg</BIND_PARAMETER> <- <PARAMETER>world1</PARAMETER> ) {
    let <BIND_PARAMETER>magic_number</BIND_PARAMETER> = 4242424242424242 in
    <PARAMETER>world2</PARAMETER>!( helloResponseFunction( msg, magic_number ) )
  }

  // RChain contracts are internally concurrent. They leverage a message-passing
  // paradigm to optimize responsiveness, offering a more dynamic and sophisticated
  // smart contract ecosystem.
  def <FUNCTION>helloResponseFunction</FUNCTION>( msg : <TYPE_PARAMETER>String</TYPE_PARAMETER>, counter ) = {
    <CONSTRUCTOR>CallMeBackConstructor</CONSTRUCTOR>( "Rho syntax highlighting demo. " + msg )
  }
}
"""
  }

  override fun getDisplayName() = "RHOlang"
  override fun getIcon() = RhoIcons.DEFAULT
  override fun getAttributeDescriptors() = ATTRS
  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
  override fun getHighlighter() = RhoSyntaxHighlighter()
  override fun getAdditionalHighlightingTagToDescriptorMap() = ANNOTATOR_TAGS
  override fun getDemoText() = DEMO_TEXT
  override fun getPriority(): DisplayPriority = DisplayPriority.KEY_LANGUAGE_SETTINGS
}
