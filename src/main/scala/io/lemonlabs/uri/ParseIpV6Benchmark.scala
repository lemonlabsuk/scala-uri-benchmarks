package io.lemonlabs.uri

import io.lemonlabs.uri.ParseIpV6Benchmark.ParseIpV6BenchmarkState
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import java.util.concurrent.TimeUnit
import scala.util.Random

object ParseIpV6Benchmark {

  @State(Scope.Thread)
  class ParseIpV6BenchmarkState {

    var regularIpV6: String = _

    @Setup def setUp(params: BenchmarkParams): Unit = {

      def isHex(ch: Char) =
        (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f')

      val pieceLength = params.getParam("pieceLength").toInt

      def randomPiece = Random.alphanumeric.filter(isHex).take(pieceLength).mkString

      regularIpV6 = List.fill(8)(randomPiece).mkString("[", ":", "]")
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class ParseIpV6Benchmark {
  
  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "2", "3", "4"))
  private var pieceLength: Int = _

  @Benchmark
  @Fork(1)
  def regularIpV6(s: ParseIpV6BenchmarkState): Unit =
    IpV6.parse(s.regularIpV6)
}
