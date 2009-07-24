package cofoga

import cofoga.engine.Settings
import cofoga.engine.Engine
import cofoga.Player._

object Main extends Application {
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
    println("Initialized new game with: " + settings)
  }

  def error = {
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