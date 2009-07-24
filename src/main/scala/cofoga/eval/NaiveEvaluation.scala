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
    horizontalEvaluation(board, whites, blacks)
    verticalEvaluation(board, whites, blacks)
    diagupEvaluation(board, whites, blacks)
    diagdownEvaluation(board, whites, blacks)
    whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
    blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
  }

  def weigh(players: String, whites: Array[Int], blacks: Array[Int]) = {
    pattern.findAllIn(players).foreach { m =>
      valueOf(m(0)) match {
        case Some(White) => whites(m.size) += 1
        case Some(Black) => blacks(m.size) += 1
        case _           => ()
      }
    }
  }

  def horizontalEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (i <- 0 until board.rows) {
      val players = board.horizontal(i, 0)(board.cols).mkString
      log("Evaluating horizontal at row " + i + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def verticalEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- 0 until board.cols) {
      val players = board.vertical(0, j)(board.rows).mkString
      log("Evaluating vertical at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def diagupEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- -board.cols until board.cols) {
      val players = board.diagup(0, j)(board.rows).mkString
      log("Evaluating diagup at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def diagdownEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- 0 until 2*board.rows) {
      val players = board.diagdown(0, j)(board.rows).mkString
      log("Evaluating diagdown at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }
}