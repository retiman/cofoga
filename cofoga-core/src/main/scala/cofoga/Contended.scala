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

  def horizontalWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (j <- col + 1 until col + connections if containsCol(j) && matrix(row)(j) == player) count += 1
    if (count == target) return true
    for (j <- col - 1 until col - connections by -1 if containsCol(j) && matrix(row)(j) == player) count += 1
    if (count == target) return true
    return false
  }

  def verticalWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (i <- row + 1 until row + connections if containsRow(i) && matrix(i)(col) == player) count += 1
    if (count == target) return true
    for (i <- row - 1 until row - connections by - 1 if containsRow(i) && matrix(i)(col) == player) count += 1
    if (count == target) return true
    return false
  }

  def diagupWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (k <- 1 until connections if contains(row + k)(col + k) && matrix(row + k)(col + k) == player) count += 1
    if (count == target) return true
    for (k <- 1 until connections if contains(row - k)(col - k) && matrix(row - k)(col - k) == player) count += 1
    if (count == target) return true
    return false
  }

  def diagdownWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (k <- 1 until connections if contains(row - k)(col + k) && matrix(row - k)(col + k) == player) count += 1
    if (count == target) return true
    for (k <- 1 until connections if contains(row + k)(col - k) && matrix(row + k)(col - k) == player) count += 1
    if (count == target) return true
    return false
  }
}