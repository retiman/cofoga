package cofoga.engine

import scala.util.logging.Logged
import cofoga.board.GameBoard
import cofoga.search.SearchStrategy
import cofoga.eval.EvaluationStrategy
import cofoga.Player._
import cofoga.board.GameBoard._

abstract class Engine(val settings: Settings) extends GameBoard
                                              with SearchStrategy
                                              with EvaluationStrategy
                                              with Logged {
  protected val board = new GameBoard(settings.rows, settings.cols, settings.connections)

  def respond() = {
    val m = search(this)
    move(m)
  }
}