package cofoga.utility

import cofoga._
import cofoga.Cofoga._
import cofoga.Player._
import scala.math._

trait NaiveUtility extends Utility with Logged {
  def utility = board.winner match {
    case White => POSITIVE_INFINITY
    case Black => NEGATIVE_INFINITY
    case _     => {
      val whites  = new Array[Int](board.connections - 1)
      val blacks  = new Array[Int](board.connections - 1)
      board.groups.foreach { tgroup =>
        tgroup.compute()
        (tgroup.player, tgroup.count) match {
          case (_, 0)         => ()
          case (White, count) => whites(count - 1) += 1
          case (Black, count) => blacks(count - 1) += 1
          case _              => ()
        }
      }
      log.debug("Whites: " + whites.toList)
      log.debug("Blacks: " + blacks.toList)
      whites.indices.map(k => pow(3, k) * whites(k)).reduceLeft(_ + _) -
      blacks.indices.map(k => pow(3, k) * blacks(k)).reduceLeft(_ + _)
    }
  }
}
