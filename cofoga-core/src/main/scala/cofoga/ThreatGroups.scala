package cofoga

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import Cofoga._
import Player._

trait ThreatGroups extends Matrix {
  type Point = Pair[Int, Int]

  class ThreatGroup(points: Array[Point]) {
    require(points.size > 0)
    private var _value: Option[Pair[Player, Int]] = None
    def value: Pair[Player, Int] = _value match {
      case Some(pair) => pair
      case _          => {
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
          case (0, _) => _value = Some((Black, blacks))
          case (_, 0) => _value = Some((White, whites))
          case _      => _value = Some((Neither, 0))
        }
        _value getOrElse { throw new IllegalStateException("_value set but has None value") }
      }
    }
    def clear = _value = None
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

  protected lazy val unsolvedGroups = {
    val _groups = new HashMap[Point, Array[ThreatGroup]]()
    groups.foreach { t => _groups += t }
    _groups
  }

  protected lazy val solvedGroups = new HashMap[Point, Array[ThreatGroup]]()

  protected def threatsAfterMove(row: Int)(col: Int) = {
  }

  protected def threatsAfterUndo(row: Int)(col: Int) = {
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