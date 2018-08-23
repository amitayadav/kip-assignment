name := "kafka-assignment"

version := "0.1"

scalaVersion := "2.12.4"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.0.0"

libraryDependencies ++= Seq("org.apache.kafka" % "kafka-clients" % "0.11.0.0","log4j" % "log4j" % "1.2.17")