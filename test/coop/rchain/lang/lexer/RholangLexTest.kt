package coop.rchain.lang.lexer

import com.intellij.lexer.Lexer
import com.intellij.openapi.application.PathManager
import com.intellij.testFramework.LexerTestCase
import coop.rchain.RholangTestUtil
import coop.rchain.lang.RhoLexerAdapter

import java.nio.file.Paths

class RholangLexTest : LexerTestCase() {

  override fun createLexer(): Lexer {
    return RhoLexerAdapter()
  }

  override fun getDirPath(): String {
    return Paths.get(RholangTestUtil.baseTestDataPath, "lexer").toString().substring(PathManager.getHomePath().length)
  }

  fun testToken() {
    doFileTest("rho")
  }
}
