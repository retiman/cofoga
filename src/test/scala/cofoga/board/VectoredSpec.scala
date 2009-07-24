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
  val connections = CXNS
  val matrix = new Array[Array[Player]](rows, cols)
  def reset() = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
  reset()

  "horizontal vectors" should {
    "compute correct players" in {
      reset()
      matrix(0)(3) = White
      matrix(0)(4) = White
      matrix(0)(5) = White
      matrix(0)(6) = White
      horizontal(0, 3)(connections).toList mustEqual List(White, White, White, White)
    }
  }
}