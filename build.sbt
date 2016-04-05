lazy val commonSettings = Seq(
    name := "ScalaPractice",
    version := "1.0",
    scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
  )