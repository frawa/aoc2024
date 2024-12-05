package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day05.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day05Suite extends AoCFunSuiteBase {

  testInput("build print queue", sample1) { lines =>
    var result = buildPrintQueue(lines)
    assertEquals(result.rules.size, 21)
    assertEquals(result.updates.size, 6)
  }

  test("in right order") {
    val updates = Seq(13, 42)
    val rules = Set((13, 42))
    assertEquals(inRightOrder(rules)(updates), true)
  }

  test("not in right order") {
    val updates = Seq(42, 13)
    val rules = Set((13, 42))
    assertEquals(inRightOrder(rules)(updates), false)
  }

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 143)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    assertEquals(result, 6612)
  }

  testInput("sample 2", sample2) { lines =>
    var result = part2(lines)
    assertEquals(result, 0)
  }

  testInput("part 2", input2) { lines =>
    var result = part2(lines)
    assertEquals(result, 0)
  }
}
