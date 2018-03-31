package coop.rchain.lang.formatter

import com.intellij.formatting.FormatTextRanges
import com.intellij.formatting.FormattingMode
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilderEx
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.formatter.DocumentBasedFormattingModel

class RholangFormattingModelBuilder : FormattingModelBuilderEx {
  override fun createModel(element: PsiElement, settings: CodeStyleSettings, mode: FormattingMode): FormattingModel {
    val psiFile = element.containingFile
    val rootNode = psiFile.node
    val context = RholangBlockContext(settings, mode)
    val rootBlock = RholangBlock(rootNode, null, null, settings, context)
    return DocumentBasedFormattingModel(rootBlock, element.project, settings, psiFile.fileType, psiFile)
  }

  override fun getIndentOptionsToUse(file: PsiFile, ranges: FormatTextRanges, settings: CodeStyleSettings): CommonCodeStyleSettings.IndentOptions? {
    return null
  }

  override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
    return createModel(element, settings, FormattingMode.REFORMAT)
  }

  override fun getRangeAffectingIndent(file: PsiFile, offset: Int, elementAtOffset: ASTNode): TextRange? {
    return null
  }
}
