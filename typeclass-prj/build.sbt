ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "io.sve"

lazy val baseDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.2.15"
)

lazy val myapp = project
.settings(
    name := "MyApp",
    libraryDependencies ++= baseDeps
)

lazy val wellstructured = project
.settings(
    name := "WellStructured",
    libraryDependencies ++= baseDeps
)
