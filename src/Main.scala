package cofoga

import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options
import org.apache.commons.cli.ParseException
import org.apache.commons.cli.PosixParser
import engine.Settings
import engine.Engine
import Cofoga._
import Player._

object Main extends Application with Logged {
  val settings = new Settings()
  val help = new HelpFormatter()
  val options = new Options()
  val parser = new PosixParser()

  override def main(args: Array[String]): Unit = {
    options.addOption("r", "rows", true, "set the number of rows in the game board")
    options.addOption("c", "cols", true, "set the number of columns in the game board")
    options.addOption("x", "connections", true, "set the number of pieces that must be connected for a win")
    options.addOption("p", "plies", true, "set the number of plies (depth) to search")
    options.addOption("s", "start", true, "set the player you want to play as")

    try {
      val cmd = parser.parse(options, args)
      if (cmd hasOption "r") settings.rows        = cmd.getOptionValue("r").toInt
      if (cmd hasOption "c") settings.cols        = cmd.getOptionValue("c").toInt
      if (cmd hasOption "x") settings.connections = cmd.getOptionValue("x").toInt
      if (cmd hasOption "p") settings.plies       = cmd.getOptionValue("p").toInt
      if (cmd hasOption "s") settings.player      = Player.valueOf(cmd.getOptionValue("s")).getOrElse(White)
    }
    catch {
      case e: NumberFormatException => error()
      case e: ParseException        => error()
    }

    val engine = new Engine(settings) with cofoga.utility.NaiveUtility
                                      with cofoga.search.MinMaxSearch { val board = this }
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
    help.printHelp("cofoga", options)
    System.exit(1)
  }
}