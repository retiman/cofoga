package cofoga.utility

import org.specs.runner.JUnit4
import org.specs.Specification
import Player._

class ThreatsSpecTest extends JUnit4(ThreatsSpec)

object ThreatsSpec extends Specification with Threats with Logged{
  val board = new GameBoard()
  computeThreats()
  "threats" should {
    "be resolved from left to right" in {
      lr(0)(0).toList mustEqual List((0, 0), (0, 1), (0, 2), (0, 3))
      lr(0)(4).toList mustEqual List((0, 4), (0, 5), (0, 6))
    }
    "be resolved from down to up" in {
      du(2)(0).toList mustEqual List((2, 0), (3, 0), (4, 0), (5, 0))
      du(4)(0).toList mustEqual List((4, 0), (5, 0))
    }
    "be resolved in the up right direction" in {
      ur(0)(0).toList mustEqual List((0, 0), (1, 1), (2, 2), (3, 3))
      ur(4)(4).toList mustEqual List((4, 4), (5, 5))
    }
    "be resolved in the down right direction" in {
      dr(3)(3).toList mustEqual List((3, 3), (2, 4), (1, 5), (0, 6))
      dr(4)(4).toList mustEqual List((4, 4), (3, 5), (2, 6))
    }
  }
}
