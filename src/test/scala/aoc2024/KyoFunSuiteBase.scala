package aoc2024

import kyo.*
import munit.Location

abstract class KyoFunSuiteBase extends munit.FunSuite {
  def testIO(name: String)(
      body: => Unit < IO
  )(implicit loc: Location): Unit = {
    test(name) {
      import AllowUnsafe.embrace.danger // Required for unsafe operations
      IO.Unsafe.run {
        body
      }.eval
    }
  }

  def testAbort(name: String)(
      body: => Unit < Abort[Throwable]
  )(implicit loc: Location): Unit = {
    test(name) {
      Abort
        .run {
          body
        }
        .eval
        .getOrThrow
    }
  }

  def testAsync(name: String)(
      body: => Unit < Async
  )(implicit loc: Location): Unit = {
    test(name) {
      import AllowUnsafe.embrace.danger // Required for unsafe operations
      val _ = Async
        .runAndBlock(42.seconds) {
          body
        }
        .pipe(Abort.run(_))
        .pipe(IO.Unsafe.run(_))
        .eval
        .getOrThrow
    }
  }
}
