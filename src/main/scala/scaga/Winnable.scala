package scaga

import scaga.Player._
import scaga.Predef._

trait Winnable[A] {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[A]]
  def containsRow(row: Int) = 0 until rows contains row
  def containsCol(col: Int) = 0 until cols contains col
  def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)

  def horizontal(row: Int, col: Int)     = check(lr, row, col) || check(rl, row, col)
  def vertical(row: Int, col: Int)       = check(du, row, col) || check(ud, row, col)
  def diagonallyUp(row: Int, col: Int)   = check(ur, row, col) || check(dl, row, col)
  def diagonallyDown(row: Int, col: Int) = check(ul, row, col) || check(dr, row, col)

  def winner(row: Int, col: Int): Option[A] = {
    List(horizontal _, vertical _, diagonallyUp _, diagonallyDown _).foreach { f =>
      if (f(row, col)) return Some(matrix(row)(col))
    }
    None
  }

  protected def check(f: (Int, Int) => Iterable[A], row: Int, col: Int) = {
    f(row, col).filter(_ == matrix(row)(col)).toList.size == connections - 1
  }

  protected def lr(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row);
                                              j <- Some(col + k) if containsCol(j))
                                           yield matrix(i)(j)

  protected def rl(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row);
                                              j <- Some(col - k) if containsCol(j))
                                           yield matrix(i)(j)

  protected def du(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row + k);
                                              j <- Some(col)     if containsRow(i))
                                           yield matrix(i)(j)

  protected def ud(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row - k);
                                              j <- Some(col)     if containsRow(i))
                                           yield matrix(i)(j)

  protected def ur(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row + k);
                                              j <- Some(col + k) if contains(i)(j))
                                           yield matrix(i)(j)

  protected def ul(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row + k);
                                              j <- Some(col - k) if contains(i)(j))
                                           yield matrix(i)(j)

  protected def dr(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row - k);
                                              j <- Some(col + k) if contains(i)(j))
                                           yield matrix(i)(j)

  protected def dl(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row - k);
                                              j <- Some(col - k) if contains(i)(j))
                                           yield matrix(i)(j)
}