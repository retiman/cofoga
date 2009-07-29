package cofoga

import Cofoga._
import Player._

trait Contended extends Vectored with Logged {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[Player]]
  protected def target = connections - 1

  def winner(row: Int, col: Int): Player = {
    List(horizontalWinner _,
         verticalWinner _,
         diagupWinner _,
         diagdownWinner _).foreach { f =>
      if (f(row)(col)) return matrix(row)(col)
    }
    Neither
  }

  def check(player: Player, players: Seq.Projection[Pair[Int, Int]]) = {
    players.map(t => matrix(t.fst)(t.snd)).takeWhile(_ == player).force.size
  }

  def horizontalWinner(row: Int)(col: Int) = {
    val player = matrix(row)(col)
    lazy val a = check(player, lr(row)(col + 1)(target))
    lazy val b = check(player, rl(row)(col - 1)(target))
    a == target || a + b == target
  }

  def verticalWinner(row: Int)(col: Int) = {
    val player = matrix(row)(col)
    lazy val a = check(player, du(row + 1)(col)(target))
    lazy val b = check(player, ud(row - 1)(col)(target))
    a == target || a + b == target
  }

  def diagupWinner(row: Int)(col: Int) = {
    val player = matrix(row)(col)
    lazy val a = check(player, ur(row + 1)(col + 1)(target))
    lazy val b = check(player, dl(row - 1)(col - 1)(target))
    a == target || a + b == target
  }

  def diagdownWinner(row: Int)(col: Int) = {
    val player = matrix(row)(col)
    lazy val a = check(player, dr(row - 1)(col + 1)(target))
    lazy val b = check(player, ul(row + 1)(col - 1)(target))
    a == target || a + b == target
  }
}