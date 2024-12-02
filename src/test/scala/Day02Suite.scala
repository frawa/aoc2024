import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import aoc2024.Day02

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day02Suite extends munit.FunSuite {
  import Day02.*

  test("safe report") {
    val safe = isSafe(Report(Seq(7, 6, 4, 2, 1)))
    assertEquals(safe, true)
  }

  test("unsafe report") {
    val safe = isSafe(Report(Seq(1, 2, 7, 8, 9)))
    assertEquals(safe, false)
  }

  test("unsafe reports") {
    val safe = isSafe(Report(Seq(9, 7, 6, 2, 1)))
    assertEquals(safe, false)
  }

  test("sample 1") {
    var result = IO.Unsafe.run {
      inputLines(Sample(Part1(2))).map(doPart1)
    }.eval
    assertEquals(result, 2)
  }

  test("part 1") {
    var result = IO.Unsafe.run {
      inputLines(Part1(2)).map(doPart1)
    }.eval
    assertEquals(result, 321)
  }

  test("sample 2") {
    var result = IO.Unsafe.run {
      inputLines(Sample(Part1(2))).map(doPart2)
    }.eval
    assertEquals(result, 4)
  }

  test("part 2 ") {
    var result = IO.Unsafe.run {
      inputLines(Part1(2)).map(doPart2)
    }.eval
    assertEquals(result, 386)
  }
}
