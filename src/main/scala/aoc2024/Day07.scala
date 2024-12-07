package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*

object Day07 {

  val input1 = Part1(7)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  case class Equation(value: Long, operands: Seq[Int])

  def parseEquations(lines: Seq[String]): Seq[Equation] = {
    lines.map { line =>
      val split = line.split(":")
      val value = split.head.toLong
      val operands =
        split.tail.flatMap(_.split(" ")).filter(_.nonEmpty).map(_.toInt)
      Equation(value, operands)
    }
  }

  enum Operator:
    case Add
    case Mult
  import Operator.*

  def possibleOperators(n: Int): Seq[Seq[Operator]] = {
    Loop(0, Seq(Seq.empty[Operator])) { (i, lists) =>
      if i < n
      then
        val lists1 = lists.flatMap { list =>
          Seq(list :+ Add, list :+ Mult)
        }
        Loop.continue(i + 1, lists1)
      else Loop.done(lists)
    }.eval
  }

  def canBeMadeTrue(eq: Equation): Boolean = {
    val ops = possibleOperators(eq.operands.size - 1)
    ops.exists { ops =>
      val eval = ops.zip(eq.operands.tail).foldLeft(eq.operands.head + 0L) {
        case (acc, (Add, v))  => acc + v
        case (acc, (Mult, v)) => acc * v
      }
      eq.value == eval
    }
  }

  def part1(lines: Seq[String]): Long = {
    val eqs = parseEquations(lines)
    val possible = eqs.filter(canBeMadeTrue)
    possible.map(_.value).sum
  }

  def part2(lines: Seq[String]): Int = {
    0
  }
}
