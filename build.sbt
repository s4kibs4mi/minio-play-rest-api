name := "minio-play-rest-api"

version := "0.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.minio" % "minio" % "3.0.5",
  "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.4",
  "mysql" % "mysql-connector-java" % "6.0.6"
)
