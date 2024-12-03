package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*

object Day03 {

  val input1 = Part1(3)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = Sample(Part2(3))

  def matchOperands(text: String): Seq[(Int, Int)] = {
    val regex = raw"mul\((\d{1,3}),(\d{1,3})\)".r

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

  def calc(operands: Seq[(Int, Int)]): Int =
    operands.map(_ * _).sum

  def part1(lines: Seq[String]): Int = {
    calc(matchOperands(lines.mkString))
  }

  def keepDos(text: String): String = {
    text.replaceAll(raw"don't\(\).*?do\(\)", "")
  }

  def part2(lines: Seq[String]): Int = {
    calc(matchOperands(keepDos(lines.mkString)))
  }
}
