package cofoga

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

trait ThreatGroups extends Matrix {
  type Point = Pair[Int, Int]
  type ThreatGroup = Array[Point]

  protected lazy val groups = {
    val _groups = new HashMap[Point, Array[ThreatGroup]]()
    val directions = List(lr _, du _, ur _, dr _)
    for (i <- 0 until rows; j <- 0 until cols) {
      val tgroups = directions.map(d => d(i)(j).toArray).filter(_.size == connections).toArray
      _groups += (i, j) -> tgroups
    }
    Map() ++ _groups
  }

  protected lazy val unsolvedGroups = {
    val _groups = new HashMap[Point, Array[ThreatGroup]]()
    groups.foreach { t => _groups += t }
    _groups
  }

  protected lazy val solvedGroups = new HashMap[Point, Array[ThreatGroup]]()
  
  protected def lr(row: Int)(col: Int) = {
    for (j <- col until col + connections if containsCol(j))
      yield (row, j)
  }

  protected def du(row: Int)(col: Int) = {
    for (i <- row until row + connections if containsRow(i))
      yield (i, col)
  }

  protected def ur(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  protected def dr(row: Int)(col: Int) = {
    for (k <- 0 until connections if contains(row - k)(col + k))
      yield (row - k, col + k)
  }
}