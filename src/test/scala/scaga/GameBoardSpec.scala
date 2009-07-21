package scaga

import org.specs.runner.JUnit4
import org.specs.Specification

class GameBoardSpecTest extends JUnit4(GameBoardSpec)

object GameBoardSpec extends Specification {
  val board = new GameBoard()
  "printing a board " should {
    "output an empty board correctly" in {
      """6  -  -  -  -  -  -  -
        |5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  -  -  -  -
        |   1  2  3  4  5  6  7""".stripMargin mustEqual board.toString.trim
    }
    "output an board with two pieces correctly" in {
      board.move(3)
      board.move(3)
      """6  -  -  -  -  -  -  -
        |5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  X  -  -  -
        |1  -  -  -  O  -  -  -
        |   1  2  3  4  5  6  7""".stripMargin mustEqual board.toString.trim
    }
  }
}