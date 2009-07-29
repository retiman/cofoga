package cofoga

import scala.collection.mutable.HashMap
import Player._

trait Vectored extends Logged {
  type Point = Pair[Int, Int]
  type Threat = Array[Point]
  type Threats = Array[Threat]
  protected val threats = new HashMap[Point, Threats]()
  protected def rows: Int
  protected def cols: Int
  protected def connections: Int
  protected def matrix: Array[Array[Player]]
  protected def containsRow(row: Int) = 0 until rows contains row
  protected def containsCol(col: Int) = 0 until cols contains col
  protected def contains(row: Int)(col: Int) = containsRow(row) && containsCol(col)
  computeThreats()

  def computeThreats() = {
    for (i <- 0 until rows; j <- 0 until cols) {
      val ts = directions.map(f => f(i)(j)(connections))
                         .filter(_.length == connections)
                         .map(_.toArray)
                         .toArray
      threats((i, j)) = ts
      //log.info("Found threats at " + (i, j) + ": " + ts.map(_.toList).toList)
    }
  }

  def directions = List(lr _, du _, ur _, dr _, rl _, ud _ ,dl _, ul _)
  
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
  }

  def rl(row: Int)(col: Int)(end: Int) = {
    for (j <- col until col - end by -1 if containsCol(j))
      yield (row, j)
  }

  def ud(row: Int)(col: Int)(end: Int) = {
    for (i <- row until row - end by -1 if containsRow(i))
      yield (i, col)
  }

  def dl(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row - k)(col - k))
      yield (row - k, col - k)
  }

  def ul(row: Int)(col: Int)(end: Int) = {
    for (k <- 0 until end if contains(row + k)(col - k))
      yield (row + k, col - k)
  }
}