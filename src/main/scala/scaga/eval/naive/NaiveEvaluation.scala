package scaga.eval.naive

import scaga.eval.EvaluationStrategy
import scaga.Player._
import scaga.Predef._

trait NaiveEvaluation extends EvaluationStrategy {
  type Threat = List[Pair[Int, Int]]
  val pattern = """X+|O+""".r
  val limit   = board.rows max board.cols

  def horizontalThreats(i: Int)      = for (j <- 0 until board.cols) yield board(i)(j)
  def verticalThreats(j: Int)        = for (i <- 0 until board.rows) yield board(i)(j)
  def diagonallyUpThreats(i: Int)    = for (k <- 0 until limit if board.contains(i + k)(k)) yield board(i + k)(k)
  def diagonallyDownThreats(j: Int)  = for (k <- 0 until limit if board.contains(k)(j - k)) yield board(k)(j - k)

  def score = {
    val whites = new Array[Int](board.connections - 1)
    val blacks = new Array[Int](board.connections - 1)

    def weigh(s: String) = pattern.findAllIn(s).foreach { m =>
      valueOf(m.toString.charAt(0).toString) match {
        case Some(White) => whites(m.length) += 1
        case Some(Black) => blacks(m.length) += 1
        case _           => ()
      }
    }

    for (i <- 0 until board.rows)          weigh(horizontalThreats(i).mkString)
    for (j <- 0 until board.cols)          weigh(verticalThreats(j).mkString)
    for (i <- (board.cols - 1) to 0 by -1) weigh(diagonallyUpThreats(i).mkString)
    for (j <- 0 until board.cols)          weigh(diagonallyDownThreats(j).mkString)

    whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
    blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
  }
}