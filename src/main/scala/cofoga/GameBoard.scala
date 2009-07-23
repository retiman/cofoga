package cofoga

import scala.collection.mutable.Stack
import cofoga.GameBoard._
import cofoga.Player._

class GameBoard(val rows: Int, val cols: Int, val connections: Int) extends Winnable {
  require(rows > 0 && cols > 0 && connections > 0)
  protected val matrix  = new Array[Array[Player]](rows, cols)
  protected val filled  = new Array[Int](cols)
  protected val history = new Stack[Int]()
  protected var player  = White
  protected var cachedw: Option[Player] = Some(Neither)
  for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither

  def this() = this(ROWS, COLS, CXNS)
  def turn = player
  def apply(row: Int)(col: Int) = matrix(row)(col)
  def legalMoves = filled.filter(_ < cols - 1)
  def moveHistory = history.toList
  def move(args: Int*): Pair[Int, Int] = args.map(move _).last

  def move(col: Int): Pair[Int, Int] = {
    val row = filled(col)
    matrix(row)(col) = player
    filled(col) += 1
    player = player.switch
    cachedw = None
    history push col
    (row, col)
  }

  def undo() = {
    val (row, col) = lastMove
    matrix(row)(col) = Neither
    filled(col) -= 1
    player = player.switch
    cachedw = None
    history pop
  }

  def lastMove = {
    val col = history.top
    val row = filled(col) - 1
    (row, col)
  }

  def winner: Player = cachedw match {
    case Some(player) => player
    case _            => {
      val (row, col) = lastMove
      val player = winner(row, col)
      cachedw = Some(player)
      player
    }
  }

  override def toString = {
    val s = new StringBuilder
    for (i <- (rows - 1) to 0 by -1) {
      for (j <- 0 until cols) {
        if (j == 0) s.append(i + " ")
        s.append(" " + matrix(i)(j).format)
        if (j < cols - 1) s.append(" ")
      }
      s.append("\n")
    }
    s.append("   ")
    for (j <- 0 until cols) {
      s.append(j)
      if (j < cols - 1) s.append("  ")
    }
    s.append("\n")
    s.toString
  }
}

object GameBoard {
  val ROWS = 6
  val COLS = 7
  val CXNS = 4
}