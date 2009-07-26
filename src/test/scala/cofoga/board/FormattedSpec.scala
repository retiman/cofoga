package cofoga.board

import scala.util.logging.ConsoleLogger
import org.specs.runner.JUnit4
import org.specs.Specification
import cofoga.Cofoga._
import cofoga.Player._

class FormattedSpecTest extends JUnit4(FormattedSpec)

object FormattedSpec extends Specification with Formatted
                                           with ConsoleLogger {
  val rows = ROWS
  val cols = COLS
  val matrix = new Array[Array[Player]](rows, cols)
  def reset() = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
  reset()

  "printing a matrix " should {
    "output an empty board correctly" in {
      reset()
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  -  -  -  -
        |0  -  -  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual toString.trim
    }
    "output an board with two pieces correctly" in {
      reset()
      matrix(0)(3) = White
      matrix(1)(3) = Black
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  X  -  -  -
        |0  -  -  -  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual toString.trim
    }
    "output a board with winner correctly" in {
      reset()
      matrix(0)(0) = White
      matrix(1)(0) = Black
      matrix(0)(1) = White
      matrix(1)(1) = Black
      matrix(0)(2) = White
      matrix(1)(2) = Black
      matrix(0)(3) = White
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  X  X  X  -  -  -  -
        |0  O  O  O  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual toString.trim
    }
  }
}