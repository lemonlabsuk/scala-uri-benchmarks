name := "scala-uri-benchmarks"

organization  := "io.lemonlabs"

version       := "0.0.1"

scalaVersion  := "2.12.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

enablePlugins(JmhPlugin)

libraryDependencies ++= Seq(
  "io.lemonlabs" %% "scala-uri" % sys.props.getOrElse("scalaUri.ver", "1.1.2"),
  "io.spray" %% "spray-json" % "1.3.5"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

fork := true