package coop.rchain.lang

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import coop.rchain.lang.psi.*

class RhoHighlightingAnnotator : Annotator {

  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    val (partToHighlight, color) = highlightElement(element) ?: return

    holder.createInfoAnnotation(partToHighlight, null).textAttributes = color.textAttributesKey
  }

  private fun highlightElement(element: PsiElement): Pair<PsiElement, RhoColor>? = when (element) {
    is RhoNamedElement -> {
      val color = colorFor(element)
      val part = partToHighlight(element)
      if (color != null && part != null)
        part to color else null
    }
    else -> null
  }
}

private fun colorFor(element: RhoCompositeElement): RhoColor? = when (element) {
//  is RhoContractName -> RhoColor.FUNCTION
//  is RhoConstrName -> RhoColor.CONSTRUCTOR
//  is RhoChan -> RhoColor.PARAMETER
//  is RhoTypeTerm -> RhoColor.TYPE_PARAMETER
  is RhoNameVar-> RhoColor.RHO_NAME
//  is RhoFnName -> RhoColor.FUNCTION
//  is RhoCPattern_ -> RhoColor.BIND_PARAMETER
//  is RhoChanRefSymbol -> RhoColor.KEYWORD
  else -> null
}

private fun partToHighlight(element: RhoNamedElement): PsiElement? = when (element) {
  is RhoNamedElement -> element
  else -> null
}
