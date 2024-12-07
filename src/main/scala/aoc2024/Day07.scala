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
    import Operator.*
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

  def eval(ops: Seq[Operator], operands: Seq[Int]): Long = {
    ops.zip(operands.tail).foldLeft(operands.head + 0L) {
      case (acc, (Add, v))  => acc + v
      case (acc, (Mult, v)) => acc * v
    }
  }

  def canBeMadeTrue(eq: Equation): Boolean = {
    val ops = possibleOperators(eq.operands.size - 1)
    ops.exists { ops => eval(ops, eq.operands) == eq.value }
  }

  def part1(lines: Seq[String]): Long = {
    val eqs = parseEquations(lines)
    val possible = eqs.filter(canBeMadeTrue)
    possible.map(_.value).sum
  }

  enum Operator2:
    case Add
    case Mult
    case Concat

  def possibleOperators2(n: Int): Seq[Seq[Operator2]] = {
    import Operator2.*
    Loop(0, Seq(Seq.empty[Operator2])) { (i, lists) =>
      if i < n
      then
        val lists1 = lists.flatMap { list =>
          Seq(list :+ Add, list :+ Mult, list :+ Concat)
        }
        Loop.continue(i + 1, lists1)
      else Loop.done(lists)
    }.eval
  }

  def eval2(ops: Seq[Operator2], operands: Seq[Int]): Long = {
    import Operator2.*
    ops.zip(operands.tail).foldLeft(operands.head + 0L) {
      case (acc, (Add, v))    => acc + v
      case (acc, (Mult, v))   => acc * v
      case (acc, (Concat, v)) => (acc.toString() + v.toString()).toLong
    }
  }

  def canBeMadeTrue2(eq: Equation): Boolean = {
    val ops = possibleOperators2(eq.operands.size - 1)
    ops.exists { ops => eval2(ops, eq.operands) == eq.value }
  }

  def part2(lines: Seq[String]): Long = {
    val eqs = parseEquations(lines)
    val possible = eqs.filter(canBeMadeTrue2)
    possible.map(_.value).sum
  }
}
