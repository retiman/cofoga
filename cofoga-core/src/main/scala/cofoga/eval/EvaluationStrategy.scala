package cofoga.eval

import cofoga.board.GameBoard

trait EvaluationStrategy extends Logged {
  val POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY
  val NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY
  def utility(board: GameBoard): Double
}