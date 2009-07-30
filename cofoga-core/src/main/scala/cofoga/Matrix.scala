package cofoga

import Player._

trait Matrix {
  val rows: Int
  val cols: Int
  val connections: Int
  protected val matrix: Array[Array[Player]]
  def containsRow(row: Int) = 0 until rows contains row
  def containsCol(col: Int) = 0 until cols contains col
  def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)
}