package scaga

import scala.collection.mutable.Stack
import scaga.Player._

class GameBoard(val rows: Int, val cols: Int, val connections: Int) {
  require(rows > 0 && cols > 0 && connections > 0)
  protected var turn    = White
  protected var board   = new Array[Array[Player]](cols, rows)
  protected var history = new Stack[Int]()

  def player = turn
  def apply(row: Int, col: Int) = board(col)(row)
  def moves = board.filter(_.filter(_.occupied).size < cols)
}

object GameBoard {
  val ROWS = 6
  val COLS = 7
  val CXNS = 4
}