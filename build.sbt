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
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.1",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "2.4.3",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.3",
    libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.3",
    libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test",
    libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
  )