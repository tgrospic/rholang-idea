//package coop.rchain.lang.lexer
//
//import com.intellij.lexer.Lexer
//import com.intellij.testFramework.LexerTestCase
//import coop.rchain.RholangTestUtil
//import coop.rchain.lang.RhoLexerAdapter
//
//import java.nio.file.Paths
//
//class RholangLexTest : LexerTestCase() {
//
//  override fun createLexer(): Lexer {
//    return RhoLexerAdapter()
//  }
//
//  override fun getDirPath(): String =
//    Paths.get(RholangTestUtil.baseTestDataPath, "lexer").toString()
//
//  override fun getPathToTestDataFile(extension: String): String =
//    this.getDirPath() + "/" + this.getTestName(true) + extension
//
//  fun testToken() {
//    doFileTest("rho")
//  }
//}
