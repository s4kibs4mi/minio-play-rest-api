name := "minio-play-rest-api"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.minio" % "minio" % "3.0.5",
  "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.4"
)
