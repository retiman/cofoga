package cofoga

import org.specs.runner.JUnit4
import org.specs.Specification
import Cofoga._
import Player._

class ContendedSpecTest extends JUnit4(ContendedSpec)

object ContendedSpec extends Specification with Contended {
  protected val rows = ROWS
  protected val cols = COLS
  protected val connections = CXNS
  protected val matrix = new Array[Array[Player]](rows, cols)
  protected def reset = for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither

  "horizontal winner" should {
    doFirst { reset }
    "be detected" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = White
      matrix(0)(3) = White
      horizontalWinner(0, 0) mustBe true
      horizontalWinner(0, 1) mustBe true
      horizontalWinner(0, 2) mustBe true
      horizontalWinner(0, 3) mustBe true
    }
    "not be detected" in {
      matrix(0)(0) = White
      matrix(0)(1) = White
      matrix(0)(2) = White
      horizontalWinner(0, 0) mustBe false
      horizontalWinner(0, 1) mustBe false
      horizontalWinner(0, 2) mustBe false
    }
  }

  "vertical winner" should {
    doFirst { reset }
    "be detected" in {
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      matrix(3)(0) = White
      verticalWinner(0, 0) mustBe true
      verticalWinner(1, 0) mustBe true
      verticalWinner(2, 0) mustBe true
      verticalWinner(3, 0) mustBe true
    }
    "not be detected" in {
      matrix(0)(0) = White
      matrix(1)(0) = White
      matrix(2)(0) = White
      verticalWinner(0, 0) mustBe false
      verticalWinner(1, 0) mustBe false
      verticalWinner(2, 0) mustBe false
    }
  }
  
  "diagonally up winner" should {
    doFirst { reset }
    "be detected" in {
      matrix(0)(3) = White
      matrix(1)(4) = White
      matrix(2)(5) = White
      matrix(3)(6) = White
      diagupWinner(0, 3) mustBe true
      diagupWinner(1, 4) mustBe true
      diagupWinner(2, 5) mustBe true
      diagupWinner(3, 6) mustBe true
    }
    "not be detected" in {
      matrix(0)(3) = White
      matrix(1)(4) = White
      matrix(2)(5) = White
      diagupWinner(0, 3) mustBe false
      diagupWinner(1, 4) mustBe false
      diagupWinner(2, 6) mustBe false
    }
  }

  "diagonally down winner" should {
    doFirst { reset }
    "be detected" in {
      matrix(3)(3) = White
      matrix(2)(4) = White
      matrix(1)(5) = White
      matrix(0)(6) = White
      diagdownWinner(3, 3) mustBe true
      diagdownWinner(2, 4) mustBe true
      diagdownWinner(1, 5) mustBe true
      diagdownWinner(0, 6) mustBe true
    }
    "not be detected" in {
      matrix(3)(3) = White
      matrix(2)(4) = White
      matrix(1)(5) = White
      diagdownWinner(3, 3) mustBe false
      diagdownWinner(2, 4) mustBe false
      diagdownWinner(1, 5) mustBe false
    }
  }
}