package cofoga.engine

import cofoga._
import cofoga.Cofoga._
import cofoga.Player._

abstract class Engine(settings: Settings) extends GameBoard(settings.rows, settings.cols, settings.connections)
                                          with Search
                                          with Utility
                                          with Logged {
  def plies = settings.plies

  def respond() = {
    val m = search(this)
    move(m)
  }
}
