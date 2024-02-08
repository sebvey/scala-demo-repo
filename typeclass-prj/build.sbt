ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

lazy val baseDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.2.15"
)

lazy val myapp = project
.settings(
    name := "App",
    libraryDependencies ++= baseDeps
)
