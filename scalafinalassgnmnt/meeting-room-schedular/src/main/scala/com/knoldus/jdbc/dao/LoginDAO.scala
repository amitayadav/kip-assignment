
package com.knoldus.jdbc.dao

import com.knoldus.jdbc.dto.UserDTO

  class LoginDAO {
    def getUserData: Array[UserDTO] = { // Get the Data from DataBase
      val array = new Array[UserDTO](2)
      var userDTO = new UserDTO
      userDTO.setUserid("amit")
      userDTO.setPassword("123")
      array(0) = userDTO
      userDTO = new UserDTO
      userDTO.setUserid("ram")
      userDTO.setPassword("123")
      array(1) = userDTO
      array
    }
}

