ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

val baseDependencies = Seq(
    "org.apache.spark"  %% "spark-core" % "3.3.2",
    "org.apache.spark"   %% "spark-sql"  % "3.3.2",
    "org.scalatest"     %% "scalatest"  % "3.2.15"       % Test
)

lazy val root = (project in file("."))
    .aggregate(
        framework,
        firstJob
    )
    .settings(name := "BasePrj")

lazy val framework = (project in file("Framework"))
    .settings(
        name := "Famework",
        libraryDependencies ++= baseDependencies
    )


lazy val firstJob = (project in file("FirstJob"))
    .dependsOn(framework)
    .settings(
        name := "FirstJob",
        libraryDependencies ++= baseDependencies
    )
