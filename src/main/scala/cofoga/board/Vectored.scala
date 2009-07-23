package cofoga.board

trait Vectored {
  def matrix: Array[Array[Player]]
  def rows: Int
  def cols: Int
  def containsRow(row: Int) = 0 until rows contains row
  def containsCol(col: Int) = 0 until cols contains col
  def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)

  def horizontal(row: Int, col: Int)(end: Int) = {
    for (j <- col until end if containsCol(j))
      yield matrix(row)(j)
  }

  def vertical(row: Int, col: Int)(end: Int) = {
    for (i <- row until end if containsRow(i))
      yield matrix(i)(col)
  }

  def diagonalup(row: Int, col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row + k)(col + k))
      yield matrix(row + k)(col + k)
  }

  def diagonaldown(row: Int, col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row - k)(col + k))
      yield matrix(row - k)(col + k)
  }
}
