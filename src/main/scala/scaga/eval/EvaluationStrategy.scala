package scaga.eval

import java.lang.Double._

trait EvaluationStrategy {
  val board: GameBoard
  def score: Double
}