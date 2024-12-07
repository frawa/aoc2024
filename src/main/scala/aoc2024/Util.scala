package aoc2024

import kyo.*
import scala.io.Source
import java.io.FileNotFoundException
import java.io.IOException

object Util {

  enum InputSpec {
    case Part1(day: Int) extends InputSpec
    case Part2(day: Int) extends InputSpec
    case Sample(input: InputSpec) extends InputSpec
  }

  def inputLinesIO(input: InputSpec): Seq[String] < IO = {
    Path("data", dataName(input) + ".txt").readLines
  }

  def inputLinesAbort(
      input: InputSpec
  ): Seq[String] < Abort[FileNotFoundException] = {
    val path = s"data/${dataName(input)}.txt"
    Abort.catching { Source.fromFile(path).getLines().toSeq }
  }

  def dataName(input: InputSpec): String = {
    input match
      case InputSpec.Part1(day)    => f"day$day%02d.part1"
      case InputSpec.Part2(day)    => f"day$day%02d.part2"
      case InputSpec.Sample(input) => dataName(input) + ".sample"
  }

  def mapParallel[A, B](parallelism: Int, vs: Seq[A])(
      f: A => B
  )(using kyo.Flat[A], kyo.Flat[B]): Seq[B] = {
    val doit = Async.parallel(parallelism)(vs.map(v => IO(f(v))))
    val result: Seq[B] < (Async & IO & Abort[IOException]) = for
      sw <- Clock.stopwatch
      result <- IO(doit)
      elapsed <- sw.elapsed
      _ <- Console.println(
        s"parallel($parallelism) map #${vs.size} in ${elapsed.toMillis}ms"
      )
    // _ = Log.warn(s"parallel map #${vs.size} in ${elapsed.toMillis}ms")
    yield result

    import AllowUnsafe.embrace.danger
    // val clocked = Clock.let(Clock.live)(result)
    // val consoled = Console.let(Console.live)(clocked)
    // val result1 = KyoApp.Unsafe.runAndBlock(13.seconds)(consoled)
    // result1.toMaybe.get

    result
      .pipe(Clock.let(Clock.live))
      .pipe(Console.let(Console.live))
      .pipe(KyoApp.Unsafe.runAndBlock(13.seconds))
      .toMaybe
      .get
  }

}
