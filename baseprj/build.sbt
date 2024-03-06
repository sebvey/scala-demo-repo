ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.18"
ThisBuild / organization := "io.sve"

lazy val baseDependencies = Seq(
  // "org.scala-lang"          % "scala-reflect"      % "2.12.18",
  "org.apache.spark"      %% "spark-core"        % "3.3.0",
  "org.apache.spark"      %% "spark-sql"         % "3.3.0",
  // "org.apache.hadoop"     % "hadoop-client"      % "3.3.5",
  "com.github.pureconfig" %% "pureconfig"        % "0.17.6",
  "com.github.pureconfig" %% "pureconfig-hadoop" % "0.17.6",
  "com.github.scopt"      %% "scopt"             % "4.1.0",
  "com.chuusai"           %% "shapeless"         % "2.3.3",
  "org.scalatest"         %% "scalatest"         % "3.2.17" % Test
)

lazy val root = (project in file("."))
  .aggregate(
    simpleconf
  )
  .settings(
    name := "baseprj"
  )

lazy val framework = project
  .settings(
    name := "framework",
    libraryDependencies ++= baseDependencies
  )

lazy val simpleconf = project
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

lazy val sandbox = project
  .dependsOn(framework % "compile->compile;test->test")
  .settings(
    name := "sandbox",
    libraryDependencies ++= baseDependencies
  )
