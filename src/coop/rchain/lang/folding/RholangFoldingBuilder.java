package coop.rchain.lang.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocComment;
import coop.rchain.lang.psi.RhoFile;
import coop.rchain.lang.psi.RhoTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RholangFoldingBuilder implements FoldingBuilder, DumbAware {

  @NotNull
  @Override
  public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode node, @NotNull Document document) {
    List<FoldingDescriptor> descriptors = new ArrayList<>();
    appendDescriptors(node.getPsi(), descriptors, document);

    return descriptors.toArray(FoldingDescriptor.EMPTY);
  }

  private void appendDescriptors(PsiElement psi, List<FoldingDescriptor> descriptors, Document document) {
    if(isSingleLine(psi, document)){ // don't fold when  text is single line
      return;
    }

    if(psi.getNode().getElementType() == RhoTypes.BLOCK_DOC_COMMENT){
      final ASTNode blockDocComment = psi.getNode();
      final String commentText = blockDocComment.getText();
      TextRange range = new TextRange(blockDocComment.getTextRange().getStartOffset() + 3, blockDocComment.getTextRange().getEndOffset() - 2);
      descriptors.add(new FoldingDescriptor(blockDocComment, range));
    }

    PsiElement child = psi.getFirstChild();
    while (child != null){
      appendDescriptors(child, descriptors, document);
      child = child.getNextSibling();
    }
  }


  private static boolean isSingleLine(PsiElement element, Document document){
    final TextRange textRange = element.getTextRange();
    return document.getLineNumber(textRange.getStartOffset()) == document.getLineNumber(textRange.getEndOffset());
  }

  @Nullable
  @Override
  public String getPlaceholderText(@NotNull ASTNode node) {
    return "...";
  }

  @Override
  public boolean isCollapsedByDefault(@NotNull ASTNode node) {
    return false;
  }
}
