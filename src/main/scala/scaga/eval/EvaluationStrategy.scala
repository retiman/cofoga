package scaga.eval

trait EvaluationStrategy {
  val POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY
  val NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY
  val board: GameBoard
  def score: Double
}