name := "scala-uri-benchmarks"

organization  := "io.lemonlabs"

version       := "0.0.1"

scalaVersion  := "2.13.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "io.lemonlabs" %% "scala-uri" % sys.props.getOrElse("scalaUri.ver", "3.0.0"),
  "io.spray" %% "spray-json" % "1.3.5" // For scala-uri 1.5.1 support only
)

logBuffered := false

fork := true
