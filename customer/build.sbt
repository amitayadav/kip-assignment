organization in ThisBuild := "com.knoldus"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.4"

lazy val `customer` = (project in file("."))
  .aggregate(`customer-api`, `customer-impl`)
lazy val `customer-api` = (project in file("customer-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
lazy val `customer-impl` = (project in file("customer-impl"))
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
  .dependsOn(`customer-api`)
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lagomUnmanagedServices in ThisBuild := Map("customer-record-service" -> "https://gist.githubusercontent.com")

