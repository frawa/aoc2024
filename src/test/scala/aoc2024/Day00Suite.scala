package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day00.*

class Day00Suite extends AoCFunSuiteBase {

  testInput("sample 1", sample1) { lines =>
    val result = part1(lines)
    assertEquals(result, 0)
  }

  testInput("part 1", input1) { lines =>
    val result = part1(lines)
    assertEquals(result, 0)
  }

  testInput("sample 2", sample2) { lines =>
    val result = part2(lines)
    assertEquals(result, 0)
  }

  testInput("part 2", input2) { lines =>
    val result = part2(lines)
    assertEquals(result, 0)
  }
}
