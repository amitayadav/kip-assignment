package com.knoldus.book.impl.service

import akka.{Done, NotUsed}
import com.knoldus.book.api.{Book, BookService}
import com.knoldus.book.impl.eventsourcing._
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRef, PersistentEntityRegistry}
import org.apache.log4j.{LogManager, Logger}

import scala.concurrent.ExecutionContext

class BookServiceImpl(persistentEntityRegistry: PersistentEntityRegistry, BookRepository: BookRepository)
                     (implicit ec: ExecutionContext) extends BookService {

  val log: Logger = LogManager.getLogger(classOf[BookServiceImpl])

  override def createBook(isbn: String, name: String, author: String, genre: String): ServiceCall[NotUsed, String] = {
    ServiceCall { _ =>
      val book = Book(isbn, name, author, genre)
      ref(isbn).ask(CreateBookCommand(book)).map {
        case Done => s"Hello ${book.author}! Your book has been added."
      }
    }
  }

  def ref(isbn: String): PersistentEntityRef[BookCommand[_]] = {
    persistentEntityRegistry
      .refFor[BookEntity](isbn)
  }

  override def getBookByIsbn(isbn: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
      BookRepository.getBookByName(isbn).map(Book =>
        s"Book for isbn:$isbn has name: ${Book.get.name} , genre: ${Book.get.genre} and author: ${Book.get.author}"
      )
    }

  override def getBookByName(name: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
      BookRepository.getBookByName(name).map(Book =>
        s"Book for name:$name has isbn: ${Book.get.isbn} , genre: ${Book.get.genre} and author: ${Book.get.author}"
      )
    }

  override def getBookByAuthor(author: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ =>
      BookRepository.getBookByAuthor(author).map(Book =>
        s"Book for author:$author has isbn: ${Book.get.isbn} , genre: ${Book.get.genre} and name: ${Book.get.name}"
      )
    }

  override def deleteBook(isbn: String): ServiceCall[NotUsed, Done] = ServiceCall { _ =>
    persistentEntityRegistry.refFor[BookEntity](isbn)
      .ask(DeleteBookCommand(isbn)).map(_ => {
      log.info(s"Book with book isbn ${isbn}, successfully deleted.")
      Done.getInstance()
    })
  }

  override def updateBook(isbn: String): ServiceCall[Book, Done] =
    ServiceCall { request =>
      persistentEntityRegistry
        .refFor[BookEntity](isbn).ask(UpdateBookCommand(request))
        .map(_ => {
          log.info(s"Book with book isbn ${isbn}, successfully " +
            s"updated.")
          Done.getInstance()
        })
    }

}
