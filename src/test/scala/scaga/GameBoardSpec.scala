package scaga

import org.specs.runner.JUnit4
import org.specs.Specification

class GameBoardSpecTest extends JUnit4(GameBoardSpec)

object GameBoardSpec extends Specification {
  val board = new GameBoard()    
  "printing a board " should {
    "output an empty board correctly" in {
      "6  -  -  -  -  -  -  - \n" +
      "5  -  -  -  -  -  -  - \n" +
      "4  -  -  -  -  -  -  - \n" +
      "3  -  -  -  -  -  -  - \n" +
      "2  -  -  -  -  -  -  - \n" +
      "1  -  -  -  -  -  -  - \n" +
      "   1  2  3  4  5  6  7  \n" mustEqual board.toString
    }
    "output an board with two pieces correctly" in {
      board.move(3)
      board.move(3)
      "6  -  -  -  -  -  -  - \n" +
      "5  -  -  -  -  -  -  - \n" +
      "4  -  -  -  -  -  -  - \n" +
      "3  -  -  -  -  -  -  - \n" +
      "2  -  -  -  X  -  -  - \n" +
      "1  -  -  -  O  -  -  - \n" +
      "   1  2  3  4  5  6  7  \n" mustEqual board.toString      
    }
  }
}