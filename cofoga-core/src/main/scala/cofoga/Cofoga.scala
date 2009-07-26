package cofoga

object Cofoga {
  val ROWS = 6
  val COLS = 7
  val CXNS = 4

  implicit def pairWrapper[A, B](a: Pair[A, B]) = new {
    def fst = a._1
    def snd = a._2
  }

  def rescue[E](expr: => E) = new {
    def using[A >: E](alt: A) = try { if (expr == null) alt else expr } catch {
      case e: Exception => alt
    }
  }
}