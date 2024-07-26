// https://www.scala-sbt.org/release/docs/Multi-Project

// Variables defined at ThisBuild scope applies to all sub prj
// (when the variable is not defined in sub-prj)

ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.19"
ThisBuild / organization := "io.sve"

// Why 'lazy val' : allows forward references
// (reference to vars later declared in the file)

lazy val baseDependencies = Seq(
  // "org.scala-lang"          % "scala-reflect"      % "2.12.18",
  "org.apache.spark"      %% "spark-core"        % "3.5.1",
  "org.apache.spark"      %% "spark-sql"         % "3.5.1",
  // "org.apache.hadoop"     % "hadoop-client"      % "3.3.5",
  "com.github.pureconfig" %% "pureconfig"        % "0.17.7",
  "com.github.pureconfig" %% "pureconfig-hadoop" % "0.17.7",
  "com.github.scopt"      %% "scopt"             % "4.1.0",
  "com.chuusai"           %% "shapeless"         % "2.3.12",
  "org.scalactic"         %% "scalactic"         % "3.2.19" % Test,
  "org.scalatest"         %% "scalatest"         % "3.2.19" % Test,
  "com.github.mrpowers"   %% "spark-fast-tests"  % "1.3.0"  % Test
)


// AGGREGATOR : running a task on the aggregator project will also run it on the aggregated projects
lazy val root = (project in file("."))
  .aggregate(
    simpleconf
  )
  .settings(
    name := "baseprj"
    // update / aggregate := false // the way to exclude the task 'update' from aggregation
  )

// here we define a sub project in a sub folder named like settings(name :=)
lazy val framework = project
  .settings(
    name := "framework",
    libraryDependencies ++= baseDependencies
  )

// a sub project with explicit sub folder given
lazy val simpleconf = (project in file("simpleconf"))
  .dependsOn(framework % "compile->compile;test->test")
  .settings(
    name := "simpleconf",
    libraryDependencies ++= baseDependencies
  )

lazy val simpleargs = project
  .dependsOn(framework % "compile->compile;test->test")
  .settings(
    name := "simpleargs",
    libraryDependencies ++= baseDependencies
  )

lazy val simplesparktest = project
  .dependsOn(framework % "compile->compile;test->test")
  .settings(
    name := "simplesparktest",
    libraryDependencies ++= baseDependencies
  )

lazy val sandbox = project
  .dependsOn(framework % "compile->compile;test->test")
  .settings(
    name := "sandbox",
    libraryDependencies ++= baseDependencies
  )
