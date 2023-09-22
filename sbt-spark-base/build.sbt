ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"

val sparkVersion = "3.1.1"

lazy val root = (project in file("."))
    .settings(
        name := "SparkTest"
    )

libraryDependencies += "org.apache.spark"  %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark"  %% "spark-sql"  % sparkVersion
libraryDependencies += "org.scalatest"     %% "scalatest"  % "3.2.+"      % Test
