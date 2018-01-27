package coop.rchain.ide.folding

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import coop.rchain.RholangTestUtil.baseTestDataPath


class RholangCodeInsightTest : LightCodeInsightFixtureTestCase() {
  override fun getTestDataPath(): String {
    return baseTestDataPath
  }

  fun testFolding() {
    doTestFolding("blockDocComment")
    doTestFolding("procedure")
  }

  private fun doTestFolding(testName: String) {
    myFixture.testFolding("$testDataPath/ide/folding/$testName.rho")
  }
}
