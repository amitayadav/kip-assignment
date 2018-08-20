name := "meeting-room-schedular"

version := "0.1"

scalaVersion := "2.12.6"


lazy val admin = project in file("admin")

lazy val room = project in file("room")

lazy val dao = project in file("dao")

lazy val employee = project in file("employee")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "mysql" % "mysql-connector-java" % "5.1.24"
)