import sbt.Keys.testFrameworks

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "BouncingBallsScala",
    testFrameworks += new TestFramework("munit.Framework"),
  )

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

// Adding the munit testing framework
libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M10" % Test

libraryDependencies += "javazoom" % "jlayer" % "1.0.1"

