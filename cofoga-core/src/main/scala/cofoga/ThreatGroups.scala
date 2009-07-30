package cofoga

import scala.collection.mutable.HashMap

trait ThreatGroups {
  type Point = Pair[Int, Int]
  type ThreatGroup = Array[Point]
  val board: GameBoard
  protected lazy val threats = {
    val map = new HashMap[Point, Threats]()
    for (i <- 0 until board.rows; j <- 0 until board.cols) {
      val ts = directions.map(f => f(i)(j).toArray)
                         .filter(_.size == board.connections)
                         .toArray
      map += (i, j) -> ts
    }
    map
  }

  def directions = List(lr _, du _, ur _, dr _)

  private def lr(row: Int)(col: Int) = {
    for (j <- col until col + board.connections if board.containsCol(j))
      yield (row, j)
  }

  private def du(row: Int)(col: Int) = {
    for (i <- row until row + board.connections if board.containsRow(i))
      yield (i, col)
  }

  private def ur(row: Int)(col: Int) = {
    for (k <- 0 until board.connections if board.contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  private def dr(row: Int)(col: Int) = {
    for (k <- 0 until board.connections if board.contains(row - k)(col + k))
      yield (row - k, col + k)
  }
}