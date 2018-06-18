name := "scala-uri-benchmarks"

organization  := "io.lemonlabs"

version       := "0.0.1"

scalaVersion  := "2.11.12"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "io.lemonlabs" %% "scala-uri" % "1.1.2",
  "com.storm-enroute" %% "scalameter" % "0.7"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

fork := true