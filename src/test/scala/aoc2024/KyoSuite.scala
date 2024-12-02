package aoc2024

import kyo.*
import munit.Location

// https://scalameta.org/munit/docs/getting-started.html
class KyoSuite extends KyoFunSuiteBase {

  test("first") {
    val a: Int < Abort[String] = Abort.get(Right(13))

    val b: Int < Abort[String] = Abort.get(Left("boom"))

    assertEquals(Abort.run(a).eval, Result.success(13))
    assertEquals(Abort.run(b).eval, Result.fail("boom"))
  }

  testAbort("abort") {
    val a: Int < Abort[String] = Abort.get(Right(13))
    val _ = a.map { a =>
      assertEquals(a, 13)
    }
  }

  testAsync("async tuple") {
    val a = Async.parallel(13, "foo")
    a.map { (i, s) =>
      assertEquals((i, s), (13, "foo"))
    }
  }

  testAsync("async seq") {
    val a: Seq[Int] < Async = Async.parallel(3)(Seq(1, 13))
    a.map { vs =>
      assertEquals(vs, Seq(1, 13))
    }
  }

  testAsync("async range") {
    val range = Seq.range(1, 13)
    val a: Seq[Int] < Async = Async.parallel(3)(range.map(IO(_)))
    a.map { vs =>
      assertEquals(vs, range)
    }
  }
}
