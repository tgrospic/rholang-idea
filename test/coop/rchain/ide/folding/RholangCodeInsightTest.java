package coop.rchain.ide.folding;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import static coop.rchain.RholangTestUtil.getBaseTestDataPath;

public class RholangCodeInsightTest extends LightCodeInsightFixtureTestCase {
  @Override
  protected String getTestDataPath() {
    return getBaseTestDataPath();
  }

  public void testFolding(){
    myFixture.testFolding(getTestDataPath() + "/ide/folding/folding.rho");
  }
}
