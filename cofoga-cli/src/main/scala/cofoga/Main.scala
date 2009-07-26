package cofoga

import cofoga.util.Logged
import cofoga.engine.Settings
import cofoga.engine.Engine
import cofoga.Cofoga._
import cofoga.Player._

object Main extends Application with Logged {
  override def main(args: Array[String]): Unit = {
    var settings = new Settings()
    val iter = args.elements
    while (iter.hasNext) {
      iter.next match {
        case "--rows"  => try { settings.rows = iter.next.toInt } catch { case _: Exception => error }
        case "--cols"  => try { settings.cols = iter.next.toInt } catch { case _: Exception => error }
        case "--cxns"  => try { settings.connections = iter.next.toInt } catch { case _: Exception => error }
        case "--plies" => try { settings.plies = iter.next.toInt } catch { case _: Exception => error }
        case "--start" => try {
                            iter.next.toString match {
                              case "W" => settings.player = White
                              case "B" => settings.player = Black
                              case _   => error
                            }
                          }
                          catch { case _: Exception => error }
        case _         => error
      }
    }

    val engine = new Engine(settings) with cofoga.eval.NaiveEvaluation
                                      with cofoga.search.MinMaxSearch
    println("Initialized new game with\n" + settings)
    println("Type \"quit\" to exit")

    do {
      println(engine)
      if (engine.turn == settings.player) {
        var input = ""
        var m = -1
        while (m == -1) {
          println("Legal moves are: " + engine.legalMoves.mkString(", "))
          print("Enter a legal move: ")
          input = readLine
          input.trim match {
            case "quit" => exit(1)
            case input  => rescue {
                             m = input.toInt
                             if (!engine.legalMoves.contains(m))
                               m = -1
                           } using ()
          }
        }
        engine.move(m)
      }
      else {
        engine.respond()
      }
    } while (engine.winner == Neither)
    println(engine)
    println("The winner was " + format(engine.winner) + ".")
  }

  def error() = {
    println(help)
    exit(1)
  }
  
  def help = """DESCRIPTION
               |Cofoga plays a game of Connect 4 with variable sized rows,
               |columns, and connections (you could play Connect 5 if you
               |wanted to).
               |
               |OPTIONS
               |--rows NUM   Sets the number of rows (default: 6)
               |--cols NUM   Sets the number of columns (default: 7)
               |--cxns NUM   Sets the number of connections (default: 4)
               |--start W|B  Start as White or Black (default: White)
               |--plies NUM  Sets the number of plies for the AI (default: 8)""".stripMargin
}