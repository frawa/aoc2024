package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
object Day01 {

  val input1 = Part1(1)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  def readLists(lines: Seq[String]): (Seq[Int], Seq[Int]) = {
    var leftRight = raw"(\d+) +(\d+)".r
    var both = lines.map { line =>
      line match {
        case leftRight(left, right) => (left.toInt, right.toInt)
      }
    }
    val left = both.map(_._1)
    val right = both.map(_._2)
    return (left, right)
  }

  def pairUp(left: Seq[Int], right: Seq[Int]): Seq[(Int, Int)] =
    left.sorted.zip(right.sorted)

  def part1(lines: Seq[String]): Int = {
    val (l, r) = readLists(lines)
    pairUp(l, r).map(_ - _).map(Math.abs).sum
  }

  def part2(lines: Seq[String]): Int = {
    val (left, right) = readLists(lines)

    val total = left.map { l =>
      val occurences = right.count(_ == l)
      val score = l * occurences
      score
    }.sum

    total
  }
}
