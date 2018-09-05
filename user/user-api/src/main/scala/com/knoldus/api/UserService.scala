
package com.knoldus.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

import scala.collection.mutable.ListBuffer

trait UserService extends Service {

  override final def descriptor = {
    import Service._

    named("User")
      .withCalls(
        restCall(Method.GET, "/api/user/:id", getUser _),
        restCall(Method.POST, "/api/user/", addUser _),
        restCall(Method.PUT, "/api/user/:id/:name", updateUser _),
        restCall(Method.DELETE, "/api/user/:id", deleteUser _)
      )
      .withAutoAcl(true)

  }

  def getUser(id: String): ServiceCall[NotUsed, User]

  def addUser: ServiceCall[User, String]

  def updateUser(id: String, name: String): ServiceCall[User, String]

  def deleteUser(id: String): ServiceCall[NotUsed, String]
}

case class User(id: String, name: String)

object User {

  implicit val format: Format[User] = Json.format[User]
  val userList: ListBuffer[User] = ListBuffer(User("1", "amita"), User("2", "aashrita"), User("3", "abhishek"), User("4", "anisha"), User("5", "pratima"))

}



