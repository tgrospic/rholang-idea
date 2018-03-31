package coop.rchain.ide.folding

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.TextRange
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import com.intellij.util.containers.ContainerUtil
import coop.rchain.RholangTestUtil


class RholangCodeInsightTest : LightCodeInsightFixtureTestCase() {
  override fun getTestDataPath(): String {
    return RholangTestUtil.baseTestDataPath
  }

  fun testFolding() {
    doTestFolding("blockDocComment")
    doTestFolding("procedure")
  }

  fun testFormatter() {
    object : WriteCommandAction.Simple<Any>(project) {
      @Throws(Throwable::class)
      override fun run() {
        myFixture.configureByFiles("./ide/formatter/token.rho")
        CodeStyleManager.getInstance(project).reformatText(myFixture.file,
          ContainerUtil.newArrayList<TextRange>(myFixture.file.textRange))
      }
    }.execute()
    myFixture.checkResultByFile("./ide/formatter/tokenFormatted.rho")
  }

  fun testFormatter2() {
    object : WriteCommandAction.Simple<Any>(project) {
      @Throws(Throwable::class)
      override fun run() {
        myFixture.configureByFiles("./ide/formatter/simpleOne.rho")
        CodeStyleManager.getInstance(project).reformatText(myFixture.file,
          ContainerUtil.newArrayList<TextRange>(myFixture.file.textRange))
      }
    }.execute()
    myFixture.checkResultByFile("./ide/formatter/simpleOneFormatted.rho")
  }

  private fun doTestFolding(testName: String) {
    myFixture.testFolding("$testDataPath/ide/folding/$testName.rho")
  }

}
