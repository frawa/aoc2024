package aoc2024

import kyo.*

// https://scalameta.org/munit/docs/getting-started.html
class KyoSuite extends munit.FunSuite {

  test("first") {
    val a: Int < Abort[String] = Abort.get(Right(13))

    val b: Int < Abort[String] = Abort.get(Left("boom"))

    assertEquals(Abort.run(a).eval, Result.success(13))
    assertEquals(Abort.run(b).eval, Result.fail("boom"))
  }
}
