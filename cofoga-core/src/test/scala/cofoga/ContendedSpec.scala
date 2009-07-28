package cofoga

import org.specs.runner.JUnit4
import org.specs.Specification
import Cofoga._
import Player._

class ContendedSpecTest extends JUnit4(ContendedSpec)

object ContendedSpec extends Specification with Logged {
  var board = new GameBoard()
  def reset() = board = new GameBoard()

  "horizontal winners" should { reset().before
    "be detected at the edge of the board" in {
      val reference =
        """5  -  -  -  -  -  -  -
          |4  -  -  -  -  -  -  -
          |3  -  -  -  -  -  -  -
          |2  -  -  -  -  -  -  -
          |1  X  X  X  -  -  -  -
          |0  O  O  O  O  -  -  -
          |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(0, 0, 1, 1, 2, 2, 3)
      reference mustEqual board.toString.trim
      board.horizontalWinner(row, col) mustEqual true
    }
    "be detected at the other edge of the board" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  X  X  X  -
        |0  -  -  -  O  O  O  O
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(3, 3, 4, 4, 5, 5, 6)
      reference mustEqual board.toString.trim
      board.horizontalWinner(row, col) mustEqual true
    }
    "not be detected if nobody wins" in {
      val reference =
        """5  -  -  -  -  -  -  -
          |4  -  -  -  -  -  -  -
          |3  -  -  -  -  -  -  -
          |2  -  -  -  -  -  -  -
          |1  X  X  X  -  -  -  -
          |0  O  O  O  -  -  -  -
          |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(0, 0, 1, 1, 2, 2)
      reference mustEqual board.toString.trim
      board.horizontalWinner(row, col) mustEqual false
    }
  }
  "vertical winners" should { reset().before
    "be detected at the edge of the board" in {
      val reference =
      """5  O  -  -  -  -  -  -
        |4  O  -  -  -  -  -  -
        |3  O  -  -  -  -  -  -
        |2  O  X  -  -  -  -  -
        |1  X  X  -  -  -  -  -
        |0  O  X  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(0, 0, 0, 1, 0, 1, 0, 1, 0)
      reference mustEqual board.toString.trim
      board.verticalWinner(row, col) mustEqual true
    }
    "not be detected if nobody wins" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  O  -  -  -  -  -  -
        |3  O  -  -  -  -  -  -
        |2  O  -  -  -  -  -  -
        |1  X  X  -  -  -  -  -
        |0  O  X  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(0, 0, 0, 1, 0, 1, 0)
      reference mustEqual board.toString.trim
      board.verticalWinner(row, col) mustEqual false
    }
  }
  "diagonally up winners" should { reset().before
    "be detected at the edge of the board" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  O
        |2  -  -  -  -  -  O  O
        |1  -  -  -  X  O  O  X
        |0  -  -  -  O  X  X  X
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(3, 4, 4, 5, 5, 6, 5, 6, 6, 3, 6)
      reference mustEqual board.toString.trim
      board.diagupWinner(row, col) mustEqual true
    }
    "not detect a winner" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  O  O
        |1  -  -  -  X  O  O  X
        |0  -  -  -  O  X  X  X
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(3, 4, 4, 5, 5, 6, 5, 6, 6, 3)
      reference mustEqual board.toString.trim
      board.diagupWinner(row, col) mustEqual false
    }
  }
  "diagonally down winners" should { reset().before
    "be detected at the edge of the board" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  X  -  -  -
        |3  -  -  -  O  -  -  -
        |2  -  -  -  O  O  -  -
        |1  -  -  -  X  X  O  -
        |0  -  -  -  O  X  X  O
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(3, 3, 3, 4, 3, 4, 4, 5, 5, 3, 6)
      reference mustEqual board.toString.trim
      board.diagdownWinner(row, col) mustEqual true
    }
    "not detect a winner" in {
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  X  -  -  -
        |3  -  -  -  O  -  -  -
        |2  -  -  -  O  O  -  -
        |1  -  -  -  X  X  O  -
        |0  -  -  -  O  X  X  -
        |   0  1  2  3  4  5  6""".stripMargin
      val (row, col) = board.move(3, 3, 3, 4, 3, 4, 4, 5, 5, 3)
      reference mustEqual board.toString.trim
      board.diagdownWinner(row, col) mustEqual false
    }
  }
  "winners calculation" should { reset().before
    "detect a winner in this situation" in {
      println("moose!")
      val reference =
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  X  -  -
        |1  -  O  O  O  O  -  -
        |0  -  X  X  O  X  -  -
        |   0  1  2  3  4  5  6""".stripMargin
      board.move(3, 2, 3, 1, 1, 4, 4, 4, 2)
      reference mustEqual board.toString.trim
      board.winner mustEqual White
    }
  }
}