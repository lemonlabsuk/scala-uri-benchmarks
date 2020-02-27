package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.PublicSuffixBenchmark.PublicSuffixBenchmarkState
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random

object PublicSuffixBenchmark {

  @State(Scope.Thread)
  class PublicSuffixBenchmarkState {

    var urls: Vector[AbsoluteUrl] = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val publicSuffixLength = params.getParam("publicSuffixLength").toInt

      val publicSuffixes = PublicSuffixes.hundredByLength(publicSuffixLength)
      val host = Random.alphanumeric.take(5).mkString

      urls = publicSuffixes.map(ps => AbsoluteUrl.parse(s"http://$host.$ps"))
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class PublicSuffixBenchmark {

  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "5", "10", "15", "20"))
  private var publicSuffixLength: Int = _

  @Benchmark
  @Fork(1)
  def publicSuffixes(s: PublicSuffixBenchmarkState): Unit =
    s.urls.foreach(_.publicSuffix)
}
