import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import kyo.*

class UtilSuite extends munit.FunSuite {

  import AllowUnsafe.embrace.danger // Required for unsafe operations

  test("resource name") {
    assertEquals(dataName(Part1(1)), "day01.part1")
    assertEquals(dataName(Part2(9)), "day09.part2")
    assertEquals(dataName(Sample(Part2(42))), "day42.part2.sample")
  }

  test("data lines") {
    IO.Unsafe.run {
      inputLines(Sample(Part2(42))).map { lines =>
        assertEquals(lines, Seq("Hello", "World"))
      }
    }.eval
  }
}
