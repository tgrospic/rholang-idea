package coop.rchain.lang.parser;

import com.intellij.lang.ParserDefinition;
import com.intellij.testFramework.ParsingTestCase;
import coop.rchain.RholangTestUtil;
import coop.rchain.lang.RhoParserDefinition;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;

public class RholangParserTest extends ParsingTestCase {
  public RholangParserTest() {
    super("", "rho", new RhoParserDefinition());
  }

  @Override
  protected String getTestDataPath() {
    return Paths.get(RholangTestUtil.getBaseTestDataPath(), "parser").toString();
  }

  public void testtoken(){
    doTest(true);
  }
}
