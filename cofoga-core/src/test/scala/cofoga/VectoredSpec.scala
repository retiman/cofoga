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
  def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither

  "pairs" should {
    doFirst { reset }
    "resolve left to right pairs from (0, 0)" in {
      lr(0)(0).toList mustEqual List((0, 0), (0, 1), (0, 2), (0, 3))
    }
  }
  /*
    "resolve 2 players from (0, 5) in the positive direction" in {
      matrix(0)(5) = White
      matrix(0)(6) = White
      _horizontal(0, 5)(4).toList mustEqual List.make(2, White)
    }
    "resolve 4 players from (0, 5) in the negative direction" in {
      matrix(0)(5) = White
      matrix(0)(4) = White
      matrix(0)(3) = White
      matrix(0)(2) = White
      _horizontal(0, 5)(-4).toList mustEqual List.make(4, White)
    }
  }
  "vertical vectors" should { reset().before
    "resolve 4 players from (0, 0) in the positive direction" in {
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      matrix(3)(0) = White
      _vertical(0, 0)(4).toList mustEqual List.make(4, White)
    }
    "resolve 3 players from (3, 0) in the positive direction" in {
      matrix(3)(0) = White
      matrix(4)(0) = White
      matrix(5)(0) = White
      _vertical(3, 0)(4).toList mustEqual List.make(3, White)
    }
    "resolve 4 players from (5, 0) in the negative direction" in {
      matrix(5)(0) = White
      matrix(4)(0) = White
      matrix(3)(0) = White
      matrix(2)(0) = White
      _vertical(5, 0)(-4).toList mustEqual List.make(4, White)
    }
    "resolve 2 players from (1, 0) in the negative direction" in {
      matrix(1)(0) = White
      matrix(0)(0) = White
      _vertical(1, 0)(-4).toList mustEqual List.make(2, White)
    }
  }
  "diagonally up vectors" should {
    "resolve 4 players from (0, 0) in the positive direction" in {
      matrix(0)(0) = White
      matrix(1)(1) = White
      matrix(2)(2) = White
      matrix(3)(3) = White
      _diagup(0, 0)(4).toList mustEqual List.make(4, White)
    }
    "resolve 4 players from (3, 3) in the negative direction" in {
      matrix(3)(3) = White
      matrix(2)(2) = White
      matrix(1)(1) = White
      matrix(0)(0) = White
      _diagup(3, 3)(-4).toList mustEqual List.make(4, White)
    }
    "resolve 2 players from (4, 4) in the positive direction" in {
      matrix(4)(4) = White
      matrix(5)(5) = White
      _diagup(4, 4)(4).toList mustEqual List.make(2, White)
    }
    "resolve 2 players from (1, 2) in the negative direction" in {
      matrix(1)(2) = White
      matrix(0)(1) = White
      _diagup(1, 2)(-4).toList mustEqual List.make(2, White)
    }
    "resolve 1 players from (0, 0) in the negative direction" in {
      reset()
      matrix(0)(0) = White
      _diagup(0, 0)(-4).toList mustEqual List.make(1, White)
    }
  }
  "diagonally down vectors" should {
    "resolve 4 players from (3, 0) in the positive direction" in {
      matrix(3)(0) = White
      matrix(2)(1) = White
      matrix(1)(2) = White
      matrix(0)(3) = White
      _diagdown(3, 0)(4).toList mustEqual List.make(4, White)
    }
    "resolve 4 players from (3, 3) in the negative direction" in {
      matrix(0)(3) = White
      matrix(1)(2) = White
      matrix(2)(1) = White
      matrix(3)(0) = White
      _diagdown(0, 3)(-4).toList mustEqual List.make(4, White)
    }
    "resolve 3 players from (4, 4) in the positive direction" in {
      matrix(4)(4) = White
      matrix(3)(5) = White
      matrix(2)(6) = White
      _diagdown(4, 4)(4).toList mustEqual List.make(3, White)
    }
    "resolve 2 players from (1, 2) in the negative direction" in {
      matrix(1)(2) = White
      matrix(2)(1) = White
      matrix(3)(0) = White
      _diagdown(1, 2)(-4).toList mustEqual List.make(3, White)
    }
    "resolve 1 players from (0, 0) in the negative direction" in {
      matrix(0)(0) = White
      _diagdown(0, 0)(-4).toList mustEqual List.make(1, White)
    }
  }
  */
}