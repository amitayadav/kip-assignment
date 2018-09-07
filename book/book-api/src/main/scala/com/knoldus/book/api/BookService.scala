package com.knoldus.book.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

trait BookService extends Service {

  def createBook: ServiceCall[Book, Done]

  def getBookByIsbn(isbn: String): ServiceCall[NotUsed, Book]

  def getBookByName(name: String): ServiceCall[NotUsed, Book]

  def getBookByAuthor(author: String): ServiceCall[NotUsed, Book]

  def updateBook(isbn: String): ServiceCall[Book, Done]

  def deleteBook(isbn: String): ServiceCall[NotUsed, Done]

  override def descriptor: Descriptor = {
    import Service._

    named("book_service")
      .withCalls(
        restCall(Method.POST, "/api/book/createBook/", createBook _),
        restCall(Method.GET, "/api/book/getBookByIsbn/:isbn", getBookByIsbn _),
        restCall(Method.GET, "/api/book/getBookByName/:name", getBookByName _),
        restCall(Method.GET, "/api/book/getBookByAuthor/:author", getBookByAuthor _),
        restCall(Method.PUT, "/api/book/updateBook/:isbn", updateBook _),
        restCall(Method.DELETE, "/api/book/deleteBook/:isbn", deleteBook _)
      ).withAutoAcl(true)
  }
}
