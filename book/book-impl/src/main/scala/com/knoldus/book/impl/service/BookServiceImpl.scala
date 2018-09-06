package com.knoldus.book.impl.service

import akka.{Done, NotUsed}
import com.knoldus.book.api.{Book, BookService}
import com.knoldus.book.impl.eventsourcing._
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRef, PersistentEntityRegistry}

import scala.concurrent.ExecutionContext

class BookServiceImpl(persistentEntityRegistry: PersistentEntityRegistry, BookRepository: BookRepository)
                     (implicit ec: ExecutionContext) extends BookService {

  override def createBook(isbn: String, name: String, author: String, genre:String): ServiceCall[NotUsed, String] = {
    ServiceCall { _ =>
      val book = Book(isbn, name, author,genre)
      ref(isbn).ask(CreateBookCommand(book)).map {
        case Done => s"Hello ${book.author}! Your account has been created."
      }
    }
  }

  override def getBookByIsbn(isbn: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
      ref(isbn).ask(GetBookCommand(isbn)).map(Book =>
        s"Book for isbn:$isbn is ${Book.name}")
    }

  override def getBookByName(name: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
    BookRepository.getBookByName(name).map(Book =>
      s"Book for name:$name has isbn: ${Book.get.isbn} , genre: ${Book.get.genre} and author: ${Book.get.author}"
    )
  }

  def ref(id: String): PersistentEntityRef[BookCommand[_]] = {
    persistentEntityRegistry
      .refFor[BookEntity](id)
  }

}
