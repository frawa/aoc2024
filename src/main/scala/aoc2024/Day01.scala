package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*

object Day01 {
  def part1(): Int = {
    IO.Unsafe.run {
      inputLines(Part1(1)).map(doPart1)
    }.eval
  }

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

  def doPart1(lines: Seq[String]): Int = {
    val (l, r) = readLists(lines)
    pairUp(l, r).map(_ - _).map(Math.abs).sum
  }

  def doPart2(lines: Seq[String]): Int = {
    val (left, right) = readLists(lines)

    val total = left.map { l =>
      val occurences = right.count(_ == l)
      val score = l * occurences
      score
    }.sum

    total
  }
}
