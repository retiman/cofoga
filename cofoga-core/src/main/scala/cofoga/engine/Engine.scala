package cofoga.engine

import Cofoga._
import Player._

abstract class Engine(settings: Settings) extends CofogaBoard(settings.rows, settings.cols, settings.connections)
                                          with Search
                                          with Utility
                                          with Logged {
  def plies = settings.plies

  def respond() = {
    val m = search(this)
    move(m)
  }
}