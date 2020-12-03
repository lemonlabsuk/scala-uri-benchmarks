package io.lemonlabs.uri

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.BenchmarkParams

import java.util.concurrent.TimeUnit
import scala.util.Random

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 200, timeUnit = TimeUnit.MILLISECONDS)
class ParseIpV6Benchmark {

  @Param(Array("1", "2", "3", "4"))
  private var pieceLength: Int = _

  var regularIpV6: String = _
  var leadingCompress: String = _
  var trailingCompress: String = _
  var middleCompress: String = _
  var embeddedIpv4: String = _

  @Setup def setUp(): Unit = {
    def isHex(ch: Char) =
      (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f')

    def randomPiece = Random.alphanumeric.filter(isHex).take(pieceLength).mkString
    def randomOctet = Random.nextInt(256)

    regularIpV6 = List.fill(8)(randomPiece).mkString("[", ":", "]")
    leadingCompress = s"[::$randomPiece]"
    trailingCompress = s"[$randomPiece::]"
    middleCompress = s"[$randomPiece:$randomPiece::$randomPiece:$randomPiece]"
    embeddedIpv4 = "[" + List.fill(6)(randomPiece).mkString(":") + s":$randomOctet.$randomOctet.$randomOctet.$randomOctet]"
  }

  @Benchmark
  @Fork(1)
  def parseRegularIpV6(): Unit =
    IpV6.parse(regularIpV6)

  @Benchmark
  @Fork(1)
  def parseLeadingCompress(): Unit =
    IpV6.parse(leadingCompress)

  @Benchmark
  @Fork(1)
  def parseTrailingCompress(): Unit =
    IpV6.parse(trailingCompress)

  @Benchmark
  @Fork(1)
  def parseMiddleCompress(): Unit =
    IpV6.parse(middleCompress)

  @Benchmark
  @Fork(1)
  def parseEmbeddedIpv4(): Unit =
    IpV6.parse(embeddedIpv4)
}
