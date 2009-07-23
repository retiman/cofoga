package cofoga.board

import cofoga.Player._

trait Vectored {
  def matrix: Array[Array[Player]]
  def rows: Int
  def cols: Int
  def containsRow(row: Int) = 0 until rows contains row
  def containsCol(col: Int) = 0 until cols contains col
  def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)

  def horizontal(row: Int, col: Int)(end: Int): Iterable[Pair[Int, Int]] = end match {
    case end if end < 0 => horizontal(row, col + end + 1)(-end)
    case _              => for (j <- col until col + end if containsCol(j))
                             yield matrix(row)(j)
  }

  def vertical(row: Int, col: Int)(end: Int): Iterable[Pair[Int, Int]] = end match {
    case end if end < 0 => vertical(row + end + 1, col)(-end)
    case _              => for (i <- row until end if containsRow(i))
                             yield matrix(i)(col)
  }

  def diagonalup(row: Int, col: Int)(end: Int): Iterable[Pair[Int, Int]] = end match {
    case end if end < 0 => diagonalup(row + end + 1, col + end + 1)(-end)
    case _              => for (k <- 0 until end if contains(row + k)(col + k))
                             yield matrix(row + k)(col + k)
  }

  def diagonaldown(row: Int, col: Int)(end: Int): Iterable[Pair[Int, Int]] = end match {
    case end if end < 0 => diagonaldown(row - end + 1, col + end + 1)(-end)
    case _              => for (k <- 0 until end if contains(row - k)(col + k))
                             yield matrix(row - k)(col + k)
  }
}
