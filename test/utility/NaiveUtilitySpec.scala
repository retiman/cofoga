package cofoga.utility

import org.specs.runner.JUnit4
import org.specs.Specification
import cofoga.Logged
import cofoga.GameBoard
import cofoga.Player._

object NaiveUtilitySpec extends Specification with NaiveUtility
                                              with Logged {
  val board = new GameBoard() { def m = matrix }

  "utility function" should { board.reset().before
    "compute a slight edge for black" in {
      // Black has 6 threats in unsolved groups vertically and diagonally up
      board.move(0, 0, 1, 1, 2, 2)
      log.info("The actual utility for " + board.moveHistory + " was: " + utility)
      utility < 0 mustBe true
    }
    "compute an advantage for white" in {
      // Black has 4 threats in unsolved groups vertically and diagonally up
      // White has a 3-threat though
      board.move(0, 0, 1, 1, 2)
      log.info("The actual utility for " + board.moveHistory + " was: " + utility)
      utility > 0 mustBe true
    }
    "compute an advantage for black" in {
      board.move(0, 0, 2, 0, 4, 0)
      log.info("The actual utility for " + board.moveHistory + " was: " + utility)
      utility < 0 mustBe true
    }
  }
}
