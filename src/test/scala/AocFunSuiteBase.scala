import aoc2024.Util.*
import aoc2024.Util.InputSpec.*
import kyo.*
import scala.concurrent.Future
import munit.Location

abstract class AoCFunSuiteBase extends munit.FunSuite {

  def testInput(name: String, input: InputSpec)(
      body: Seq[String] => Unit < IO
  )(implicit loc: Location): Unit = {
    test(name) {
      import AllowUnsafe.embrace.danger // Required for unsafe operations
      IO.Unsafe.run {
        inputLines(input).map(body)
      }.eval
    }
  }

}
