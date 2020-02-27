package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.ToStringManyPartsBenchmark.ToStringManyPartsBenchmarkState
import io.lemonlabs.uri.config.UriConfig
import io.lemonlabs.uri.encoding.{NoopEncoder, PercentEncoder}
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random

object ToStringManyPartsBenchmark {

  @State(Scope.Thread)
  class ToStringManyPartsBenchmarkState {

    var domainManyParts: Url = _
    var pathManyParts: Url = _
    var queryManyParams: Url = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val numOfParts = params.getParam("uriParts").toInt
      val percentEncode = params.getParam("everythingIsPercentEncoded").toBoolean
      val alphaNums = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
      val pe = if(percentEncode) PercentEncoder(alphaNums.toSet) else NoopEncoder
      implicit val config: UriConfig = UriConfig(encoder = pe)

      val parts = Vector.fill(numOfParts)(Random.alphanumeric.take(5).mkString)

      domainManyParts = Url.parse("http://" + parts.mkString("."))
      pathManyParts = Url.parse("http://example.com/" + parts.mkString("/"))
      queryManyParams = Url.parse("http://example.com?" + parts.foldLeft("") { (path, part) =>
        s"$path&$part=$part"
      })
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
private class ToStringManyPartsBenchmark {


  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "10", "100", "1000", "2000"))
  private var uriParts: Int = _

  @Param(Array("true", "false"))
  private var everythingIsPercentEncoded: Boolean = _

  @Benchmark
  @Fork(1)
  def domainManyParts(s: ToStringManyPartsBenchmarkState): Unit =
    s.domainManyParts.toString()

  @Benchmark
  @Fork(1)
  def pathManyParts(s: ToStringManyPartsBenchmarkState): Unit =
    s.pathManyParts.toString()

  @Benchmark
  @Fork(1)
  def queryManyParams(s: ToStringManyPartsBenchmarkState): Unit =
    s.queryManyParams.toString()
}
