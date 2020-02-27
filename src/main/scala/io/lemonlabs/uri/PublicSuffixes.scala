package io.lemonlabs.uri

import scala.io.Source

object PublicSuffixes {
  val hundredByLength: Map[Int, Vector[String]] = {
    Source.fromURL(getClass.getResource("/public_suffix_list.dat"), "UTF-8")
      .getLines()
      .map(_.trim)
      .filter(line => !line.startsWith("//") && !line.isEmpty && line.length < 20)
      .toVector
      .groupBy(_.length)
      .mapValues(_.take(100))
      .toMap
  }
}
