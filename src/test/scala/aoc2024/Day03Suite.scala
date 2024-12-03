package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day03.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

class Day03Suite extends AoCFunSuiteBase {

  testInput("matchOperands", sample1) { lines =>
    var result = matchOperands(lines.mkString)
    assertEquals(result, Seq((2, 4), (5, 5), (11, 8), (8, 5)))
  }

  testInput("sample 1", sample1) { lines =>
    var result = part1(lines)
    assertEquals(result, 161)
  }

  testInput("part 1", input1) { lines =>
    var result = part1(lines)
    assertEquals(result, 191183308)
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
