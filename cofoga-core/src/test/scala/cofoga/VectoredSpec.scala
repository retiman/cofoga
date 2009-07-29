package cofoga.board

import org.specs.runner.JUnit4
import org.specs.Specification
import Cofoga._
import Player._

class VectoredSpecTest extends JUnit4(VectoredSpec)

object VectoredSpec extends Specification with Vectored
                                          with Logged {
  val rows = ROWS
  val cols = COLS
  val connections = CXNS
  val matrix = new Array[Array[Player]](rows, cols)
  implicit val vectorLimit = connections
  def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither

  "pairs" should {
    doFirst { reset }
    "be resolved from left to right" in {
      lr(0)(0).toList mustEqual List((0, 0), (0, 1), (0, 2), (0, 3))
      lr(0)(4).toList mustEqual List((0, 4), (0, 5), (0, 6))
    }
    "be resolved from right to left" in {
      rl(0)(2).toList mustEqual List((0, 0), (0, 1), (0, 2))
      rl(0)(3).toList mustEqual List((0, 0), (0, 1), (0, 2), (0, 3))
    }
    "be resolved from down to up" in {
      du(2)(0).toList mustEqual List((2, 0), (3, 0), (4, 0), (5, 0))
      du(4)(0).toList mustEqual List((4, 0), (5, 0))
    }
    "be resolved from up to down" in {
      ud(3)(0).toList mustEqual List((0, 0), (1, 0), (2, 0), (3, 0))
      ud(2)(0).toList mustEqual List((0, 0), (1, 0), (2, 0))
    }
    "be resolved in the up right direction" in {
      ur(0)(0).toList mustEqual List((0, 0), (1, 1), (2, 2), (3, 3))
      ur(4)(4).toList mustEqual List((4, 4), (5, 5))
    }
    "be resolved in the down left direction" in {
      dl(2)(2).toList mustEqual List((0, 0), (1, 1), (2, 2))
      dl(3)(3).toList mustEqual List((0, 0), (1, 1), (2, 2), (3, 3))
    }
    "be resolved in the down right direction" in {
      dr(3)(3).toList mustEqual List((3, 3), (2, 4), (1, 5), (0, 6))
      dr(4)(4).toList mustEqual List((4, 4), (3, 5), (2, 6))
    }
    "be resolved in the up left direction" in {
      ul(3)(3).toList mustEqual List((5, 1), (4, 2), (3, 3))
      ul(2)(2).toList mustEqual List((4, 0), (3, 1), (2, 2))
    }
  }
}