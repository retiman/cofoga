package cofoga

trait Search extends Utility with Logged {
  def plies: Int
  def halfPlies: Int = 2 * plies
  def search(board: CofogaBoard): Int
  def terminal(board: CofogaBoard, depth: Int): Boolean
}