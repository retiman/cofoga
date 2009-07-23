package cofoga.eval

import org.specs.runner.JUnit4
import org.specs.Specification
import cofoga.Player._

class NaiveEvaluationSpecTest extends JUnit4(NaiveEvaluationSpec)

object NaiveEvaluationSpec extends Specification {
  class TestBoard extends GameBoard {
    def grid = matrix
  }

  class TestEvaluation extends NaiveEvaluation

  "horizontals" should {
    "compute correct threats" in {
      val board = new TestBoard()
      val eval  = new TestEvaluation()
      for (j <- 0 until board.cols if j % 2 == 0)
        board.grid(0)(j) = White
      "O-O-O-O" mustEqual eval.horizontal(board, 0).map(_.format).mkString
    }
  }
  "verticals calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      val eval  = new TestEvaluation()
      for (i <- 0 until board.rows if i % 2 == 0)
        board.grid(i)(0) = White
      "O-O-O-" mustEqual eval.vertical(board, 0).map(_.format).mkString
    }
  }
  "diagonally up calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      val eval  = new TestEvaluation()
      board.grid(0)(0) = White
      board.grid(2)(2) = White
      board.grid(4)(4) = White
      "O-O-O-" mustEqual eval.diagonallyUp(board, 0).map(_.format).mkString
    }
  }
  "diagonally down calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      val eval  = new TestEvaluation()
      board.grid(5)(0) = White
      board.grid(3)(2) = White
      board.grid(1)(4) = White
      "O-O-O-" mustEqual eval.diagonallyDown(board, 5).map(_.format).mkString
    }
  }
}