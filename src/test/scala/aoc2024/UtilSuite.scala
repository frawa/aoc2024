package aoc2024

import kyo.*

import Util.*
import Util.InputSpec.*
import java.io.FileNotFoundException
import java.nio.file.NoSuchFileException

class UtilSuite extends munit.FunSuite {

  import AllowUnsafe.embrace.danger // Required for unsafe operations

  test("data name") {
    assertEquals(dataName(Part1(1)), "day01.part1")
    assertEquals(dataName(Part2(9)), "day09.part2")
    assertEquals(dataName(Sample(Part2(42))), "day42.part2.sample")
  }

  test("data lines") {
    IO.Unsafe.run {
      inputLinesIO(Sample(Part2(42))).map { lines =>
        assertEquals(lines, Seq("Hello", "World"))
      }
    }.eval
  }

  test("data lines Abort") {
    Abort
      .run {
        inputLinesAbort(Sample(Part2(42))).map { lines =>
          assertEquals(lines, Seq("Hello", "World"))
        }
      }
      .eval
      .getOrThrow
  }

  test("missing file IO".tag(munit.Fail)) {
    IO.Unsafe.run {
      inputLinesIO(Sample(Part2(1313)))
    }.eval
  }

  test("missing file IO, intercept") {
    intercept[NoSuchFileException] {
      IO.Unsafe.run {
        inputLinesIO(Sample(Part2(1313)))
      }.eval
    }
  }

  test("missing file Abort, intercept") {
    intercept[FileNotFoundException] {
      Abort
        .run {
          inputLinesAbort(Sample(Part2(1313)))
        }
        .eval
        .getOrThrow
    }
  }

  test("map parallel: sequential") {
    val n = 13131313
    val range = Seq.range(0, n)
    Util.mapParallel(1, range)(_ + 1)
  }

  test("map parallel: parallel") {
    val n = 13131313
    val range = Seq.range(0, n)
    Util.mapParallel(4, range)(_ + 1)
  }
}
