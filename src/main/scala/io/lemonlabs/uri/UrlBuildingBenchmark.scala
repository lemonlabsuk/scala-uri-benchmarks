package io.lemonlabs.uri

import java.util.concurrent.TimeUnit

import io.lemonlabs.uri.UrlBuildingBenchmark.UrlBuildingBenchmarkState
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import scala.util.Random


object UrlBuildingBenchmark {

  @State(Scope.Thread)
  class UrlBuildingBenchmarkState {

    var startUrl: Url = _
    var pathParts: Vector[String] = _
    var queryParts: Vector[(String, String)] = _

    @Setup def setUp(params: BenchmarkParams): Unit = {
      val numOfParts = params.getParam("uriParts").toInt
      def randomPart = Random.alphanumeric.take(5).mkString

      startUrl = Url.parse(s"http://$randomPart")
      pathParts = Vector.fill(numOfParts)(randomPart)
      queryParts = Vector.fill(numOfParts)(randomPart -> randomPart)
    }
  }
}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class UrlBuildingBenchmark {

  import org.openjdk.jmh.annotations.Param

  @Param(Array("1", "10", "100", "1000", "2000"))
  private var uriParts: Int = _

  @Benchmark
  @Fork(1)
  def addPathPart(s: UrlBuildingBenchmarkState): Unit =
    s.pathParts.foldLeft(s.startUrl)(_ addPathPart _)

  @Benchmark
  @Fork(1)
  def addQueryParam(s: UrlBuildingBenchmarkState): Unit =
    s.queryParts.foldLeft(s.startUrl)(_ addParam _)
}
