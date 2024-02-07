ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

lazy val baseDependencies = Seq(
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
)

lazy val root = (project in file("."))
    .aggregate(
        common,
        theone
    )
    .settings(
        name := "scalabricks"
    )

lazy val common = project
    .settings(
        name := "common",
        libraryDependencies ++= baseDependencies
    )

lazy val theone = project
    .settings(
        name := "theone",
        libraryDependencies ++= baseDependencies
    )

    