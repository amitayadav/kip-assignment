

  name := "meeting-room-schedular"

  version := "0.1"

  scalaVersion := "2.12.6"

lazy val root= project in file(".")
               //.settings(setting)


lazy val database= project in file("db")



libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.24"
