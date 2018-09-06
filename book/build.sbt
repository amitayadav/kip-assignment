name := "book"

organization in ThisBuild := "com.example"

version := "0.1"

scalaVersion := "2.12.6"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

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
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`book-api`)

lagomCassandraEnabled in ThisBuild := false
lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://localhost:9042")
lagomKafkaEnabled in ThisBuild := false

