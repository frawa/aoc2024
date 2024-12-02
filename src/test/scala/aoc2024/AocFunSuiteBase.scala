package aoc2024

import kyo.*
import munit.Location

import scala.concurrent.Future

import Util.*
import Util.InputSpec.*

abstract class AoCFunSuiteBase extends munit.FunSuite {

  def testInput(name: String, input: InputSpec)(
      body: Seq[String] => Unit < IO
  )(implicit loc: Location): Unit = {
    test(name) {
      import AllowUnsafe.embrace.danger // Required for unsafe operations
      IO.Unsafe.run {
        inputLinesIO(input).map(body)
      }.eval
    }
  }

}
