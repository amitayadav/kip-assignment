
package com.knoldus.impl

import akka.{Done, NotUsed}
import com.knoldus.api.{User, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def getUser(id: String): ServiceCall[NotUsed, User] = ServiceCall { _ =>
     Future.successful(User.userList.find(user => user.id == id).get)
  }

  override def addUser: ServiceCall[User, String] = ServiceCall{ newUser =>
    User.userList += newUser
    Future.successful("user added :" + User.userList.lastOption)
  }

  override def updateUser(id: String): ServiceCall[User, String] = ServiceCall {_ =>
    val requiredUser = User.userList.find(user => user.id == id).get
    if(requiredUser != null)
     { User.userList -= requiredUser
       User.userList += User(id,"ankita")
       Future.successful("user updated")
     }
    else Future.successful("user does not exists")
  }


  override def deleteUser(id: String): ServiceCall[NotUsed, String] = ServiceCall {_ =>

   val requiredUser = User.userList.find(user => user.id == id).get
    if(requiredUser != null)
    Future.successful("user deleted"+(User.userList -= requiredUser).lastOption)
    else
      Future.successful("user does not exists")
  }
}
