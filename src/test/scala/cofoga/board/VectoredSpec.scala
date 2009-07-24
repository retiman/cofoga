package cofoga.board

import scala.util.logging.ConsoleLogger
import org.specs.runner.JUnit4
import org.specs.Specification
import cofoga.Player._
import cofoga.board.GameBoard._

class VectoredSpecTest extends JUnit4(VectoredSpec)

object VectoredSpec extends Specification with Vectored
                                          with ConsoleLogger {
  val rows = ROWS
  val cols = COLS
  val matrix = new Array[Array[Player]](rows, cols)
  def reset() = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
  reset()

  "horizontal vectors" should {
    "resolve 4 players from (0, 3) in the positive direction" in {
      reset()
      matrix(0)(3) = White
      matrix(0)(4) = White
      matrix(0)(5) = White
      matrix(0)(6) = White
      horizontal(0, 3)(4).toList mustEqual List.make(4, White)
    }
    "resolve 2 players from (0, 5) in the positive direction" in {
      reset()
      matrix(0)(5) = White
      matrix(0)(6) = White
      horizontal(0, 5)(4).toList mustEqual List.make(2, White)
    }
    "resolve 4 players from (0, 5) in the negative direction" in {
      reset()
      matrix(0)(5) = White
      matrix(0)(4) = White
      matrix(0)(3) = White
      matrix(0)(2) = White
      horizontal(0, 5)(-4).toList mustEqual List.make(4, White)
    }
  }
  "vertical vectors" should {
    "resolve 4 players from (0, 0) in the positive direction" in {
      reset()
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      matrix(3)(0) = White
      vertical(0, 0)(4).toList mustEqual List.make(4, White)
    }
    "resolve 3 players from (3, 0) in the positive direction" in {
      reset()
      matrix(3)(0) = White
      matrix(4)(0) = White
      matrix(5)(0) = White
      vertical(3, 0)(4).toList mustEqual List.make(3, White)
    }
    "resolve 4 players from (5, 0) in the negative direction" in {
      reset()
      matrix(5)(0) = White
      matrix(4)(0) = White
      matrix(3)(0) = White
      matrix(2)(0) = White
      vertical(5, 0)(-4).toList mustEqual List.make(4, White)
    }
    "resolve 2 players from (1, 0) in the negative direction" in {
      reset()
      matrix(1)(0) = White
      matrix(0)(0) = White
      vertical(1, 0)(-4).toList mustEqual List.make(2, White)
    }
  }
}