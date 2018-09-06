package com.knoldus.book.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait BookService extends Service {

  def createBook(isbn: String, name: String, author: String , genre:String): ServiceCall[NotUsed, String]
  def getBookByIsbn(isbn: String): ServiceCall[NotUsed, String]
  def getBookByName(name: String): ServiceCall[NotUsed, String]

  override def descriptor: Descriptor = {
    import Service._

    named("book_service")
      .withCalls(
        restCall(Method.POST, "/book/createBook/", createBook _),
        restCall(Method.GET, "/book/details/isbn/:isbn", getBookByIsbn _),
        restCall(Method.GET, "/book/details/name/:name", getBookByName _)
      ).withAutoAcl(true)
  }
}
