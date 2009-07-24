package cofoga

import scala.util.logging.Logged

object Player extends Enumeration with Logged {
  type Player = Value
  val Neither, White, Black = Value

  implicit def valueWrapper(player: Player) = new {
    def occupied = player match {
      case Neither => false
      case _       => true
    }

    def value = player match {
      case White => 1
      case Black => -1
      case _     => 0
    }

    def switch = player match {
      case White => Black
      case Black => White
      case _     => require(false, "Can only switch White to Black or vice versa")
                    player
    }
  
    def format = player match {
      case White => "O"
      case Black => "X"
      case _     => "-"
    }
  }

  def format(player: Player) = player match {
    case White => "White"
    case Black => "Black"
    case _     => "Neither"
  }

  override def valueOf(s: String): Option[Player] = s match {
    case "O" => Some(White)
    case "X" => Some(Black)
    case _   => None
  }

  def valueOf(c: Char): Option[Player] = valueOf(c.toString)
}