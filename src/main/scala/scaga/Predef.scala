package scaga

object Predef {
  def rescue[E](expr: => E) = new {
    def using[A >: E](alt: A) = try { if (expr == null) alt else expr } catch {
      case e: Exception => alt
    }
  }
}