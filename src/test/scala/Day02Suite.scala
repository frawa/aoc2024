import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import aoc2024.Day02

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day02Suite extends AoCFunSuiteBase {
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

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 2)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    assertEquals(result, 321)
  }

  testInput("sample 2", sample2) { lines =>
    var result = part2(lines)
    assertEquals(result, 4)
  }

  testInput("part 2", input2) { lines =>
    var result = part2(lines)
    assertEquals(result, 386)
  }
}
