import sbt.Keys._

lazy val commonSettings = Seq(
    name := "ScalaPractice",
    version := "1.0",
    scalaVersion := "2.11.8",
    resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.1"
  )