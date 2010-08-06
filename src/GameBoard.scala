package cofoga

import scala.collection.mutable.Stack
import Cofoga._
import Player._

class GameBoard(val rows: Int, val cols: Int, val connections: Int) extends Matrix
                                                                    with ThreatGroups
                                                                    with Contended
                                                                    with Formatted
                                                                    with Logged {
  require(rows > 0 && cols > 0 && connections > 0)
  override protected val matrix = new Array[Array[Player]](rows, cols)
  protected val filled = new Array[Int](cols)
  protected val history = new Stack[Int]()
  protected var player = White
  protected var _winner: Option[Player] = Some(Neither)

  def this() = this(ROWS, COLS, CXNS)
  def turn = player
  def apply(row: Int)(col: Int) = matrix(row)(col)
  def legalMoves = filled.indices.filter(i => filled(i) < rows)
  def moveHistory = history.toList
  def move(args: Int*): Pair[Int, Int] = args.map(move _).last

  def move(col: Int): Pair[Int, Int] = {
    val row = filled(col)
    matrix(row)(col) = player
    filled(col) += 1
    player = player.switch
    _winner = None
    threatsAfterMove(row)(col)
    history push col
    (row, col)
  }

  def undo() = {
    val (row, col) = lastMove
    matrix(row)(col) = Neither
    filled(col) -= 1
    player = player.switch
    _winner = None
    threatsAfterUndo(row)(col)
    history pop
  }

  def reset() = {
    for (i <- 0 until rows; j <- 0 until cols) matrix(i)(j) = Neither
    for (k <- 0 until filled.size) filled(k) = 0
    history.clear
    player = White
    _winner = None
  }

  def lastMove = {
    val col = history.top
    val row = filled(col) - 1
    (row, col)
  }

  def winner: Player = _winner match {
    case Some(player) => player
    case _            => {
      val (row, col) = lastMove
      val player = winner(row)(col)
      _winner = Some(player)
      player
    }
  }
}