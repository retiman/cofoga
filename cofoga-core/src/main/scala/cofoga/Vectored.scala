package cofoga

import Player._

trait Vectored {
  protected def rows: Int
  protected def cols: Int
  protected def connections: Int
  protected def matrix: Array[Array[Player]]
  protected def containsRow(row: Int) = 0 until rows contains row
  protected def containsCol(col: Int) = 0 until cols contains col
  protected def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)

  def horizontalVector(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row)(col + k))
      yield (row, col + k)
  }

  def verticalVector(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col))
      yield (row + k, col)
  }

  def diagonalUpVector(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col + k))
      yield (row + k, col + k)
  }


  def diagonalDownVector(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row - k)(col + k))
      yield (row - k, col + k)
  }


  def _horizontal(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           _horizontal(row, col - k + 1)(-end)
    case _              => for (j <- col until col + end if containsCol(j))
                             yield matrix(row)(j)
  }

  def _vertical(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           _vertical(row - k + 1, col)(-end)
    case _              => for (i <- row until row + end if containsRow(i))
                             yield matrix(i)(col)
  }

  def _diagup(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           _diagup(row - k + 1, col - k + 1)(-end)
    case _              => for (k <- 0 until end if contains(row + k)(col + k))
                             yield matrix(row + k)(col + k)
  }

  def _diagdown(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           _diagdown(row + k - 1, col - k + 1)(-end)
    case _              => for (k <- 0 until end if contains(row - k)(col + k))
                             yield matrix(row - k)(col + k)
  }
}
