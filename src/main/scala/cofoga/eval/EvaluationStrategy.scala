package cofoga.eval

import scala.util.logging.Logged

trait EvaluationStrategy extends Logged {
  val POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY
  val NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY
  def utility(board: GameBoard): Double
}