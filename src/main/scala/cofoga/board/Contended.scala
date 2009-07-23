package cofoga.board

import scala.util.logging.Logged
import cofoga.Player._
import cofoga.Predef._

trait Contended extends Vectored with Logged {
  protected val rows: Int
  protected val cols: Int
  protected val connections: Int
  protected val matrix: Array[Array[Player]]

  def horizontal(row: Int, col: Int)     = check(lr, row, col) || check(rl, row, col)
  def vertical(row: Int, col: Int)       = check(du, row, col) || check(ud, row, col)
  def diagonallyUp(row: Int, col: Int)   = check(ur, row, col) || check(dl, row, col)
  def diagonallyDown(row: Int, col: Int) = check(ul, row, col) || check(dr, row, col)

  def winner(row: Int, col: Int): Player = {
    List(horizontal _, vertical _, diagonallyUp _, diagonallyDown _).foreach { f =>
      if (f(row, col)) return matrix(row)(col)
    }
    Neither
  }

  protected def check(f: (Int, Int) => Iterable[Player], row: Int, col: Int) = {
    f(row, col).filter(_ == matrix(row)(col)).toList.size == connections - 1
  }

  protected def lr(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row);
                      j <- Some(col + k) if containsCol(j))
                   yield (i, j)
    log("Left right scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }

  protected def rl(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row);
                      j <- Some(col - k) if containsCol(j))
                   yield (i, j)
    log("Right left scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }


  protected def du(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row + k);
                      j <- Some(col) if containsRow(i))
                   yield (i, j)
    log("Down up scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }

  protected def ud(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row - k);
                      j <- Some(col) if containsRow(i))
                   yield matrix(i)(j)
    log("Up down scan from " + (row, col) + ": " + result.toString)
    result
  }

  protected def ur(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row + k);
                      j <- Some(col + k) if contains(i)(j))
                   yield (i, j)
    log("Up right scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }

  protected def ul(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row + k);
                      j <- Some(col - k) if contains(i)(j))
                   yield (i, j)
    log("Up left scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }

  protected def dr(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row - k);
                      j <- Some(col + k) if contains(i)(j))
                   yield (i, j)
    log("Down right scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }

  protected def dl(row: Int, col: Int) = {
    val result = for (k <- 1 until connections;
                      i <- Some(row - k);
                      j <- Some(col - k) if contains(i)(j))
                   yield (i, j)
    log("Down left scan from " + (row, col) + ": " + result.toString)
    result.map { t => matrix(t.fst)(t.snd) }
  }
}