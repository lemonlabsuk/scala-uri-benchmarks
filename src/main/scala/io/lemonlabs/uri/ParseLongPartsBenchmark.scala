package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.ParseLongPartsBenchmark.ParseLongPartsState
import io.lemonlabs.uri.encoding.PercentEncoder
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random

object ParseLongPartsBenchmark {

  @State(Scope.Thread)
  class ParseLongPartsState {

    var longDomain: String = _
    var longPath: String = _
    var longQueryKey: String = _
    var longQueryValue: String = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val partLength = params.getParam("uriPartLength").toInt
      val percentEncode = params.getParam("everythingIsPercentEncoded").toBoolean

      val pe = PercentEncoder()

      val randomString = Random.alphanumeric.take(partLength).mkString
      val part = if(percentEncode) randomString.flatMap(pe.encodeChar) else randomString

      longDomain = "http://" + part
      longPath = "/" + part
      longQueryKey = "http://example.com?" + part + "=value"
      longQueryValue = "http://example.com?key=" + part
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class ParseLongPartsBenchmark {


  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "10", "100", "1000", "2000"))
  private var uriPartLength: Int = _

  @Param(Array("true", "false"))
  private var everythingIsPercentEncoded: Boolean = _

  @Benchmark
  @Fork(1)
  def longDomain(s: ParseLongPartsState): Unit =
    Url.parse(s.longDomain)

  @Benchmark
  @Fork(1)
  def longPath(s: ParseLongPartsState): Unit =
    Url.parse(s.longPath)

  @Benchmark
  @Fork(1)
  def longQueryKey(s: ParseLongPartsState): Unit =
    Url.parse(s.longQueryKey)

  @Benchmark
  @Fork(1)
  def longQueryValue(s: ParseLongPartsState): Unit =
    Url.parse(s.longQueryValue)
}
