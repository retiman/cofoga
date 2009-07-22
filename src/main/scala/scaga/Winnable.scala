package scaga

import scaga.Player._
import scaga.Predef._

trait Winnable {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected def containsRow(row: Int): Boolean
  protected def containsCol(col: Int): Boolean
  protected def contains(row: Int, col: Int): Boolean
  protected val matrix: Array[Array[Player]]
  protected def lastMove: Pair[Int, Int]

  def horizontal(row: Int, col: Int)   = check(lr, row, col) || check(rl, row, col)
  def vertical(row: Int, col: Int)     = check(du, row, col) || check(ud, row, col)
  def updiagonal(row: Int, col: Int)   = check(ur, row, col) || check(dl, row, col)
  def downdiagonal(row: Int, col: Int) = check(ul, row, col) || check(dr, row, col)

  def winner: Option[Player] = {
    val (row, col) = lastMove
    val player = matrix(row)(col)
    List(horizontal _, vertical _, updiagonal _, downdiagonal _).foreach { f =>
      if (f(row, col)) return Some(player)
    }
    None
  }

  protected def check(f: (Int, Int) => Iterable[Player], row: Int, col: Int) = {
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
                                              j <- Some(col + k) if contains(i, j))
                                           yield matrix(i)(j)

  protected def ul(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row + k);
                                              j <- Some(col - k) if contains(i, j))
                                           yield matrix(i)(j)

  protected def dr(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row - k);
                                              j <- Some(col + k) if contains(i, j))
                                           yield matrix(i)(j)

  protected def dl(row: Int, col: Int) = for (k <- 1 until connections;
                                              i <- Some(row - k);
                                              j <- Some(col - k) if contains(i, j))
                                           yield matrix(i)(j)
}