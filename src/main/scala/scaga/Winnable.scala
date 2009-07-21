package scaga

import scaga.Player._

trait Winnable {
  val rows: Int
  val cols: Int
  val connections: Int
  protected val board: Array[Array[Player]]

  def check(player: Player, is: Iterable[Int], js: Iterable[Int]) = {
    var count = 0
    for (i <- is;
         j <- js
         if (board(i)(j)) == player && count < connections)
      count += 1
    if (count == connections) Some(player) else None
  }

  def horizontal(row: Int, col: Int) = check(
    board(row)(col),
    row to row,
    (0 max col - connections + 1) until (cols min col + connections)
  )

  def vertical(row: Int, col: Int) = check(
    board(row)(col),
    (0 max row - connections + 1) until (rows min row + connections),
    col to col
  )

  def upforward(row: Int, col: Int) = check(
    board(row)(col),
    (0 max row - connections + 1) until (rows min row + connections - 1),
    (0 max col - connections + 1) until (cols min col + connections)
  )

  def downforward(row: Int, col: Int) = check(
    board(row)(col),
    (rows min row + connections - 1) until (0 max row - connections + 1) by -1,
    (0 max col - connections + 1) until (cols min col + connections)
  )
}