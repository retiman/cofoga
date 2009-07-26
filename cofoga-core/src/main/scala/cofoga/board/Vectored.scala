package cofoga.board

import cofoga.Player._

trait Vectored {
  protected def rows: Int
  protected def cols: Int
  protected def matrix: Array[Array[Player]]
  def containsRow(row: Int) = 0 until rows contains row
  def containsCol(col: Int) = 0 until cols contains col
  def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)

  def horizontal(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           horizontal(row, col - k + 1)(-end)
    case _              => for (j <- col until col + end if containsCol(j))
                             yield matrix(row)(j)
  }

  def vertical(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           vertical(row - k + 1, col)(-end)
    case _              => for (i <- row until row + end if containsRow(i))
                             yield matrix(i)(col)
  }

  def diagup(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           diagup(row - k + 1, col - k + 1)(-end)
    case _              => for (k <- 0 until end if contains(row + k)(col + k))
                             yield matrix(row + k)(col + k)
  }

  def diagdown(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           diagdown(row + k - 1, col - k + 1)(-end)
    case _              => for (k <- 0 until end if contains(row - k)(col + k))
                             yield matrix(row - k)(col + k)
  }
}