package cofoga.eval

trait EvaluationStrategy {
  val POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY
  val NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY
  def score(board: GameBoard): Double
}