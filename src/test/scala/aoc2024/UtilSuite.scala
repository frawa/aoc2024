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

  test("sequential map") {
    val n = 13131313
    val range = Seq.range(0, n)
    val calc: (Duration, Seq[Int]) < IO = for
      sw <- Clock.stopwatch
      incremented <- IO(range.map(_ + 1))
      elapsed <- sw.elapsed
    yield (elapsed, incremented)

    val clocked = Clock.let(Clock.live)(calc)
    import AllowUnsafe.embrace.danger
    val result = KyoApp.Unsafe.runAndBlock(13.seconds)(clocked)

    val (duration, incremented) = result.toMaybe.get
    assertEquals(range.last, n - 1)
    assertEquals(incremented.last, n)
    println(s"FW sequential ${duration.toMillis}ms")
  }

  test("parallel map") {
    val n = 13131313
    val range = Seq.range(0, n)
    val doit = Async.parallel(13)(range.map(_ + 1))
    val calc: (Duration, Seq[Int]) < (Async & IO) = for
      sw <- Clock.stopwatch
      incremented <- IO(doit)
      elapsed <- sw.elapsed
    yield (elapsed, incremented)

    val clocked = Clock.let(Clock.live)(calc)
    import AllowUnsafe.embrace.danger
    val result = KyoApp.Unsafe.runAndBlock(13.seconds)(clocked)

    val (duration, incremented) = result.toMaybe.get
    assertEquals(range.last, n - 1)
    assertEquals(incremented.last, n)
    println(s"FW parallel ${duration.toMillis}ms")
  }
}
