package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*
import scala.annotation.tailrec

object Day05 {

  val input1 = Part1(5)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  case class PrintQueue(rules: Set[(Int, Int)], updates: Seq[Array[Int]])

  def buildPrintQueue(lines: Seq[String]): PrintQueue = {
    val pair = raw"(\d+)\|(\d+)".r
    val rules = lines
      .takeWhile(_.nonEmpty)
      .collect { case pair(before, after) =>
        (before.toInt, after.toInt)
      }
      .toSet
    val updates = lines.dropWhile(_.nonEmpty).drop(1).map { line =>
      line.split(",").flatMap(_.toIntOption)
    }
    PrintQueue(rules, updates)
  }

  def inRightOrder(rules: Set[(Int, Int)])(queue: Seq[Int]): Boolean = {
    def ok(before: Int, tail: Seq[Int]): Boolean =
      tail.forall(after => !rules.contains((after, before)))

    @tailrec
    def go(pages: List[Int]): Boolean =
      pages match
        case p :: ps => ok(p, ps) && go(ps)
        case _       => true

    go(queue.toList)
  }

  def part1(lines: Seq[String]): Int = {
    val PrintQueue(rules, updates) = buildPrintQueue(lines)
    updates
      .filter(inRightOrder(rules))
      .map { updates =>
        updates(updates.size / 2)
      }
      .sum
  }

  def part2(lines: Seq[String]): Int = {
    0
  }
}
