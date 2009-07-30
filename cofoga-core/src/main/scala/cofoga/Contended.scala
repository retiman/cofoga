package cofoga

import Cofoga._
import Player._

trait Contended extends ThreatGroups with Matrix
                                     with Logged {
  protected def target = connections - 1

  def winner(row: Int)(col: Int): Player = {
    val point = (row, col)
    val player = matrix(row)(col)
    val threats = groups(point)
    if (!threats.filter(_.count == connections).isEmpty)
      player
    else
      Neither
  }
}