package cofoga

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import Cofoga._
import Player._

trait ThreatGroups extends Matrix {
  type Point = Pair[Int, Int]

  class ThreatGroup(val points: Array[Point]) {
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
    override def toString = "Group[points: " + points.toList.toString + ", player: " + player + ", count: " + count + "]"
  }

  protected lazy val pointMap = {
    val map = new HashMap[Point, HashSet[ThreatGroup]]()
    val directions = List(lr _, du _, ur _, dr _)
    for (i <- 0 until rows; j <- 0 until cols) {
      val tgroups = directions.map(d => d(i)(j).toArray)
                              .filter(_.size == connections)
                              .map(new ThreatGroup(_))
      tgroups.foreach { tgroup =>
        tgroup.points.foreach { point =>
          if (!map.contains(point))
            map(point) = new HashSet()
          map(point) += tgroup
        }
      }
    }
    Map() ++ map
  }

  lazy val groups = pointMap.values.map(Set() ++ _.elements).reduceLeft(_ ++ _)

  def groupsBy(row: Int)(col: Int) = pointMap((row, col))

  protected def threatsAfterMove(row: Int)(col: Int) = {
    groupsBy(row)(col).foreach { _.compute() }
  }

  protected def threatsAfterUndo(row: Int)(col: Int) = {
    groupsBy(row)(col).foreach { _.clear() }
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