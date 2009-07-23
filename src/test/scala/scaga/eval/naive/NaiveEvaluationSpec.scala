package scaga.eval.naive

import org.specs.runner.JUnit4
import org.specs.Specification
import scaga.Player._

class NaiveEvaluationSpecTest extends JUnit4(NaiveEvaluationSpec)

object NaiveEvaluationSpec extends Specification {
  class TestBoard extends GameBoard {
    def grid = matrix
  }

  class TestEvaluation(val board: TestBoard) extends NaiveEvaluation

  "horizontals calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      for (j <- 0 until board.cols if j % 2 == 0)
        board.grid(0)(j) = White
      val eval = new TestEvaluation(board)
      "O-O-O-O" mustEqual eval.horizontalThreats(0).map(_.format).mkString
    }
  }
  "verticals calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      for (i <- 0 until board.rows if i % 2 == 0)
        board.grid(i)(0) = White
      val eval = new TestEvaluation(board)
      "O-O-O-" mustEqual eval.verticalThreats(0).map(_.format).mkString
    }
  }
  "diagonally up calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      board.grid(0)(0) = White
      board.grid(2)(2) = White
      board.grid(4)(4) = White
      val eval = new TestEvaluation(board)
      "O-O-O-" mustEqual eval.diagonallyUpThreats(0).map(_.format).mkString
    }
  }
  "diagonally down calculation" should {
    "compute correct threats" in {
      val board = new TestBoard()
      board.grid(5)(0) = White
      board.grid(3)(2) = White
      board.grid(1)(4) = White
      val eval = new TestEvaluation(board)
      "O-O-O-" mustEqual eval.diagonallyDownThreats(5).map(_.format).mkString
    }
  }
}