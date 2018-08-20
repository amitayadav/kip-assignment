/*
package com.knoldus.jdbc.helper

import com.knoldus.jdbc.dao.LoginDAO
import com.knoldus.jdbc.dto.UserDTO

class LoginHelper {
  def checkLogin(userDTO: UserDTO): Boolean = { // Get the Data From View
    // Get the Data From DB (DAO)
    // Compare the Data
    val loginDAO = new LoginDAO
    val array :UserDTO = loginDAO.getUserData
    for (userObject: UserDTO <- array) {
      if (userObject.getUserid == userDTO.getUserid && userObject.getPassword == userDTO.getPassword) return true
    }
    false
  }
}

*/
