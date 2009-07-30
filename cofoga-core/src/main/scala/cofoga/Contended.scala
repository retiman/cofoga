package cofoga

import Cofoga._
import Player._

trait Contended extends Matrix with Logged {
  protected def target = connections - 1

  def winner(row: Int, col: Int): Player = {
    directions.foreach { f => if (f(row)(col)) return matrix(row)(col) }
    Neither
  }
  
  protected def horizontalWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (j <- col + 1 until col + connections) 
      if (containsCol(j) && matrix(row)(j) == player)
        count += 1
    if (count == target)
      return true
    for (j <- col - 1 until col - connections by -1) 
      if (containsCol(j) && matrix(row)(j) == player)
        count += 1
    if (count == target)
      return true
    return false
  }

  protected def verticalWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (i <- row + 1 until row + connections) 
      if (containsRow(i) && matrix(i)(col) == player)
        count += 1
    if (count == target)
      return true
    for (i <- row - 1 until row - connections by - 1) 
      if (containsRow(i) && matrix(i)(col) == player)
        count += 1
    if (count == target)
      return true
    return false
  }

  protected def diagupWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (k <- 1 until connections) 
      if (contains(row + k)(col + k) && matrix(row + k)(col + k) == player)
        count += 1
    if (count == target)
      return true
    for (k <- 1 until connections) 
      if (contains(row - k)(col - k) && matrix(row - k)(col - k) == player)
        count += 1
    if (count == target)
      return true
    return false
  }

  protected def diagdownWinner(row: Int)(col: Int): Boolean = {
    val player = matrix(row)(col)
    var count = 0
    for (k <- 1 until connections) 
      if (contains(row - k)(col + k) && matrix(row - k)(col + k) == player)
        count += 1
    if (count == target)
      return true
    for (k <- 1 until connections) 
      if (contains(row + k)(col - k) && matrix(row + k)(col - k) == player)
        count += 1
    if (count == target)
      return true
    return false
  }

  protected def directions = List(horizontalWinner _, diagupWinner _, diagdownWinner _, verticalWinner _)
}