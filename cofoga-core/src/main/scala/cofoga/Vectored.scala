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
  
  def lr(row: Int)(col: Int)(implicit end: Int) = {
    for (j <- col until col + end if containsCol(j))
      yield (row, j)
  }

  def du(row: Int)(col: Int)(implicit end: Int) = {
    for (i <- row until row + end if containsRow(i))
      yield (i, col)
  }

  def ur(row: Int)(col: Int)(implicit end: Int) = {
    for (k <- 0 until end if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  def dr(row: Int)(col: Int)(implicit end: Int) = {
    for (k <- 0 until end if contains(row - k)(col + k))
      yield (row - k, col + k)
  }

  def rl(row: Int)(col: Int)(implicit end: Int) = lr(row)(col - end + 1)(end)

  def ud(row: Int)(col: Int)(implicit end: Int) = du(row - end + 1)(col)(end)

  def dl(row: Int)(col: Int)(implicit end: Int) = ur(row - end + 1)(col - end + 1)(end)

  def ul(row: Int)(col: Int)(implicit end: Int) = dr(row + end - 1)(col - end + 1)(end)
}