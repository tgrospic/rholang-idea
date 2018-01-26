package coop.rchain.ide.folding

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase

import coop.rchain.RholangTestUtil.getBaseTestDataPath

class RholangCodeInsightTest : LightCodeInsightFixtureTestCase() {
    override fun getTestDataPath(): String {
        return getBaseTestDataPath()
    }

    fun testFolding() {
        doTestFolding("blockDocComment")
        doTestFolding("procedure")
    }

    private fun doTestFolding(testName: String) {
        myFixture.testFolding("$testDataPath/ide/folding/$testName.rho")
    }
}
