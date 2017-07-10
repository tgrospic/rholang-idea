package coop.rchain.lang.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import coop.rchain.lang.RhoLanguage
import org.jetbrains.annotations.NonNls

class RhoElementType(@NonNls debugName: String) : IElementType(debugName, RhoLanguage.INSTANCE)

class RhoTokenType(@NonNls debugName: String) : IElementType(debugName, RhoLanguage.INSTANCE)

// Named element
interface RhoNamedElement : RhoCompositeElement

abstract class RhoNamedElementImpl(node: ASTNode) : RhoCompositeElementImpl(node), RhoNamedElement

interface RhoCompositeElement : PsiElement

abstract class RhoCompositeElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), RhoCompositeElement
