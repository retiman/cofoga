package cofoga.utility

import org.specs.runner.JUnit4
import org.specs.Specification
import Player._

class NaiveUtilitySpecTest extends JUnit4(NaiveUtilitySpec)

object NaiveUtilitySpec extends Specification with NaiveUtility
                                              with Logged {
  val board = new GameBoard() { def m = matrix }

  "utility function" should { board.reset().before
    "compute correct score" in {
      board.move(0, 0, 1, 1, 2, 2)
      utility mustEqual 0
    }    
    "compute correct score" in {
      board.move(0, 0, 1, 1, 2)
      utility mustEqual 9
    }
    "compute correct score" in {
      board.move(0, 0, 2, 0, 4, 0)
      utility mustEqual -6
    }
  }
}