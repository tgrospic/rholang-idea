package coop.rchain;

import com.intellij.openapi.application.PathManager;

public class RholangTestUtil {
  public static String getBaseTestDataPath() {
    return PathManager.getHomePath() + "/testData";
  }
}
