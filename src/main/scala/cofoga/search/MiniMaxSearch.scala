package cofoga.search

import cofoga.Player._

trait MiniMaxSearch extends SearchStrategy {
  def terminal(board: GameBoard, depth: Int) = board.winner != Neither || depth == plies

  def max(board: GameBoard, depth: Int, alpha: Double, beta: Double): Double = {
    if (terminal(board, depth))
      return utility(board)
    var value = NEGATIVE_INFINITY
    var alpha_ = alpha
    board.legalMoves.foreach { m =>
      board.move(m)
      value = value max min(board, depth + 1, alpha_, beta)
      board.undo()
      if (value >= beta)
        return value
      alpha_ = alpha_ max value
    }
    value
  }

  def min(board: GameBoard, depth: Int, alpha: Double, beta: Double): Double = {
    if (terminal(board, depth))
      return utility(board)
    var value = POSITIVE_INFINITY
    var beta_ = beta
    board.legalMoves.foreach { m =>
      board.move(m)
      value = value min max(board, depth + 1, alpha, beta_)
      board.undo()
      if (value <= alpha)
        return value
      beta_ = beta_ min value
    }
    value
  }
}