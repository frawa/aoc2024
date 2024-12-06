package aoc2024

import kyo.*

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

  // def inRightOrder(rules: Set[(Int, Int)])(queue: Seq[Int]): Boolean = {
  //   def ok(before: Int, tail: Seq[Int]): Boolean =
  //     tail.forall(after => !rules.contains((after, before)))

  //   @tailrec
  //   def go(pages: List[Int]): Boolean =
  //     pages match
  //       case p :: ps => ok(p, ps) && go(ps)
  //       case _       => true

  //   go(queue.toList)
  // }

  def inRightOrder(rules: Set[(Int, Int)])(queue: Seq[Int]): Boolean = {
    def ok(before: Int, tail: Seq[Int]): Boolean =
      tail.forall(after => !rules.contains((after, before)))

    Loop(queue.toList) { pages =>
      pages match
        case p :: ps =>
          if ok(p, ps)
          then Loop.continue(ps)
          else Loop.done(false)
        case _ => Loop.done(true)
    }.eval
  }

  def part1(lines: Seq[String]): Int = {
    val PrintQueue(rules, updates) = buildPrintQueue(lines)
    val corrects = updates
      .map(update => IO(Some(update).filter(inRightOrder(rules))))

    val calc = Async.parallel(4)(corrects)

    import AllowUnsafe.embrace.danger
    val result = KyoApp.Unsafe.runAndBlock(13.seconds)(calc)
    result
      .map { corrects =>
        corrects.flatMap { updates =>
          updates.map(updates => updates(updates.size / 2))
        }.sum
      }
      .getOrElse(0)
  }

  def fixRightOrder(rules: Set[(Int, Int)])(queue: Array[Int]): Array[Int] = {
    queue.sortWith((a, b) => rules.contains(a, b)).toArray
  }

  def part2(lines: Seq[String]): Int = {
    val PrintQueue(rules, updates) = buildPrintQueue(lines)
    updates
      .filterNot(inRightOrder(rules))
      .map(fixRightOrder(rules))
      .map { updates =>
        updates(updates.size / 2)
      }
      .sum
  }
}
