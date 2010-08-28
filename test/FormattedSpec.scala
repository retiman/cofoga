package cofoga.board

import org.specs.runner.JUnit4
import org.specs.Specification
import cofoga.Logged
import cofoga.Formatted
import cofoga.Cofoga._
import cofoga.Player._

object FormattedSpec extends Specification with Logged {
  var f: FormattedImpl = _

  class FormattedImpl extends Formatted {
    val rows = ROWS
    val cols = COLS
    val connections = CXNS
    val matrix = Array.ofDim[Player](rows, cols)
    for (
      i <- 0 until rows;
      j <- 0 until cols
    ) matrix(i)(j) = Neither
  }

  def setup = f = new FormattedImpl

  "printing a matrix " should { setup.before
    "output an empty board correctly" in {
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  -  -  -  -
        |0  -  -  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual f.toString.trim
    }
    "output an board with two pieces correctly" in {
      f.matrix(0)(3) = White
      f.matrix(1)(3) = Black
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  X  -  -  -
        |0  -  -  -  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual f.toString.trim
    }
    "output a board with winner correctly" in {
      f.matrix(0)(0) = White
      f.matrix(1)(0) = Black
      f.matrix(0)(1) = White
      f.matrix(1)(1) = Black
      f.matrix(0)(2) = White
      f.matrix(1)(2) = Black
      f.matrix(0)(3) = White
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  X  X  X  -  -  -  -
        |0  O  O  O  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual f.toString.trim
    }
  }
}
