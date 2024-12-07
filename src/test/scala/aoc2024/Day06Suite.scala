package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import Day06.*
import Dir.*
import scala.collection.immutable.TreeSet

class Day06Suite extends AoCFunSuiteBase {

  testInput("build map", sample1) { lines =>
    val result = buildMapData(lines)
    assertEquals(result.currentPosition, (6, 4))
    assertEquals(result.obstructions.size, 8)
  }

  testInput("next state right", sample1) { lines =>
    val state = State(Right, (0, 2))
    val next = nextState((13, 13))(state)
    assertEquals(next, Some(State(Right, (0, 3))))
    assertEquals(nextState((2, 2))(state), None)
  }

  testInput("next state left", sample1) { lines =>
    val state = State(Left, (0, 2))
    val next = nextState((13, 13))(state)
    assertEquals(next, Some(State(Left, (0, 1))))
    assertEquals(nextState((2, 2))(State(Left, (0, 0))), None)
  }

  testInput("next state up", sample1) { lines =>
    val state = State(Up, (2, 0))
    val next = nextState((13, 13))(state)
    assertEquals(next, Some(State(Up, (1, 0))))
    assertEquals(nextState((2, 2))(State(Up, (0, 0))), None)
  }

  testInput("next state down", sample1) { lines =>
    val state = State(Down, (2, 0))
    val next = nextState((13, 13))(state)
    assertEquals(next, Some(State(Down, (3, 0))))
    assertEquals(nextState((2, 2))(state), None)
  }

  testInput("sample 1", sample1) { lines =>
    val result = part1(lines)
    assertEquals(result, 41)
  }

  testInput("part 1", input1) { lines =>
    val result = part1(lines)
    assertEquals(result, 5404)
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
