ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

val sparkVersion = "3.1.1"

val sparkCoreDep = "org.apache.spark"  %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark"   %% "spark-sql"  % sparkVersion
val scalatestDep = "org.scalatest"     %% "scalatest"  % "3.2.+"       % Test

lazy val root = (project in file("."))
    .aggregate(
        common,
        scalaBasics,
        sparkBasics
    )
    .settings(name := "ScalaDemoRepo")

lazy val common = (project in file("common"))
    .settings(name := "Common")

lazy val sparkBasics = (project in file("spark-basics"))
    .settings(
        name := "SparkBasicsApp",
        libraryDependencies ++= Seq(
            sparkCoreDep,
            sparkSqlDep,
            scalatestDep
        )
    )

lazy val scalaBasics = (project in file("scala-basics"))
    .dependsOn(common)
    .settings(name := "ScalaBasicsApp")
