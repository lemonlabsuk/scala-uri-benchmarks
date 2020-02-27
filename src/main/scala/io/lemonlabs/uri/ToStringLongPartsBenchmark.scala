package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.ToStringLongPartsBenchmark.ToStringLongPartsBenchmarkState
import io.lemonlabs.uri.config.UriConfig
import io.lemonlabs.uri.encoding.{NoopEncoder, PercentEncoder}
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random

object ToStringLongPartsBenchmark {

  @State(Scope.Thread)
  class ToStringLongPartsBenchmarkState {

    var longDomain: Url = _
    var longPath: Url = _
    var longQueryKey: Url = _
    var longQueryValue: Url = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val partLength = params.getParam("uriPartLength").toInt
      val percentEncode = params.getParam("everythingIsPercentEncoded").toBoolean
      val alphaNums = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      val pe = if(percentEncode) PercentEncoder(alphaNums.toSet) else NoopEncoder
      implicit val config: UriConfig = UriConfig(encoder = pe)
      val part = Random.alphanumeric.take(partLength).mkString

      longDomain = Url.parse("http://" + part)
      longPath = Url.parse("http://example.com/" + part)
      longQueryKey = Url.parse("http://example.com?" + part + "=value")
      longQueryValue = Url.parse("http://example.com?key=" + part)
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class ToStringLongPartsBenchmark {

  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "10", "100", "1000", "2000"))
  private var uriPartLength: Int = _

  @Param(Array("true", "false"))
  private var everythingIsPercentEncoded: Boolean = _

  @Benchmark
  @Fork(1)
  def longDomain(s: ToStringLongPartsBenchmarkState): Unit =
    s.longDomain.toString()

  @Benchmark
  @Fork(1)
  def longPath(s: ToStringLongPartsBenchmarkState): Unit =
    s.longPath.toString()

  @Benchmark
  @Fork(1)
  def longQueryKey(s: ToStringLongPartsBenchmarkState): Unit =
    s.longQueryKey.toString()

  @Benchmark
  @Fork(1)
  def longQueryValue(s: ToStringLongPartsBenchmarkState): Unit =
    s.longQueryValue.toString()
}
