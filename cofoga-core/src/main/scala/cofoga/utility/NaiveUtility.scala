package cofoga.utility

import Cofoga._
import Player._

trait NaiveUtility extends ThreatGroups with Utility {
  def utility = board.winner match {
    case White => POSITIVE_INFINITY
    case Black => NEGATIVE_INFINITY
    case _     => {
      val whites  = new Array[Int](board.connections - 1)
      val blacks  = new Array[Int](board.connections - 1)
      groups.foreach { tgroup =>
        tgroup.player match {
          case White => whites(tgroup.count - 1) += 1
          case Black => blacks(tgroup.count - 1) += 1
        }
      }
      whites.map(3 * _).reduceLeft(_ + _) - blacks.map(3 * _).reduceLeft(_ + _)
    }
  }
}