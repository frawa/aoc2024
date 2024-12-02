package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day01.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day01Suite extends AoCFunSuiteBase {

  test("read lists") {
    val lists = readLists(Seq("1 2", "3 4"))
    assertEquals(lists, (Seq(1, 3), Seq(2, 4)))
  }

  test("pair up") {
    val pairs = pairUp(Seq(1, 2), Seq(4, 3))
    assertEquals(pairs, Seq((1, 3), (2, 4)))
  }

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 11)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    assertEquals(result, 1222801)
  }

  testInput("sample 2", sample2) { lines =>
    var result = part2(lines)
    assertEquals(result, 31)
  }

  testInput("part 2", input2) { lines =>
    var result = part2(lines)
    assertEquals(result, 22545250)
  }
}
