package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day04.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day04Suite extends AoCFunSuiteBase {

  test("overlapping") {
    var count = countMatches("hello")(raw"(?=(\w\w))".r)
    assertEquals(count, 4)
  }

  // testInput("debug sample 1", sample1) { lines =>
  //   val n = lines.head.length()
  //   val patterns = buildPatterns(n)
  //   val text = lines.mkString
  //   val counts = patterns.map(p => (p, countMatches(text)(p)))
  //   assertEquals(counts, Seq())
  // }

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 18)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    // assertEquals(result, 2363) // too high
    assertEquals(result, 2336) // too high
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
