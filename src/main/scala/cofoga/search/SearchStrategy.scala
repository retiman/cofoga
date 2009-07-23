package cofoga.search

import cofoga.GameBoard

trait SearchStrategy {
  def search(board: GameBoard): Int
}
