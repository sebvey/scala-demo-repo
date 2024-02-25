ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

lazy val baseDependencies = Seq(
    "com.github.pureconfig" %% "pureconfig"   % "0.17.6",
    "org.scalatest"         %% "scalatest"    % "3.2.15"  % Test
)

lazy val root = (project in file("."))
    .aggregate(
        simpleconf
    )
    .settings(
        name := "pureconfig"
    )

lazy val simpleconf = project
    .settings(
        name := "simpleconf",
        libraryDependencies ++= baseDependencies
    )
