package coop.rchain

import java.io.File

object RholangTestUtil {
  val testDataPath = File("testData").absolutePath
  val baseTestDataPath: String
    get() = testDataPath
}
