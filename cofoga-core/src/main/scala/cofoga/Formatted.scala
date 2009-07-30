package cofoga

import Player._

trait Formatted extends Matrix {
  override def toString = {
    val s = new StringBuilder
    for (i <- (rows - 1) to 0 by -1) {
      for (j <- 0 until cols) {
        if (j == 0) s.append(i + " ")
        s.append(" " + matrix(i)(j).format)
        if (j < cols - 1) s.append(" ")
      }
      s.append("\n")
    }
    s.append("   ")
    for (j <- 0 until cols) {
      s.append(j)
      if (j < cols - 1) s.append("  ")
    }
    s.append("\n")
    s.toString
  }
}
