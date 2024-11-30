// import kyo.*
import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import kyo.*

class UtilSuite extends munit.FunSuite {

  import AllowUnsafe.embrace.danger // Required for unsafe operations

  test("resource name") {
    assertEquals(resourceName(Part1(1)), "day01.part1")
    assertEquals(resourceName(Part2(9)), "day09.part2")
    assertEquals(resourceName(Sample(Part2(42))), "day42.part2.sample")
  }

  test("read lines") {
    val ioLines = readLines(Sample(Part2(42)))
    val lines = IO.Unsafe.run(ioLines).eval
    assertEquals(lines, Seq("Hello", "World"))
  }
}
