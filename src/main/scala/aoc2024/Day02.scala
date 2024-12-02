package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*
import scala.annotation.tailrec

object Day02 {

  val input1 = Part1(2)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  case class Report(levels: Seq[Int])

  def readReports(lines: Seq[String]): Seq[Report] = {
    lines.map { line =>
      val levels = line.split(" ").map(_.toInt)
      Report(levels)
    }
  }

  def isSafe(report: Report): Boolean = {
    @tailrec
    def go(f: (Int, Int) => Boolean)(h: Int, levels: List[Int]): Boolean =
      levels match
        case head :: next =>
          if f(h, head) then go(f)(head, next)
          else false
        case _ => true

    val increasing = go(_ <= _)(report.levels.head, report.levels.tail.toList)
    if increasing
    then
      go((a, b) => b - a <= 3 && b - a > 0)(
        report.levels.head,
        report.levels.tail.toList
      )
    else
      val decreasing = go(_ >= _)(report.levels.head, report.levels.tail.toList)
      if decreasing
      then
        go((a, b) => a - b <= 3 && a - b > 0)(
          report.levels.head,
          report.levels.tail.toList
        )
      else false
  }

  def part1(lines: Seq[String]): Int = {
    readReports(lines).count(isSafe)
  }

  def isTolerated(report: Report): Boolean = {
    @tailrec
    def go(prefix: List[Int], levels: List[Int]): Boolean =
      levels match
        case head :: tail =>
          isSafe(Report(prefix ++ tail)) || go(prefix :+ head, tail)
        case _ => false
    go(List.empty, report.levels.toList)
  }

  def part2(lines: Seq[String]): Int = {
    val reports = readReports(lines)
    val unsafes = reports.filterNot(isSafe)
    val safes = reports.size - unsafes.size
    val tolerated = unsafes.count(isTolerated)
    safes + tolerated
  }
}
