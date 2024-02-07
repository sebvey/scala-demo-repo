// https://www.scala-sbt.org/release/docs/Multi-Project

// Variables defined at ThisBuild scope applies to all sub prj
// (when the variable is not defined in sub-prj)
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

lazy val baseDependencies = Seq(
    "org.apache.spark"     %% "spark-core"           % "3.3.1",
    "org.apache.spark"     %% "spark-sql"            % "3.3.1",
    "org.scalatest"        %% "scalatest"            % "3.2.15"  % Test,
    "com.github.mrpowers"  %% "spark-fast-tests"     % "1.1.0"   % Test
)


// running a task on the aggregator project will also
// run it on the aggregated projects
lazy val root = (project in file("."))
    .aggregate(
        framework,
        firstJob,
        sandbox
    )
    .settings(
        name := "BasePrj",
        // update / aggregate := false // it would exclude the task 'update' from aggregation
        )

lazy val framework = project
    .settings(
        name := "Famework",
        libraryDependencies ++= baseDependencies
    )

// subprj in dir with same name as variable
lazy val firstJob = (project in file("firstjob"))
    .dependsOn(framework % "compile->compile;test->test")
    .settings(
        name := "FirstJob",
        libraryDependencies ++= baseDependencies
    )

lazy val sandbox = (project in file("sandbox"))
    .settings(
        name := "Sandbox",
        libraryDependencies ++= baseDependencies
    )

// Why lazy val -> allows forward references (reference to vars later declared in the file)
