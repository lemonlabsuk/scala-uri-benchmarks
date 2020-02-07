package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.ParseManyPartsBenchmark.ParseManyPartsState
import io.lemonlabs.uri.encoding.PercentEncoder
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random

object ParseManyPartsBenchmark {

  @State(Scope.Thread)
  class ParseManyPartsState {

    var domainManyParts: String = _
    var pathManyParts: String = _
    var queryManyParams: String = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val partLength = params.getParam("uriParts").toInt
      val percentEncode = params.getParam("everythingIsPercentEncoded").toBoolean

      val pe = PercentEncoder()

      val randomStrings = Vector.fill(partLength)(Random.alphanumeric.take(5).mkString)
      val parts = if(percentEncode) randomStrings.map(pe.encode(_, "UTF-8")) else randomStrings

      domainManyParts = "http://" + parts.mkString(".")
      pathManyParts = "/" + parts.mkString("/")
      queryManyParams = "http://example.com?" + parts.foldLeft("") { (path, part) =>
        s"$path&$part=$part"
      }
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class ParseManyPartsBenchmark {


  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "10", "100", "1000", "2000"))
  private var uriParts: Int = _

  @Param(Array("true", "false"))
  private var everythingIsPercentEncoded: Boolean = _

  @Benchmark
  @Fork(1)
  def domainManyParts(s: ParseManyPartsState): Unit =
    Url.parse(s.domainManyParts)

  @Benchmark
  @Fork(1)
  def pathManyParts(s: ParseManyPartsState): Unit =
    Url.parse(s.pathManyParts)

  @Benchmark
  @Fork(1)
  def queryManyParams(s: ParseManyPartsState): Unit =
    Url.parse(s.queryManyParams)
}
