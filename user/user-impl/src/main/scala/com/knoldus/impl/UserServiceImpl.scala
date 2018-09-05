
package com.knoldus.impl

import akka.NotUsed
import com.knoldus.api.{User, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def getUser(id: String): ServiceCall[NotUsed, User] = ServiceCall { _ =>
    findUser(id) match {
      case Some(user) => Future.successful(user)
      case None => Future.successful(User(s"$id", "user does not found"))
    }
  }

  private def findUser(id: String): Option[User] = {
    User.userList.find(user => user.id == id)
  }

  override def addUser: ServiceCall[User, String] = ServiceCall { newUser =>
    findUser(newUser.id) match {
      case None => User.userList += newUser
        Future.successful(s"${newUser.name} added :" + User.userList.lastOption)
      case Some(user) => Future.successful("user already exists" + user)
    }
  }

  override def updateUser(id: String, name: String): ServiceCall[User, String] = ServiceCall { _ =>
    findUser(id) match {
      case Some(user) => val requiredUser = User.userList.find(user => user.id == id).get
        User.userList -= requiredUser
        User.userList += User(id, name)
        Future.successful("user updated : " + user)
      case None => Future.successful("user does not exists")
    }
  }

  override def deleteUser(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    findUser(id) match {
      case Some(user) => val requiredUser = User.userList.find(user => user.id == id).get
        User.userList -= requiredUser
        Future.successful("user deleted: " + user)
      case None => Future.successful("user does not exists")
    }
  }
}
