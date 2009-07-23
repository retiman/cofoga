package cofoga.board

import scala.util.logging.ConsoleLogger
import org.specs.runner.JUnit4
import org.specs.Specification

class GameBoardSpecTest extends JUnit4(GameBoardSpec)

object GameBoardSpec extends Specification with ConsoleLogger {
  "printing a board " should {
    "output an empty board correctly" in {
      val board = new GameBoard() with ConsoleLogger
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  -  -  -  -
        |0  -  -  -  -  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual board.toString.trim
    }
    "output an board with two pieces correctly" in {
      val board = new GameBoard() with ConsoleLogger
      board.move(3, 3)
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  -  -  -  X  -  -  -
        |0  -  -  -  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual board.toString.trim
    }
    "output a board with winner correctly" in {
      val board = new GameBoard() with ConsoleLogger
      board.move(0, 0, 1, 1, 2, 2, 3)
      """5  -  -  -  -  -  -  -
        |4  -  -  -  -  -  -  -
        |3  -  -  -  -  -  -  -
        |2  -  -  -  -  -  -  -
        |1  X  X  X  -  -  -  -
        |0  O  O  O  O  -  -  -
        |   0  1  2  3  4  5  6""".stripMargin mustEqual board.toString.trim
    }
  }
}