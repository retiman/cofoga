package cofoga

import org.specs.runner.JUnit4
import org.specs.Specification
import Cofoga._
import Player._

class ContendedSpecTest extends JUnit4(ContendedSpec)

object ContendedSpec extends Specification with Contended {
  val rows = ROWS
  val cols = COLS
  val connections = CXNS
  protected val matrix = new Array[Array[Player]](rows, cols)
  def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
  def compute() = for (i <- 0 until rows; j <- 0 until cols) {
    groupsBy(i)(j).foreach { g =>
      g.clear()
      g.compute()
    }
  }

  "horizontal winner" should { reset.before
    "be detected" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = White
      matrix(0)(3) = White
      compute()
      winner(0)(0) mustEqual White
      winner(0)(1) mustEqual White
      winner(0)(2) mustEqual White
      winner(0)(3) mustEqual White
    }
    "not be detected" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = White
      compute()
      winner(0)(0) mustEqual Neither
      winner(0)(1) mustEqual Neither
      winner(0)(2) mustEqual Neither
    }
  }

  "vertical winner" should { reset.before
    "be detected" in {
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      matrix(3)(0) = White
      compute()
      winner(0)(0) mustEqual White
      winner(1)(0) mustEqual White
      winner(2)(0) mustEqual White
      winner(3)(0) mustEqual White
    }
    "not be detected" in {
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      compute()
      winner(0)(0) mustEqual Neither
      winner(1)(0) mustEqual Neither
      winner(2)(0) mustEqual Neither
    }
  }
  
  "diagonally up winner" should { reset.before
    "be detected" in {
      matrix(0)(3) = White
      matrix(1)(4) = White
      matrix(2)(5) = White
      matrix(3)(6) = White
      compute()
      winner(0)(3) mustEqual White
      winner(1)(4) mustEqual White
      winner(2)(5) mustEqual White
      winner(3)(6) mustEqual White
    }
    "not be detected" in {
      matrix(0)(3) = White
      matrix(1)(4) = White
      matrix(2)(5) = White
      compute()
      winner(0)(3) mustEqual Neither
      winner(1)(4) mustEqual Neither
      winner(2)(6) mustEqual Neither
    }
  }

  "diagonally down winner" should { reset.before
    "be detected" in {
      matrix(3)(3) = White
      matrix(2)(4) = White
      matrix(1)(5) = White
      matrix(0)(6) = White
      compute()
      winner(3)(3) mustEqual White
      winner(2)(4) mustEqual White
      winner(1)(5) mustEqual White
      winner(0)(6) mustEqual White
    }
    "not be detected" in {
      matrix(3)(3) = White
      matrix(2)(4) = White
      matrix(1)(5) = White
      compute()
      winner(3)(3) mustEqual Neither
      winner(2)(4) mustEqual Neither
      winner(1)(5) mustEqual Neither
    }
  }

  "winner computation" should { reset.before
    "be relatively fast" in {
      for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = White
      val branchingFactor = 7
      val states = Math.pow(7, HALF_PLIES)
      val pruned = states.toInt / 3
      log.info("Winner computation simulation for " + pruned + " states")
      val start = System.currentTimeMillis
      for (i <- 0 until pruned) {
        winner(3)(3)
      }
      val end = System.currentTimeMillis
      val time = (end - start) / 1000
      log.info("Winner computation simulation took " + time + " seconds")
      time <= 7 mustEqual true
    }
  }
}