package aoc2024

import kyo.*
import scala.io.Source

object Util {

  enum InputSpec {
    case Part1(day: Int) extends InputSpec
    case Part2(day: Int) extends InputSpec
    case Sample(input: InputSpec) extends InputSpec
  }

  def inputLines(input: InputSpec): Seq[String] < IO = {
    Path("data", dataName(input) + ".txt").readLines
  }

  def dataName(input: InputSpec): String = {
    input match
      case InputSpec.Part1(day)    => f"day$day%02d.part1"
      case InputSpec.Part2(day)    => f"day$day%02d.part2"
      case InputSpec.Sample(input) => dataName(input) + ".sample"
  }
}
