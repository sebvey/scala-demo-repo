ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.14"
ThisBuild / organization := "io.sve"


lazy val baseDependencies = Seq(
  "dev.zio"               %% "zio"               % "2.0.22",
  "org.scalatest"         %% "scalatest"         % "3.2.18" % Test
)

lazy val root = (project in file("."))
  .aggregate(oneoone)
  .settings( name := "ziolearning" )

lazy val oneoone = project
  .settings(
    name := "oneoone",
    libraryDependencies ++= baseDependencies
  )
