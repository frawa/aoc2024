package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day07.*
import Day07.Operator.*

class Day07Suite extends AoCFunSuiteBase {

  testInput("parse sample 1", sample1) { lines =>
    val result = parseEquations(lines)
    assertEquals(result.size, 9)
  }

  test("possible operators") {
    assertEquals(possibleOperators(1), Seq(Seq(Add), Seq(Mult)))
    assertEquals(
      possibleOperators(2),
      Seq(Seq(Add, Add), Seq(Add, Mult), Seq(Mult, Add), Seq(Mult, Mult))
    )
  }

  test("can be made true") {
    assertEquals(
      canBeMadeTrue(Equation(190, Seq(19, 10))),
      true
    )
    assertEquals(
      canBeMadeTrue(Equation(21037, Seq(9, 7, 18, 13))),
      false
    )
  }

  testInput("sample 1", sample1) { lines =>
    val result = part1(lines)
    assertEquals(result, 3749L)
  }

  testInput("part 1", input1) { lines =>
    val result = part1(lines)
    assertEquals(result, 850435817339L)
  }

  testInput("sample 2", sample2) { lines =>
    val result = part2(lines)
    assertEquals(result, 11387L)
  }

  testInput("part 2", input2) { lines =>
    val result = part2(lines)
    assertEquals(result, 104824810233437L)
  }

}
