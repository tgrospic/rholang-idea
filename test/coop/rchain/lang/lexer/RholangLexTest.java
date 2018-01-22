package coop.rchain.lang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.LexerTestCase;
import coop.rchain.RholangTestUtil;
import coop.rchain.lang.RhoLexerAdapter;

import java.nio.file.Paths;

public class RholangLexTest extends LexerTestCase {

  @Override
  protected Lexer createLexer() {
    return new RhoLexerAdapter();
  }

  @Override
  protected String getDirPath() {
    return Paths.get(RholangTestUtil.getBaseTestDataPath(), "lexer").toString();
  }

  public void testToken() {
    doFileTest("rho");
  }
}
