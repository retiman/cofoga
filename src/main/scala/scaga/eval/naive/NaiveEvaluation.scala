package scaga.eval.naive

import scaga.eval.EvaluationStrategy
import scaga.Player._
import scaga.Predef._

trait NaiveEvaluation extends EvaluationStrategy {
  type Threat = List[Pair[Int, Int]]
  val pattern = """X+|O+""".r

  def score = {
    val whites = new Array[Int](board.connections - 1)
    val blacks = new Array[Int](board.connections - 1)

    def compute(s: String) = pattern.findAllIn(s).foreach { m =>
      valueOf(m.toString.charAt(0).toString) match {
        case Some(White) => whites(m.length) += 1
        case Some(Black) => blacks(m.length) += 1
        case _           => ()
      }
    }

    // Compute horizontal threats
    for (i <- 0 until board.rows) {
      val r = for (j <- 0 until board.cols) yield board(i)(j)
      compute(r.mkString)
    }

    // Compute vertical threats
    for (j <- 0 until board.cols) {
      val r = for (i <- 0 until board.rows) yield board(i)(j)
      compute(r.mkString)
    }

    // Compute up diagonal threats
    val limit = board.rows max board.cols
    for (i <- (board.cols - 1) to 0 by -1) {
      val r = for (k <- 0 until limit if
                   (0 until board.rows contains i + k) &&
                   (0 until board.cols contains k))
                yield board(i + k)(k)
      compute(r.mkString)
    }

    // Compute down diagonal threats
    for (j <- 0 until board.cols) {
      val r = for (k <- 0 until limit if
                   (0 until board.rows contains k) &&
                   (0 until board.cols contains j - k))
                yield board(k)(j - k)
      compute(r.mkString)
    }

    whites.indices.map(i => Math.pow(3, i) * whites(i)).reduceLeft(_+_) -
    blacks.indices.map(i => Math.pow(3, i) * blacks(i)).reduceLeft(_+_)
  }
}