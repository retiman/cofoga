package cofoga.board

import cofoga.util.Logged
import cofoga.Cofoga._
import cofoga.Player._

trait Contended extends Vectored with Logged {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[Player]]
  protected val target = connections - 1
  protected val wpattern = ("X{" + connections + "}|O{" + connections + "}").r

  def winner(row: Int, col: Int): Player = {
    List(horizontalWinner _,
         verticalWinner _,
         diagupWinner _,
         diagdownWinner _).foreach { f =>
      if (f(row, col)) return matrix(row)(col)
    }
    Neither
  }

  def horizontalWinner(row: Int, col: Int) = check(matrix(row)(col), horizontal(row, col - target)(2 * connections))
  def verticalWinner(row: Int, col: Int)   = check(matrix(row)(col), vertical(row - target, col)(2 * connections))
  def diagupWinner(row: Int, col: Int)     = check(matrix(row)(col), diagup(row - target, col - target)(2 * connections))
  def diagdownWinner(row: Int, col: Int)   = check(matrix(row)(col), diagdown(row + target, col - target)(2 * connections))

  protected def check(player: Player, players: Seq.Projection[Player]) = !wpattern.findAllIn(players.map(_.format).mkString)
                                                                                  .toList
                                                                                  .isEmpty
}