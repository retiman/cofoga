package scaga

import org.specs.runner.JUnit4
import org.specs.Specification
import scaga.Player._

class WinnableSpecTest extends JUnit4(WinnableSpec)

object WinnableSpec extends Specification {
  "horizontal winners" should {
    "be detected at the edge of the board" in {
      val board = new GameBoard()
      board.move(0, 0, 1, 1, 2, 2, 3)
      board.horizontal(0, 0) mustEqual Some(White)
    }
    "be detected at the other edge of the board" in {
      val board = new GameBoard()
      board.move(3, 3, 4, 4, 5, 5, 6)
      board.horizontal(0, 3) mustEqual Some(White)
    }
    "not be detected if nobody wins" in {
      val board = new GameBoard()
      board.move(0, 0, 1, 1, 2, 2)
      board.horizontal(0, 0) mustEqual None
    }
  }
}