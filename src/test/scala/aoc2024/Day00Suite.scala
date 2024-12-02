package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day00.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day00Suite extends AoCFunSuiteBase {

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 0)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    assertEquals(result, 0)
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
