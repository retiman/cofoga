package cofoga.search

import cofoga.GameBoard
import cofoga.eval.EvaluationStrategy

trait SearchStrategy extends EvaluationStrategy{
  def plies: Int
  def search(board: GameBoard): Int
}