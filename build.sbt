name := "minio-play-rest-api"

version := "0.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, DebianPlugin)

maintainer in Linux := "Sakib Sami <root@sakib.ninja>"

packageSummary in Linux := "Minio Rest API"

packageDescription := "Application to maintain minio cloud storage with restful webservice."

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "io.minio" % "minio" % "3.0.5",
  "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.4",
  "mysql" % "mysql-connector-java" % "6.0.6",
  "commons-codec" % "commons-codec" % "1.10"
)
