package cofoga.utility

import scala.collection.mutable.HashMap
import Cofoga._
import Player._

trait NaiveUtility extends Utility {
  val pattern = """X+|O+""".r
  type Point = Pair[Int, Int]
  type Threat = Array[Point]
  type Threats = Array[Threat]
  protected val threats = new HashMap[Point, Threats]()
/*
  computeThreats()

  def computeThreats() = {
    for (i <- 0 until rows; j <- 0 until cols) {
      val ts = directions.map(f => f(i)(j)(connections))
                         .filter(_.length == connections)
                         .map(_.toArray)
                         .toArray
      threats((i, j)) = ts
      log.info("Found threats at " + (i, j) + ": " + ts.map(_.toList).toList)
    }
  }

  def directions = List(lr _, du _, ur _, dr _)

  def lr(row: Int)(col: Int)(end: Int) = {
    for (j <- col until col + end if containsCol(j))
      yield (row, j)
  }

  def du(row: Int)(col: Int)(end: Int) = {
    for (i <- row until row + end if containsRow(i))
      yield (i, col)
  }

  def ur(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row + k)(col + k))
      yield (row + k, col + k)
  }

  def dr(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row - k)(col + k))
      yield (row - k, col + k)
  }*/

  def utility = {
    val score = board.winner match {
      case White => POSITIVE_INFINITY
      case Black => NEGATIVE_INFINITY
      case _     => eval(board)
    }
    log.info("Evaluated a utility of " + score)
    score
  }

  def eval(board: GameBoard): Double = {
    0
    /*
    val whites  = new Array[Int](board.connections - 1)
    val blacks  = new Array[Int](board.connections - 1)
    horizontalEvaluation(board, whites, blacks)
    verticalEvaluation(board, whites, blacks)
    diagupEvaluation(board, whites, blacks)
    diagdownEvaluation(board, whites, blacks)
    whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
    blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
    */
  }

  /*
  def weigh(players: String, whites: Array[Int], blacks: Array[Int]) = {
    pattern.findAllIn(players).foreach { m =>
      valueOf(m(0)) match {
        case Some(White) => whites(m.size - 1) += 1
        case Some(Black) => blacks(m.size - 1) += 1
        case _           => ()
      }
    }
  }

  def horizontalEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (i <- 0 until board.rows) {
      val players = board._horizontal(i, 0)(board.cols).map(_.format).mkString
      log.debug("Evaluating horizontal at row " + i + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def verticalEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- 0 until board.cols) {
      val players = board._vertical(0, j)(board.rows).map(_.format).mkString
      log.debug("Evaluating vertical at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def diagupEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- -board.cols until board.cols) {
      val players = board._diagup(0, j)(board.rows).map(_.format).mkString
      log.debug("Evaluating diagup at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }

  def diagdownEvaluation(board: GameBoard, whites: Array[Int], blacks: Array[Int]) = {
    for (j <- -board.cols until board.cols) {
      val players = board._diagdown(board.rows - 1, j)(board.rows).map(_.format).mkString
      log.debug("Evaluating diagdown at col " + j + ": " + players)
      weigh(players, whites, blacks)
    }
  }
  */
}