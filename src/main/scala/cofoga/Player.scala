package cofoga

object Player extends Enumeration {
  type Player = Value
  val Empty, White, Black = Value

  implicit def valueWrapper(player: Player) = new {
    def occupied = player match {
      case Empty => false
      case _     => true
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

  override def valueOf(s: String) = s match {
    case "O" => Some(White)
    case "X" => Some(Black)
    case _   => None
  }
}