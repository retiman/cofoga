package scaga

import scaga.Player._
import scaga.Predef._

trait Winnable {
  protected val matrix: Array[Array[Player]]
  val rows: Int
  val cols: Int
  val connections: Int  
  def lastMove: Pair[Int, Int]

  def winner: Option[Player] = {
    val (row, col) = lastMove
    val player = matrix(row)(col)
    List(horizontal _, vertical _, updiagonal _, downdiagonal _).foreach { f =>
      if (f(row, col)) return Some(player)
    }
    None
  }

  def horizontal(row: Int, col: Int)   = lr(row, col).filter(_ == matrix(row)(col)).size == target ||
                                         rl(row, col).filter(_ == matrix(row)(col)).size == target

  def vertical(row: Int, col: Int)     = du(row, col).filter(_ == matrix(row)(col)).size == target ||
                                         ud(row, col).filter(_ == matrix(row)(col)).size == target

  def updiagonal(row: Int, col: Int)   = ur(row, col).filter(_ == matrix(row)(col)).size == target ||
                                         dl(row, col).filter(_ == matrix(row)(col)).size == target

  def downdiagonal(row: Int, col: Int) = ul(row, col).filter(_ == matrix(row)(col)).size == target ||
                                         dr(row, col).filter(_ == matrix(row)(col)).size == target

  private def target = connections - 1

  private def constrained(i: Int, j: Int) = (0 until rows contains i) && (0 until cols contains j)

  private def lr(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row);
                                            j <- Some(col + k) if constrained(i, j))
                                       yield matrix(i)(j)

  private def rl(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row);
                                            j <- Some(col - k) if constrained(i, j))
                                       yield matrix(i)(j)

  private def du(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row + k);
                                            j <- Some(col) if constrained(i, j))
                                       yield matrix(i)(j)

  private def ud(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row - k);
                                            j <- Some(col) if constrained(i, j))
                                       yield matrix(i)(j)

  private def ur(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row + k);
                                            j <- Some(col + k) if constrained(i, j))
                                       yield matrix(i)(j)

  private def ul(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row + k);
                                            j <- Some(col - k) if constrained(i, j))
                                       yield matrix(i)(j)

  private def dr(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row - k);
                                            j <- Some(col + k) if constrained(i, j))
                                       yield matrix(i)(j)

  private def dl(row: Int, col: Int) = for (k <- 1 until connections;
                                            i <- Some(row - k);
                                            j <- Some(col - k) if constrained(i, j))
                                       yield matrix(i)(j)
}