package aoc2024

import kyo.*

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

  def isSafe_(report: Report): Boolean = {
    @tailrec
    def go(f: (Int, Int) => Boolean)(h: Int, levels: List[Int]): Boolean =
      levels match
        case head :: next =>
          if f(h, head) then go(f)(head, next)
          else false
        case _ => true

    val head = report.levels.head
    val tail = report.levels.tail.toList

    val increasing = go(_ <= _)(head, tail)
    if increasing
    then go((a, b) => b - a <= 3 && b - a > 0)(head, tail)
    else
      val decreasing = go(_ >= _)(head, tail)
      if decreasing
      then go((a, b) => a - b <= 3 && a - b > 0)(head, tail)
      else false
  }

  def isSafe(report: Report): Boolean = {
    def check(increasing: Boolean, levels: Seq[Int]): Boolean = {
      val ok: (Int, Int) => Boolean =
        if increasing
        then (a, b) => b - a <= 3 && b - a > 0
        else (a, b) => a - b <= 3 && a - b > 0
      levels.zip(levels.drop(1)).forall(ok.tupled)
    }

    report.levels.toList match
      case a :: b :: _ if a < b => check(true, report.levels)
      case a :: b :: _ if a > b => check(false, report.levels)
      case _                    => false
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
