package com.knoldus.jdbc.dto

class UserDTO {

    private var userid :String= null
    private var password :String = null

    def getUserid: String = userid

    def setUserid(userid: String): Unit = {
      this.userid = userid
    }

    def getPassword: String = password

    def setPassword(password: String): Unit = {
      this.password = password
    }


}
