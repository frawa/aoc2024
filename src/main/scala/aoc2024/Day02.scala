package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*

object Day02 {
  // def part1(): Int = {
  //   IO.Unsafe.run {
  //     inputLines(Part1(1)).map(doPart1)
  //   }.eval
  // }

  case class Report(levels: Seq[Int])

  def readReports(lines: Seq[String]): Seq[Report] = {
    lines.map { line =>
      val levels = line.split(" ").map(_.toInt)
      Report(levels)
    }
  }

  def isSafe(report: Report): Boolean = {
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
      val dereasing = go(_ >= _)(report.levels.head, report.levels.tail.toList)
      if dereasing
      then
        go((a, b) => a - b <= 3 && a - b > 0)(
          report.levels.head,
          report.levels.tail.toList
        )
      else false
  }

  def doPart1(lines: Seq[String]): Int = {
    readReports(lines).count(isSafe)
  }

  def isTolerated(report: Report): Boolean = {
    def go(prefix: List[Int], levels: List[Int]): Boolean =
      levels match
        case head :: tail =>
          isSafe(Report(prefix ++ tail)) || go(prefix :+ head, tail)
        case _ => false
    go(List.empty, report.levels.toList)
  }

  def doPart2(lines: Seq[String]): Int = {
    val reports = readReports(lines)
    val unsafes = reports.filterNot(isSafe)
    val safes = reports.size - unsafes.size
    val tolerated = unsafes.count(isTolerated)
    safes + tolerated
  }
}