package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day03.*
class Day03Suite extends AoCFunSuiteBase {

  testInput("matchOperands", sample1) { lines =>
    val result = matchOperands(lines.mkString)
    assertEquals(result, Seq((2, 4), (5, 5), (11, 8), (8, 5)))
  }

  testInput("sample 1", sample1) { lines =>
    val result = part1(lines)
    assertEquals(result, 161)
  }

  testInput("part 1", input1) { lines =>
    val result = part1(lines)
    assertEquals(result, 191183308)
  }

  testInput("partitionDos", sample2) { lines =>
    val result = keepDos(lines.mkString)
    assertEquals(result, "xmul(2,4)&mul[3,7]!^?mul(8,5))")
  }

  testInput("sample 2", sample2) { lines =>
    val result = part2(lines)
    assertEquals(result, 48)
  }

  testInput("part 2", input2) { lines =>
    val result = part2(lines)
    // assertEquals(result, 4246106) // too low
    assertEquals(result, 92082041) // too low
  }
}
