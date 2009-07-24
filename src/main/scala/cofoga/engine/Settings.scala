package cofoga.engine

import cofoga.Player._
import cofoga.board.GameBoard._

class Settings {
  var rows = ROWS
  var cols = COLS
  var connections = CXNS
  var player = White
  var plies = 8
  override def toString = {
    "Rows: " + rows + "\n" +
    "Columns: " + cols + "\n" +
    "Connections: " + connections + "\n" +
    "Plies: " + plies + "\n" +
    "Player: " + format(player) + "\n"
  }
}