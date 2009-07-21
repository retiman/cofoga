package scaga

import org.specs.runner.JUnit4
import org.specs.Specification
import scaga.Player._

class WinnableSpecTest extends JUnit4(WinnableSpec)

object WinnableSpec extends Specification {
  "horizontal winners" should {
    "be detected at the edge of the board" in {
      val reference =
        """5  -  -  -  -  -  -  -
          |4  -  -  -  -  -  -  -
          |3  -  -  -  -  -  -  -
          |2  -  -  -  -  -  -  -
          |1  X  X  X  -  -  -  -
          |0  O  O  O  O  -  -  -
          |   0  1  2  3  4  5  6""".stripMargin
      val board = new GameBoard()
      board.move(0, 0, 1, 1, 2, 2, 3)
      reference mustEqual board.toString.trim
      board.winner mustEqual Some(White)
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
      val board = new GameBoard()
      board.move(3, 3, 4, 4, 5, 5, 6)
      reference mustEqual board.toString.trim
      board.winner mustEqual Some(White)
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
      val board = new GameBoard()
      board.move(0, 0, 1, 1, 2, 2)
      reference mustEqual board.toString.trim
      board.winner mustEqual None
    }
  }
  "vertical winners" should {
    "be detected at the edge of the board" in {
      val reference =
      """5  O  -  -  -  -  -  -
        |4  O  -  -  -  -  -  -
        |3  O  -  -  -  -  -  -
        |2  O  X  -  -  -  -  -
        |1  X  X  -  -  -  -  -
        |0  O  X  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin
      val board = new GameBoard()
      board.move(0, 0, 0, 1, 0, 1, 0, 1, 0)
      reference mustEqual board.toString.trim
      board.winner mustEqual Some(White)
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
      val board = new GameBoard()
      board.move(0, 0, 0, 1, 0, 1, 0)
      reference mustEqual board.toString.trim
      board.winner mustEqual None
    }
  }
}