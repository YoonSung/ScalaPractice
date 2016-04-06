lazy val commonSettings = Seq(
    name := "ScalaPractice",
    version := "1.0",
    scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
  )