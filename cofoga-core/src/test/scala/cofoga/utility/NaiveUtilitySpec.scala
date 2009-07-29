package cofoga.utility

import org.specs.runner.JUnit4
import org.specs.Specification
import Player._

class NaiveUtilitySpecTest extends JUnit4(NaiveUtilitySpec)

object NaiveUtilitySpec extends Specification //with NaiveUtility
                                              with Logged {
                                                /*
  class TestBoard extends GameBoard { def m = matrix }
  var board = new TestBoard()
  var whites = new Array[Int](board.connections - 1)
  var blacks = new Array[Int](board.connections - 1)

  def reset() = {
    board = new TestBoard()
    whites = new Array[Int](board.connections - 1)
    blacks = new Array[Int](board.connections - 1)
  }*/

/*
  val rows = ROWS
  val cols = COLS
  val connections = CXNS
  val matrix = new Array[Array[Player]](rows, cols)
  def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither

  "pairs" should {
    doFirst { reset }
    "be resolved from left to right" in {
      lr(0)(0)(4).toList mustEqual List((0, 0), (0, 1), (0, 2), (0, 3))
      lr(0)(4)(4).toList mustEqual List((0, 4), (0, 5), (0, 6))
    }
    "be resolved from down to up" in {
      du(2)(0)(4).toList mustEqual List((2, 0), (3, 0), (4, 0), (5, 0))
      du(4)(0)(4).toList mustEqual List((4, 0), (5, 0))
    }
    "be resolved in the up right direction" in {
      ur(0)(0)(4).toList mustEqual List((0, 0), (1, 1), (2, 2), (3, 3))
      ur(4)(4)(4).toList mustEqual List((4, 4), (5, 5))
    }
    "be resolved in the down right direction" in {
      dr(3)(3)(4).toList mustEqual List((3, 3), (2, 4), (1, 5), (0, 6))
      dr(4)(4)(4).toList mustEqual List((4, 4), (3, 5), (2, 6))
    }
  }*/

  /*

  "horizontals" should { reset().before
    "compute correct threats" in {
      for (j <- 0 until board.cols if j % 2 == 0)
        board.m(0)(j) = White
      horizontalEvaluation(board, whites, blacks)
      List(4, 0, 0) mustEqual whites.toList
      List(0, 0, 0) mustEqual blacks.toList
    }
  }
  "verticals calculation" should { reset().before
    "compute correct threats" in {
      for (i <- 0 until board.rows if i % 2 == 0)
        board.m(i)(0) = White
      verticalEvaluation(board, whites, blacks)
      List(3, 0, 0) mustEqual whites.toList
      List(0, 0, 0) mustEqual blacks.toList
    }
  }
  "diagonally up calculation" should { reset().before
    "compute correct threats" in {
      board.m(0)(0) = White
      board.m(2)(2) = White
      board.m(4)(4) = White
      diagupEvaluation(board, whites, blacks)
      List(3, 0, 0) mustEqual whites.toList
      List(0, 0, 0) mustEqual blacks.toList
    }
  }
  "diagonally down calculation" should { reset().before
    "compute correct threats" in {
      board.m(5)(0) = White
      board.m(3)(2) = White
      board.m(1)(4) = White
      diagdownEvaluation(board, whites, blacks)
      List(3, 0, 0) mustEqual whites.toList
      List(0, 0, 0) mustEqual blacks.toList
    }
  }
  "utility function" should { reset().before
    "compute correct score" in {
      board.move(0, 0, 1, 1, 2, 2)
      utility(board) mustEqual 0
    }    
    "compute correct score" in {
      board.move(0, 0, 1, 1, 2)
      utility(board) mustEqual 9
    }
    "compute correct score" in {
      board.move(0, 0, 2, 0, 4, 0)
      utility(board) mustEqual -6
    }
  }
  */
}