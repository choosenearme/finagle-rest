name := "finagle-rest"

version := "1.0.0-SNAPSHOT"

organization := "me.choosenear"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "releases"  at "http://oss.sonatype.org/content/repositories/releases",
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "twitter-repo" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % "3.0.0",
  "com.twitter" %% "finagle-http" % "3.0.0",
  "org.scalaj" %% "scalaj-collection" % "1.2",
  "org.specs2" %% "specs2" % "1.9" % "test"
)

scalacOptions ++= List("-deprecation", "-unchecked")

javacOptions ++= Seq("-Dfile.encoding=UTF-8")