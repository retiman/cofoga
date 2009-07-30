package cofoga

import scala.collection.mutable.HashMap

trait ThreatGroups extends Matrix {
  type Point = Pair[Int, Int]
  type ThreatGroup = Array[Point]

  protected lazy val groups = {
    val map = new HashMap[Point, ThreatGroup]()
    for (i <- 0 until rows; j <- 0 until cols) {
      val ts = directions.map(f => f(i)(j).toArray)
                         .filter(_.size == connections)
                         .toArray
      map += (i, j) -> ts
    }
    map
  }

  def directions = List(lr _, du _, ur _, dr _)

  private def lr(row: Int)(col: Int) = {
    for (j <- col until col + connections if containsCol(j))
      yield (row, j)
  }

  private def du(row: Int)(col: Int) = {
    for (i <- row until row + connections if containsRow(i))
      yield (i, col)
  }

  private def ur(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  private def dr(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row - k)(col + k))
      yield (row - k, col + k)
  }
}