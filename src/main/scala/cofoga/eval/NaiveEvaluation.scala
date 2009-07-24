package cofoga.eval

import cofoga.board.GameBoard
import cofoga.Player._
import cofoga.Predef._

trait NaiveEvaluation extends EvaluationStrategy {
  val pattern = """X+|O+""".r

  def utility(board: GameBoard) = board.winner match {
    case White => POSITIVE_INFINITY
    case Black => NEGATIVE_INFINITY
    case _     => eval(board)
  }

  def eval(board: GameBoard) = {
    val whites  = new Array[Int](board.connections - 1)
    val blacks  = new Array[Int](board.connections - 1)
    for (i <- 0 until board.rows)           weigh(board.horizontal(i, 0)(board.cols), whites, blacks)
    for (j <- 0 until board.cols)           weigh(board.vertical(0, j)(board.rows),   whites, blacks)
    for (j <- -board.cols until board.cols) weigh(board.diagup(0, j)(board.rows),     whites, blacks)
    for (j <- 0 until 2*board.rows)         weigh(board.diagdown(0, j)(board.rows),   whites, blacks)
    whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
    blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
  }

  def weigh(players: Seq.Projection[Player], whites: Array[Int], blacks: Array[Int]) = {
    val s = players.mkString
    pattern.findAllIn(s).foreach { m =>
      valueOf(m(0)) match {
        case Some(White) => whites(m.size) += 1
        case Some(Black) => blacks(m.size) += 1
        case _           => ()
      }
    }
  }
}