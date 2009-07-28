package cofoga.engine

import cofoga.search.SearchStrategy
import cofoga.eval.EvaluationStrategy
import cofoga.Cofoga._
import cofoga.Player._

abstract class Engine(settings: Settings) extends GameBoard(settings.rows, settings.cols, settings.connections)
                                          with SearchStrategy
                                          with EvaluationStrategy
                                          with Logged {
  def plies = settings.plies

  def respond() = {
    val m = search(this)
    move(m)
  }
}