name := "book"

organization in ThisBuild := "com.example"

version := "0.1"

scalaVersion := "2.12.6"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test
val logger = "ch.qos.logback" % "logback-classic" % "1.2.3"
val scalaLogger ="com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"

lazy val `book` = (project in file("."))
  .aggregate(`book-api`, `book-impl`)

lazy val `book-api` = (project in file("book-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `book-impl` = (project in file("book-impl"))
  .enablePlugins(LagomScala)
  .disablePlugins(LagomLogback)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest,
      logger,
      scalaLogger
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`book-api`)

lagomCassandraEnabled in ThisBuild := false
lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://localhost:9042")
lagomKafkaEnabled in ThisBuild := false


