import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import aoc2024.Day01

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day01Suite extends munit.FunSuite {
  import Day01.*

  test("read lists") {
    val lists = readLists(Seq("1 2", "3 4"))
    assertEquals(lists, (Seq(1, 3), Seq(2, 4)))
  }

  test("pair up") {
    val pairs = pairUp(Seq(1, 2), Seq(4, 3))
    assertEquals(pairs, Seq((1, 3), (2, 4)))
  }

  test("sample 1") {
    var result = IO.Unsafe.run {
      inputLines(Sample(Part1(1))).map(doPart1)
    }.eval
    assertEquals(result, 11)
  }

  test("part 1") {
    var result = IO.Unsafe.run {
      inputLines(Part1(1)).map(doPart1)
    }.eval
    assertEquals(result, 1222801)
  }
}
