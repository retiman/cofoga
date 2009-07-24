package cofoga.search

import scala.util.logging.Logged
import cofoga.board.GameBoard
import cofoga.eval.EvaluationStrategy

trait SearchStrategy extends EvaluationStrategy with Logged {
  def plies: Int
  def search(board: GameBoard): Int
  def terminal(board: GameBoard, depth: Int): Boolean
}