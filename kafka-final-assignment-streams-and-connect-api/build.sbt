
name := "kafka-final-assignment-streams-and-connect-api"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq("org.apache.kafka" % "kafka-clients" % "0.11.0.0",
  "log4j" % "log4j" % "1.2.17",
  "org.apache.kafka" % "kafka-streams" % "1.0.0", "net.liftweb" %% "lift-json" % "3.1.1")