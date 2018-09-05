
package com.knoldus.impl

import akka.NotUsed
import com.knoldus.api.{User, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def getUser(id: String): ServiceCall[NotUsed, User] = ServiceCall { _ =>
    Future.successful(User.userList.find(user => user.id == id)
      .getOrElse(User("", "")))
  }

  override def addUser: ServiceCall[User, String] = ServiceCall { newUser =>
    if (isUser(newUser.id) == false) {
      User.userList += newUser
      Future.successful("user added :" + User.userList.lastOption)
    }
    else
      Future.successful("user already exists")
  }

  private def isUser(id: String): Boolean = {
    if (User.userList.exists(user => user.id == id))
      true
    else
      false
  }

  override def updateUser(id: String, name: String): ServiceCall[User, String] = ServiceCall { _ =>
    if (isUser(id) == true) {
      val requiredUser = User.userList.find(user => user.id == id).get
      User.userList -= requiredUser
      User.userList += User(id, name)
      Future.successful("user updated")
    }
    else Future.successful("user does not exists")
  }

  override def deleteUser(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    if (isUser(id) == true) {
      val requiredUser = User.userList.find(user => user.id == id).get
      User.userList -= requiredUser
      Future.successful("user deleted")
    }
    else
      Future.successful("user does not exists")
  }
}
