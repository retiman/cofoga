package cofoga.engine

import scala.util.logging.Logged
import cofoga.board.GameBoard
import cofoga.search.SearchStrategy
import cofoga.eval.EvaluationStrategy
import cofoga.Cofoga._
import cofoga.Player._

abstract class Engine(val settings: Settings) extends GameBoard
                                              with SearchStrategy
                                              with EvaluationStrategy
                                              with Logged {
  protected val board = new GameBoard(settings.rows, settings.cols, settings.connections)

  def plies = settings.plies

  def respond() = {
    val m = search(this)
    move(m)
  }
}