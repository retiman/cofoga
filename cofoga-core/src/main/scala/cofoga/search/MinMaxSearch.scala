package cofoga.search

import cofoga.board.GameBoard
import cofoga.Cofoga._
import cofoga.Player._

trait MinMaxSearch extends SearchStrategy {
  def terminal(board: GameBoard, depth: Int) = board.winner != Neither || depth == plies

  def search(board: GameBoard) = board.turn match {
    case White => max(board, 0, NEGATIVE_INFINITY, POSITIVE_INFINITY).fst
    case Black => min(board, 0, NEGATIVE_INFINITY, POSITIVE_INFINITY).fst
    case _     => throw new IllegalStateException("Current player turn is unknown")
  }

  def max(board: GameBoard, depth: Int, alpha: Double, beta: Double): Pair[Int, Double] = {
    if (terminal(board, depth))
      return (0, utility(board))
    var value  = NEGATIVE_INFINITY
    var alpha_ = alpha
    var best   = 0
    val legals = board.legalMoves
    val (r, c) = board.lastMove
    legals.map(m => (m, Math.abs(m-c))).toList.sort((t1,t2) => t1.snd < t2.snd).map(_.fst).foreach { m =>
      board.move(m)
      val (b, v) = min(board, depth + 1, alpha_, beta)
      if (v > value) {
        value = v
        best = m
        log.info("At depth " + depth + " best move for White is " + m + ", (v, a, b) = " + (value, alpha_, beta))
      }
      board.undo()
      if (value >= beta)
        return (best, value)
      alpha_ = alpha_ max value
    }
    (best, value)
  }

  def min(board: GameBoard, depth: Int, alpha: Double, beta: Double): Pair[Int, Double] = {
    if (terminal(board, depth))
      return (0, utility(board))
    var value = POSITIVE_INFINITY
    var beta_ = beta
    var best  = 0    
    val legals = board.legalMoves
    val (r, c) = board.lastMove
    legals.map(m => (m, Math.abs(m-c))).toList.sort((t1,t2) => t1.snd < t2.snd).map(_.fst).foreach { m =>
      board.move(m)
      val (b, v) = max(board, depth + 1, alpha, beta_)
      if (v < value) {
        value = v
        best = m
        log.info("At depth " + depth + " best move for Black is " + m + ", (v, a, b) = " + (value, alpha, beta_))
      }
      board.undo()
      if (value <= alpha)
        return (best, value)
      beta_ = beta_ min value
    }
    (best, value)
  }
}