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

  def move(col: Int) = {
    val row = columns(col)
    board(row)(col) = player
    columns(col) += 1
    player = player.switch
    history push col
  }

  def undo() = {
    val col = history.top
    val row = columns(col)
    board(row)(col) = Empty
    player = player.switch
    history pop
  }

  override def toString = {
    val s = new StringBuilder
    for (i <- (rows - 1) to 0 by -1) {
      for (j <- 0 until cols) {
        if (j == 0) s.append(i + 1 + " ")
        s.append(" " + board(i)(j).format + " ")
      }
      s.append("\n")
    }
    s.append("   ")
    for (j <- 0 until cols)
      s.append(j + 1 + " ")
    s.append("\n")
    s.toString
  }
}

object GameBoard {
  val ROWS = 6
  val COLS = 7
  val CXNS = 4
}