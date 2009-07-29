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

  def hvectors(row: Int, col: Int) = {
    for (k <- 0 until connections if contains(row)(col + k))
      yield (row, col + k)
  }

  def vvectors(row: Int, col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col))
      yield (row + k, col)
  }

  def ddvectors(row: Int, col: Int) = {
    for (k <- 0 until connections if contains(row - k)(col + k))
      yield (row - k, col + k)
  }

  def duvectors(row: Int, col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  def _horizontal(row: Int, col: Int)(end: Int): Seq.Projection[Player] = end match {
    case end if end < 0 => val k = -end
                           _horizontal(row, col - k + 1)(-end)
    case _              => for (j <- col until col + end if containsCol(j))
                             yield matrix(row)(j)
  }

  def computeVectors() = {
    var vectors = List[Seq.Projection[Pair[Int, Int]]]()
    for (i <- 0 until rows;
         j <- 0 until cols) {
      val threats = Set(hvectors _, vvectors _, ddvectors _, duvectors _).map(f => f(i, j)).filter(_.toList.size == connections)
      vectors ++= threats
    }
    vectors
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
