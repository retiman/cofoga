package cofoga

trait Search extends Utility with Logged {
  def plies: Int
  def halfPlies: Int = 2 * plies
  def search(board: GameBoard): Int
  def terminal(board: GameBoard, depth: Int): Boolean
}