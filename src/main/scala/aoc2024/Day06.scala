package aoc2024

import kyo.*
import AllowUnsafe.embrace.danger // Required for unsafe operations

import Util.*
import Util.InputSpec.*
import scala.collection.immutable.TreeSet

object Day06 {

  val input1 = Part1(6)
  val sample1 = Sample(input1)
  val input2 = input1
  val sample2 = sample1

  type Pos = (Int, Int)
  case class MapData(currentPosition: Pos, max: Pos, obstructions: Set[Pos])

  def buildMapData(lines: Seq[String]): MapData = {
    val max = (lines.size - 1, lines(0).length - 1)
    val map = MapData((0, 0), max, Set.empty)
    lines.zipWithIndex.foldLeft(map) { case (map, (line, i)) =>
      line.toSeq.zipWithIndex.foldLeft(map) {
        case (map, ('#', j)) =>
          map.copy(obstructions = map.obstructions + (i -> j))
        case (map, ('^', j)) => map.copy(currentPosition = (i, j))
        case (map, _)        => map
      }
    }
  }

  type Ranges = Map[Int, TreeSet[Int]]
  enum Dir:
    case Up
    case Down
    case Left
    case Right

  case class State(dir: Dir, pos: Pos)

  def nextState(max: Pos)(state: State): Option[State] = {
    val (rowMax, colMax) = max
    val (row, col) = state.pos
    state.dir match
      case Dir.Up if row > 0 =>
        Some(state.copy(pos = (row - 1, col)))
      case Dir.Down if row < rowMax =>
        Some(state.copy(pos = (row + 1, col)))
      case Dir.Left if col > 0 =>
        Some(state.copy(pos = (row, col - 1)))
      case Dir.Right if col < colMax =>
        Some(state.copy(pos = (row, col + 1)))
      case _ => None
  }

  def turnRight(state: State): State = {
    state.dir match
      case Dir.Up    => state.copy(dir = Dir.Right)
      case Dir.Down  => state.copy(dir = Dir.Left)
      case Dir.Left  => state.copy(dir = Dir.Up)
      case Dir.Right => state.copy(dir = Dir.Down)
  }

  def part1(lines: Seq[String]): Int = {
    val map = buildMapData(lines)

    val state = State(Dir.Up, map.currentPosition)

    val visited = Loop(state, Set(state.pos)) { (state, visited) =>
      nextState(map.max)(state) match
        case Some(nextState) =>
          if !map.obstructions.contains(nextState.pos)
          then Loop.continue(nextState, visited + nextState.pos)
          else Loop.continue(turnRight(state), visited)
        case None => Loop.done(visited)
    }.eval

    visited.size
  }

  def part2(lines: Seq[String]): Int = {
    0
  }
}
