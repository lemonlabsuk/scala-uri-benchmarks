package io.lemonlabs.uri

import io.lemonlabs.uri.decoding.PercentDecoder
import io.lemonlabs.uri.encoding.PercentEncoder
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import java.net.URLEncoder
import java.util.concurrent.TimeUnit
import scala.util.Random

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class PercentDecodeBenchmark {

  @Param(Array("5", "10", "50", "100"))
  private var amount: Int = _

  var noPercentEncoded: String = _
  var allPercentEncoded: String = _
  var halfPercentEncoded: String = _
  
  @Setup def setUp(): Unit = {
    
    def randomAlphaNum() = Random.alphanumeric
    def randomUnicode() = 
      LazyList
        .continually(Random.nextInt(Character.MAX_CODE_POINT + 1))
        .filter(cp => cp > 127)
        .filter(Character.isValidCodePoint)
        .filter(cp => Character.getType(cp) != Character.PRIVATE_USE)
        .filter(cp => Character.getType(cp) != Character.SURROGATE)
        .filter(cp => Character.getType(cp) != Character.UNASSIGNED)
        .map(Character.toString)

    val encoder = new PercentEncoder(Set.empty) {
      override def shouldEncode(ch: Char): Boolean = true
    }
    
    noPercentEncoded = randomAlphaNum().take(amount).mkString
    
    val allPercentEncodedBefore = randomUnicode().take(amount).mkString
    allPercentEncoded = encoder.encode(allPercentEncodedBefore, "UTF-8")
    
    val halfPercentEncodedBefore = (randomAlphaNum() zip randomUnicode()).take(amount / 2).map { case (a, b) => s"$a$b" }.mkString 
    halfPercentEncoded = encoder.encode (
      halfPercentEncodedBefore, "UTF-8"
    )
    
//    println("noPercentEncoded = " + noPercentEncoded)
//    println("allPercentEncoded = " + allPercentEncoded)
//    println("halfPercentEncoded = " + halfPercentEncoded)
  }

  val decoder = PercentDecoder(ignoreInvalidPercentEncoding = true)
  
  @Benchmark
  @Fork(1)
  def decodeNoPercentEncoded(): Unit =
    decoder.decode(noPercentEncoded)

  @Benchmark
  @Fork(1)
  def decodeHalfPercentEncoded(): Unit =
    decoder.decode(halfPercentEncoded)

  @Benchmark
  @Fork(1)
  def decodeAllPercentEncoded(): Unit =
    decoder.decode(allPercentEncoded)

}
