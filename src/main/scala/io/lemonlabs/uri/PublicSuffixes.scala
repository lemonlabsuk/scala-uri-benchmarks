package io.lemonlabs.uri

import scala.io.Source

object PublicSuffixes {
  val all: List[String] = {
    Source.fromURL(getClass.getResource("/public_suffix_list.dat"), "UTF-8")
      .getLines()
      .map(_.trim)
      .filter(line => !line.startsWith("//") && line.nonEmpty)
      .toList
  }

  val alexa10k: List[String] = {
    Source.fromURL(getClass.getResource("/alexa-top-10thousand.txt"), "UTF-8")
      .getLines()
      .map(_.trim)
      .filter(line => line.nonEmpty)
      .toList
  }
}
