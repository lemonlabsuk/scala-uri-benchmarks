name := "scala-uri-benchmarks"

organization  := "io.lemonlabs"

version       := "0.0.1"

scalaVersion  := "2.13.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "io.lemonlabs" %% "scala-uri" % sys.props.getOrElse("scalaUri.ver", "4.0.0-M3")
)

logBuffered := false

fork := true
