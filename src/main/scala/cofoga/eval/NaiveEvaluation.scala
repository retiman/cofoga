package cofoga.eval

import cofoga.board.GameBoard
import cofoga.Player._
import cofoga.Predef._

trait NaiveEvaluation extends EvaluationStrategy {
  def horizontal(board: GameBoard, i: Int)     = for (j <- 0 until board.cols)
                                                   yield board(i)(j)
                                               
  def vertical(board: GameBoard, j: Int)       = for (i <- 0 until board.rows)
                                                   yield board(i)(j)

  def diagonallyUp(board: GameBoard, i: Int)   = for (k <- 0 until board.cols if board.contains(i + k)(k))
                                                   yield board(i + k)(k)

  def diagonallyDown(board: GameBoard, i: Int) = for (k <- 0 until board.cols if board.contains(i - k)(k))
                                                   yield board(i - k)(k)

  def utility(board: GameBoard) = {
    board.winner match {
      case White => POSITIVE_INFINITY
      case Black => NEGATIVE_INFINITY
      case _     => {
        val pattern = """X+|O+""".r
        val whites  = new Array[Int](board.connections - 1)
        val blacks  = new Array[Int](board.connections - 1)

        def weigh(s: String) = {
            pattern.findAllIn(s).foreach { m =>
            valueOf(m.toString.charAt(0).toString) match {
              case Some(White) => whites(m.length) += 1
              case Some(Black) => blacks(m.length) += 1
              case _           => ()
            }
          }
        }

        for (i <- 0 until board.rows)           weigh(horizontal(board, i).mkString)
        for (j <- 0 until board.cols)           weigh(vertical(board, j).mkString)
        for (i <- -board.rows until board.rows) weigh(diagonallyUp(board, i).mkString)
        for (j <- 0 until 2*board.rows)         weigh(diagonallyDown(board, j).mkString)

        whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
        blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
      }
    }
  }
}