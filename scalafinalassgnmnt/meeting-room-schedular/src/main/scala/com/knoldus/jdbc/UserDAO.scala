package com.knoldus.jdbc

import java.io.FileReader
import java.sql._
import java.util.Properties
import java.sql.ResultSet
import java.sql.SQLException

object UserDAO extends App {

  val fr: FileReader = new FileReader("/home/knoldus/Documents/assignments/kip-assignment/02-08-2018/meeting-room-schedular/src/main/resources/db.properties")
  var p: Properties = new Properties()
  p.load(fr)

  var connection: Connection = _
  var stmt: Statement = _
  var pstmt: PreparedStatement = _

  def createConnection: Connection = {

    Class.forName(p.getProperty("driver"))
    connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"))
    connection
  }

  @throws[ClassNotFoundException]
  @throws[SQLException]
  def addUser(userid: String, password: String, pinCode: String): String = {
    try {
      connection = createConnection

      pstmt = connection.prepareStatement("insert into " + "user_data values(?,?,?")
      pstmt.setString(1, userid)
      pstmt.setString(2, password)
      pstmt.setInt(3, pinCode.toInt)
      val rowUpdated = pstmt.executeUpdate
      //stmt = connection.createStatement();
      //		int rowUpdated = stmt.executeUpdate("insert into "
      //				+ "user_data values('"+userid+"','"+password+"',"+pinCode+")");
      return if (rowUpdated > 0) "Record Added"
      else "Record Not Added"
    }

    finally {
      if (pstmt != null) {
        pstmt.close()
      }
      if (connection != null) {
        connection.close()
      }
    }
  }



  @throws[SQLException]
  @throws[ClassNotFoundException]
  def getUsers(userid: String, password: String, pinCode: String) = { //String sql = "select * from user_info where userid='ram' and password='1234';"
    // Step -1 Connection

    var rs:ResultSet = null// Store the Query (Select Result) Record
    try {
      connection = createConnection
      pstmt = connection.prepareStatement("select * from user_data where userid =? and password =? and pincode=?")
      pstmt.setString(1, userid)
      pstmt.setString(2, password)
      pstmt.setInt(3, pinCode.toInt)
      //stmt = con.createStatement();
      rs = pstmt.executeQuery
      //rs = stmt.executeQuery("select * from user_data where userid='"+userid+"' and password='"+password+"' and pincode="+pinCode);
      if
      (rs.next) "Welcome " + userid
      else
        "Invalid Userid or Password"
    }

    finally {
      if (rs != null) rs.close()
      if (stmt != null) stmt.close
      if (connection != null) connection.close
    }
  }


  System.out.println("Enter the Userid")
  val userid = scala.io.StdIn.readLine()
  System.out.println("Enter the Password ")
  val pwd = scala.io.StdIn.readLine()
  System.out.println("Enter the Pincode")
  val pincode = scala.io.StdIn.readLine()
  val result = UserDAO.getUsers(userid, pwd, pincode)
  System.out.println(result)
}



