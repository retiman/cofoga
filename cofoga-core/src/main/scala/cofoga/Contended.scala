package cofoga

import Cofoga._
import Player._

trait Contended extends Vectored with Logged {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[Player]]
  protected val target = connections - 1

  def winner(row: Int, col: Int): Player = {
    List(horizontalWinner _,
         verticalWinner _,
         diagupWinner _,
         diagdownWinner _).foreach { f =>
      if (f(row)(col)) return matrix(row)(col)
    }
    Neither
  }

  def horizontalWinner(row: Int)(col: Int) = {
    var count = 0
    var left = true
    var right = true
    val player = matrix(row)(col)
    for (k <- 1 until connections) {
      if (right)
        if (containsCol(col + k) && matrix(row)(col + k) == player) count += 1 else right = false
      if (left)
        if (containsCol(col - k) && matrix(row)(col - k) == player) count += 1 else left = false
    }
    count == target
  }

  def verticalWinner(row: Int)(col: Int) = {
    var count = 0
    var left = true
    var right = true
    val player = matrix(row)(col)
    for (k <- 1 until connections) {
      if (right)
        if (containsRow(row + k) && matrix(row + k)(col) == player) count += 1 else right = false
      if (left)
        if (containsRow(row - k) && matrix(row - k)(col) == player) count += 1 else left = false
    }
    count == target
  }

  def diagupWinner(row: Int)(col: Int) = {
    var count = 0
    var left = true
    var right = true
    val player = matrix(row)(col)
    for (k <- 1 until connections) {
      if (right)
        if (contains(row + k)(col + k) && matrix(row + k)(col + k) == player) count += 1 else right = false
      if (left)
        if (contains(row - k)(col - k) && matrix(row - k)(col - k) == player) count += 1 else left = false
    }
    count == target
  }

  def diagdownWinner(row: Int)(col: Int) = {
    var count = 0
    var left = true
    var right = true
    val player = matrix(row)(col)
    for (k <- 1 until connections) {
      if (right)
        if (contains(row - k)(col + k) && matrix(row - k)(col + k) == player) count += 1 else right = false
      if (left)
        if (contains(row + k)(col - k) && matrix(row + k)(col - k) == player) count += 1 else left = false
    }
    count == target
  }
}