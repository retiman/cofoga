package scaga

import scaga.Player._

trait Winnable {
  val rows: Int
  val cols: Int
  val connections: Int
  protected val board: Array[Array[Player]]

  def horizontal(row: Int, col: Int) = {
    var count  = 0
    val player = board(row)(col)
    val start  = 0    max (col - connections + 1)
    val end    = cols min (col + connections)
    for (j <- start until end
         if board(row)(j) == player && count < connections)
      count += 1
    if (count == connections) Some(player) else None
  }
}