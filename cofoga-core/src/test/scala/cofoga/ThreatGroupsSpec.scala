package cofoga

import org.specs.runner.JUnit4
import org.specs.Specification
import Cofoga._
import Player._

class ThreatGroupsSpecTest extends JUnit4(ThreatGroupsSpec)

object ThreatGroupsSpec extends Specification with ThreatGroups with Logged {
  val rows = ROWS
  val cols = COLS
  val connections = CXNS
  protected val matrix = new Array[Array[Player]](rows, cols)
  def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
  
  "vectors" should { reset.before
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

  "threat group" should { reset.before
    "compute correct value" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = Black
      matrix(0)(3) = Black
      val group = new ThreatGroup(Array((0, 0), (0, 1), (0, 2), (0, 3)))
      group.compute()
      group.player mustEqual Neither
      group.count mustEqual 0
    }
    "compute correct value" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = Neither
      matrix(0)(3) = White
      val group = new ThreatGroup(Array((0, 0), (0, 1), (0, 2), (0, 3)))
      group.compute()
      group.player mustEqual White
      group.count mustEqual 3
    }
    /*
    "be relatively fast" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = Neither
      matrix(0)(3) = White
      val group = new ThreatGroup(Array((0, 0), (0, 1), (0, 2), (0, 3)))
      val branchingFactor = 7
      val states = Math.pow(7, HALF_PLIES)
      val pruned = states.toInt / 3
      log.info("Threats computation simulation for " + pruned + " states")
      val start = System.currentTimeMillis
      for (i <- 0 until (4*pruned)) {
        group.clear()
        group.compute()
      }
      val end = System.currentTimeMillis
      val time = (end - start) / 1000
      log.info("Threats computation simulation took " + time + " seconds")
      time <= 7 mustBe true
    }
    */
  }
}
