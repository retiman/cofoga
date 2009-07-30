package cofoga

trait Utility extends Logged {
  val POSITIVE_INFINITY = java.lang.Double.POSITIVE_INFINITY
  val NEGATIVE_INFINITY = java.lang.Double.NEGATIVE_INFINITY
  val board: GameBoard
  def utility: Double
}