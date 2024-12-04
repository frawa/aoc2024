package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*
import scala.util.matching.Regex

object Day04 {

  val input1 = Part1(4)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  def countMatches(text: String)(regex: Regex): Int = {
    regex.findAllMatchIn(text).size
  }

  def buildPatterns(n: Int): Seq[Regex] = {
    def dot(i: Int) = s".{${i}}"
    Seq(
      raw"(?=(XMAS))".r,
      raw"(?=(SAMX))".r,
      raw"(?=(X${dot(n - 1)}M${dot(n - 1)}A${dot(n - 1)}S))".r,
      raw"(?=(S${dot(n - 1)}A${dot(n - 1)}M${dot(n - 1)}X))".r,
      raw"(?=(X${dot(n)}M${dot(n)}A${dot(n)}S))".r,
      raw"(?=(S${dot(n)}A${dot(n)}M${dot(n)}X))".r,
      raw"(?=(X${dot(n - 2)}M${dot(n - 2)}A${dot(n - 2)}S))".r,
      raw"(?=(S${dot(n - 2)}A${dot(n - 2)}M${dot(n - 2)}X))".r
    )
  }

  def part1(lines: Seq[String]): Int = {
    val n = lines.head.length()
    val patterns = buildPatterns(n + 1)
    val text = lines.mkString(" ")
    patterns.map(countMatches(text)).sum
  }

  def buildStarPatterns(n: Int): Seq[Regex] = {
    val dots = s".{${n - 3}}"
    def star1 = s"""|M.S${dots}
                   |.A.${dots}
                   |M.S
                   |""".stripMargin.split("\n").mkString
    def star2 = s"""|S.M${dots}
                    |.A.${dots}
                    |S.M
                    |""".stripMargin.split("\n").mkString
    def star3 = s"""|M.M${dots}
                    |.A.${dots}
                    |S.S
                    |""".stripMargin.split("\n").mkString
    def star4 = s"""|S.S${dots}
                    |.A.${dots}
                    |M.M
                    |""".stripMargin.split("\n").mkString
    Seq(
      raw"(?=(${star1}))".r,
      raw"(?=(${star2}))".r,
      raw"(?=(${star3}))".r,
      raw"(?=(${star4}))".r
    )
  }

  def part2(lines: Seq[String]): Int = {
    val n = lines.head.length()
    val patterns = buildStarPatterns(n + 1)
    val text = lines.mkString(" ")
    patterns.map(countMatches(text)).sum
  }
}
