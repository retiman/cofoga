package cofoga.search

import cofoga.GameBoard

trait SearchStrategy {
  def plies: Int
  def search(board: GameBoard): Int
}