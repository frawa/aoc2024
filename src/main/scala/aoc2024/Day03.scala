package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*

object Day03 {

  val input1 = Part1(3)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  val regex = raw"mul\((\d{1,3}),(\d{1,3})\)".r

  def matchOperands(text: String): Seq[(Int, Int)] = {
    regex
      .findAllMatchIn(text)
      .flatMap { m =>
        for
          op1 <- m.group(1).toIntOption
          op2 <- m.group(2).toIntOption
        yield (op1, op2)
      }
      .toSeq
  }

  def part1(lines: Seq[String]): Int = {
    matchOperands(lines.mkString).map(_ * _).sum
  }

  def part2(lines: Seq[String]): Int = {
    0
  }
}
