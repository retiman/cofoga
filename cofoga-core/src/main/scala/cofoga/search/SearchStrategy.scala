package cofoga.search

import cofoga.board.GameBoard
import cofoga.eval.EvaluationStrategy

trait SearchStrategy extends EvaluationStrategy with Logged {
  def plies: Int
  def halfPlies: Int = 2 * plies
  def search(board: GameBoard): Int
  def terminal(board: GameBoard, depth: Int): Boolean
}