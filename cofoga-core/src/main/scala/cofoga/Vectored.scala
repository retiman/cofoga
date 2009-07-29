package cofoga

import Player._

trait Vectored extends Logged {
  protected def rows: Int
  protected def cols: Int
  protected def connections: Int
  protected def matrix: Array[Array[Player]]
  protected def containsRow(row: Int) = 0 until rows contains row
  protected def containsCol(col: Int) = 0 until cols contains col
  protected def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)
  
  def lr(row: Int)(col: Int)(end: Int) = {
    for (j <- col until col + end if containsCol(j))
      yield (row, j)
  }

  def du(row: Int)(col: Int)(end: Int) = {
    for (i <- row until row + end if containsRow(i))
      yield (i, col)
  }

  def ur(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  def dr(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row - k)(col + k))
      yield (row - k, col + k)
  }

  def rl(row: Int)(col: Int)(end: Int) = {
    for (j <- col until col - end by -1 if containsCol(j))
      yield (row, j)
  }

  def ud(row: Int)(col: Int)(end: Int) = {
    for (i <- row until row - end by -1 if containsRow(i))
      yield (i, col)
  }

  def dl(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row - k)(col - k))
      yield (row - k, col - k)
  }

  def ul(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row + k)(col - k))
      yield (row + k, col - k)
  }
}