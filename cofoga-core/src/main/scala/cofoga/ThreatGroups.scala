package cofoga

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import Cofoga._
import Player._

trait ThreatGroups extends Matrix {
  type Point = Pair[Int, Int]

  class ThreatGroup(points: Array[Point]) {
    require(points.size > 0)
    var player = Neither
    var count = -1
    def clear() = count = -1
    def compute() = count match {
      case -1 => {
        var whites = 0
        var blacks = 0
        for (k <- 0 until points.size) {
          val (i, j) = points(k)
          matrix(i)(j) match {
            case White => whites += 1
            case Black => blacks += 1
            case _     => ()
          }
        }
        (whites, blacks) match {
          case (0, _) => player = Black
                         count  = blacks
          case (_, 0) => player = White
                         count  = whites                         
          case _      => player = Neither
                         count  = 0
        }
      }
      case _ => ()
    }
  }

  protected lazy val groups = {
    val _groups = new HashMap[Point, Array[ThreatGroup]]()
    val directions = List(lr _, du _, ur _, dr _)
    for (i <- 0 until rows; j <- 0 until cols) {
      val tgroups = directions.map(d => d(i)(j).toArray)
                              .filter(_.size == connections)
                              .map(new ThreatGroup(_))
                              .toArray
      _groups += (i, j) -> tgroups
    }
    Map() ++ _groups
  }

  protected def threatsAfterMove(row: Int)(col: Int) = {
    val point = (row, col)
    groups(point).foreach { _.compute() }
  }

  protected def threatsAfterUndo(row: Int)(col: Int) = {
    val point = (row, col)
    groups(point).foreach { _.clear() }
  }
  
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