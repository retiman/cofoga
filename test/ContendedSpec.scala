package cofoga

import org.specs.Specification
import Cofoga._
import Player._
import scala.math._

object ContendedSpec extends Specification with Logged {
  var c: ContendedImpl = _

  class ContendedImpl extends Contended {
    val rows = ROWS
    val cols = COLS
    val connections = CXNS
    val matrix = Array.ofDim[Player](rows, cols)
    def compute = for (i <- 0 until rows; j <- 0 until cols) {
      groupsBy(i)(j).foreach { g =>
        g.clear
        g.compute
      }
    }
  }

  def setup = c = new ContendedImpl

  "horizontal winner" should { setup.before
    "be detected" in {
      c.matrix(0)(0) = White
      c.matrix(0)(1) = White
      c.matrix(0)(2) = White
      c.matrix(0)(3) = White
      c.compute
      c.winner(0)(0) mustEqual White
      c.winner(0)(1) mustEqual White
      c.winner(0)(2) mustEqual White
      c.winner(0)(3) mustEqual White
    }
    "not be detected" in {
      c.matrix(0)(0) = White
      c.matrix(0)(1) = White
      c.matrix(0)(2) = White
      c.compute
      c.winner(0)(0) mustEqual Neither
      c.winner(0)(1) mustEqual Neither
      c.winner(0)(2) mustEqual Neither
    }
  }

  "vertical winner" should { setup.before
    "be detected" in {
      c.matrix(0)(0) = White
      c.matrix(1)(0) = White
      c.matrix(2)(0) = White
      c.matrix(3)(0) = White
      c.compute
      c.winner(0)(0) mustEqual White
      c.winner(1)(0) mustEqual White
      c.winner(2)(0) mustEqual White
      c.winner(3)(0) mustEqual White
    }
    "not be detected" in {
      c.matrix(0)(0) = White
      c.matrix(1)(0) = White
      c.matrix(2)(0) = White
      c.compute
      c.winner(0)(0) mustEqual Neither
      c.winner(1)(0) mustEqual Neither
      c.winner(2)(0) mustEqual Neither
    }
  }

  "diagonally up winner" should { setup.before
    "be detected" in {
      c.matrix(0)(3) = White
      c.matrix(1)(4) = White
      c.matrix(2)(5) = White
      c.matrix(3)(6) = White
      c.compute
      c.winner(0)(3) mustEqual White
      c.winner(1)(4) mustEqual White
      c.winner(2)(5) mustEqual White
      c.winner(3)(6) mustEqual White
    }
    "not be detected" in {
      c.matrix(0)(3) = White
      c.matrix(1)(4) = White
      c.matrix(2)(5) = White
      c.compute()
      c.winner(0)(3) mustEqual Neither
      c.winner(1)(4) mustEqual Neither
      c.winner(2)(6) mustEqual Neither
    }
  }

  "diagonally down winner" should { setup.before
    "be detected" in {
      c.matrix(3)(3) = White
      c.matrix(2)(4) = White
      c.matrix(1)(5) = White
      c.matrix(0)(6) = White
      c.compute()
      c.winner(3)(3) mustEqual White
      c.winner(2)(4) mustEqual White
      c.winner(1)(5) mustEqual White
      c.winner(0)(6) mustEqual White
    }
    "not be detected" in {
      c.matrix(3)(3) = White
      c.matrix(2)(4) = White
      c.matrix(1)(5) = White
      c.compute()
      c.winner(3)(3) mustEqual Neither
      c.winner(2)(4) mustEqual Neither
      c.winner(1)(5) mustEqual Neither
    }
  }

  "winner computation" should { setup.before
    "be relatively fast" in {
      for (i <- 0 until c.rows; j <- 0 until c.cols) c.matrix(i)(j) = White
      val branchingFactor = 7
      val states = pow(7, HALF_PLIES)
      val pruned = states.toInt / 3
      log.info("Winner computation simulation for " + pruned + " states")
      val start = System.currentTimeMillis
      for (i <- 0 until pruned) {
        c.winner(3)(3)
      }
      val end = System.currentTimeMillis
      val time = (end - start) / 1000
      log.info("Winner computation simulation took " + time + " seconds")
      time <= 7 mustEqual true
    }
  }
}
