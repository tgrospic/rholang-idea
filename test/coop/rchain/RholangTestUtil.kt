package coop.rchain

import com.intellij.openapi.application.PathManager

object RholangTestUtil {
  val baseTestDataPath: String
    get() = PathManager.getHomePath() + "/testData"
}
