package scaga

import scaga.Player._

trait Winnable {
  protected val matrix: Array[Array[Player]]
  val rows: Int
  val cols: Int
  val connections: Int  
  def lastMove: Pair[Int, Int]

  def winner: Option[Player] = {
    val (row, col) = lastMove
    List(horizontal _, vertical _).foreach { f =>
      f(row, col) match {
        case Some(player) => return Some(player)
        case _            => ()
      }
    }
    None
  }

  def horizontal(row: Int, col: Int): Option[Player] = check(
    matrix(row)(col),
    row to row,
    (0 max (col - connections + 1)) until (cols min (col + connections))
  )

  def vertical(row: Int, col: Int) = check(
    matrix(row)(col),
    (0 max (row - connections + 1)) until (rows min (row + connections)),
    col to col
  )

  def upforward(row: Int, col: Int) = check(
    matrix(row)(col),
    (0 max (row - connections + 1)) until (rows min (row + connections - 1)),
    (0 max (col - connections + 1)) until (cols min (col + connections))
  )

  def downforward(row: Int, col: Int) = check(
    matrix(row)(col),
    (rows min row + connections - 1) until (0 max row - connections + 1) by -1,
    (0 max col - connections + 1) until (cols min col + connections)
  )

  def check(player: Player, is: Iterable[Int], js: Iterable[Int]) = {
    var count = 0
    for (i <- is;
         j <- js
         if (matrix(i)(j)) == player && count < connections)
      count += 1
    if (count == connections) Some(player) else None
  }
}