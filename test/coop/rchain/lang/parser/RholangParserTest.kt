package coop.rchain.lang.parser

import com.intellij.lang.ParserDefinition
import com.intellij.testFramework.ParsingTestCase
import coop.rchain.RholangTestUtil
import coop.rchain.lang.RhoParserDefinition

import java.nio.file.Paths

class RholangParserTest : ParsingTestCase("", "rho", RhoParserDefinition()) {

    override fun getTestDataPath(): String {
        return Paths.get(RholangTestUtil.getBaseTestDataPath(), "parser").toString()
    }

    fun testtoken() {
        doTest(true)
    }
}
