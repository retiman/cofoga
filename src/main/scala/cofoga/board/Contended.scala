package cofoga.board

import scala.util.logging.Logged
import cofoga.Player._
import cofoga.Predef._

trait Contended extends Vectored with Logged {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[Player]]
  protected val target = connections - 1

  def winner(row: Int, col: Int): Player = {
    List(horizontalWinner _,
         verticalWinner _,
         diagonallyUp _,
         diagonallyDown _).foreach { f =>
      if (f(row, col)) return matrix(row)(col)
    }
    Neither
  }

  def horizontalWinner(row: Int, col: Int)     = check(lr, row, col) || check(rl, row, col)
  def verticalWinner(row: Int, col: Int)       = check(du, row, col) || check(ud, row, col)
  def diagonallyUpWinner(row: Int, col: Int)   = check(ur, row, col) || check(dl, row, col)
  def diagonallyDownWinner(row: Int, col: Int) = check(ul, row, col) || check(dr, row, col)

  protected def check(player: Player, players: Iterable[Player]) = players.filter(_ == player)
                                                                          .init
                                                                          .toArray
                                                                          .size == target
  protected def lr(row: Int, col: Int) = horizontal(row, col)(connections)
  protected def rl(row: Int, col: Int) = horizontal(row, col)(-connections)
  protected def du(row: Int, col: Int) = vertical(row, col)(connections)
  protected def ud(row: Int, col: Int) = vertical(row, col)(-connections)
  protected def ur(row: Int, col: Int) = diagonalup(row, col)(connections)
  protected def dl(row: Int, col: Int) = diagonalup(row, col)(-connections)
  protected def dr(row: Int, col: Int) = diagonaldown(row, col)(connections)
  protected def ul(row: Int, col: Int) = diagonaldown(row, col)(-connections)
}