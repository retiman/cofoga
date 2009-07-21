package scaga

import scala.collection.mutable.Stack
import scaga.Player._

class GameBoard(val rows: Int, val cols: Int, val connections: Int) {
  require(rows > 0 && cols > 0 && connections > 0)
  protected var player  = White
  protected var board   = new Array[Array[Player]](rows, cols)
  protected var columns = new Array[Int](cols)
  protected var history = new Stack[Int]()

  def turn = player
  def apply(row: Int, col: Int) = board(row)(col)
  def moves = columns.filter(_ < rows)
}

object GameBoard {
  val ROWS = 6
  val COLS = 7
  val CXNS = 4
}