package cofoga

import Cofoga._
import Player._

trait Contended extends ThreatGroups with Matrix
                                     with Logged {
  protected def target = connections - 1

  def winner(row: Int)(col: Int): Player = {
    val player = matrix(row)(col)
    val threats = groupsBy(row)(col)
    if (!threats.filter(_.count == connections).isEmpty)
      player
    else
      Neither
  }
}