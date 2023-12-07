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
        common,
        sparkBasics
    )
    .settings(name := "BasePrj")

lazy val framework = (project in file("framework"))
    .settings(
        name := "Framework",
        libraryDependencies ++= baseDependencies
    )

lazy val common = (project in file("common"))
    .dependsOn(framework)
    .settings(
        name := "Common",
        libraryDependencies ++= baseDependencies
    )

lazy val sparkBasics = (project in file("spark-basics"))
    .dependsOn(framework)
    .settings(
        name := "SparkBasicsApp",
        libraryDependencies ++= baseDependencies
    )
