package com.knoldus.book.impl.service

import akka.{Done, NotUsed}
import com.knoldus.book.api.{Book, BookService}
import com.knoldus.book.impl.eventsourcing._
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.{ExceptionMessage, NotFound, TransportErrorCode}
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRef, PersistentEntityRegistry}
import com.typesafe.scalalogging.Logger

import scala.concurrent.ExecutionContext

class BookServiceImpl(persistentEntityRegistry: PersistentEntityRegistry, session: CassandraSession, BookRepository: BookRepository)
                     (implicit ec: ExecutionContext) extends BookService {

  val log = Logger(classOf[BookServiceImpl])

  override def createBook: ServiceCall[Book, Done] = ServiceCall { request =>
   persistentEntityRegistry
      .refFor[BookEntity](request.isbn).ask(CreateBookCommand(request))
      .map(_ => {
         log.info(s"Book with book isbn ${request.isbn} successfully created. ")
         Done.getInstance()
    })
  }

  override def getBookByIsbn(isbn: String): ServiceCall[NotUsed, Book] = ServiceCall { _ =>
    session.selectOne("SELECT * FROM booktable WHERE isbn =?", isbn).map {
      case Some(row) => Book.apply(row.getString("isbn"), row.getString("name"), row.getString("author"),
        row.getString("genre"))
      case None => throw new NotFound(TransportErrorCode.NotFound, new ExceptionMessage("Book Isbn Not Found",
        "Book with this book isbn does not exist"))
    }
  }

  override def getBookByName(name: String): ServiceCall[NotUsed, Book] = ServiceCall { _ =>
      session.selectOne("SELECT * FROM booktable WHERE name =?", name).map {
        case Some(row) => Book.apply(row.getString("isbn"), row.getString("name"), row.getString("author"),
          row.getString("genre"))
        case None => throw new NotFound(TransportErrorCode.NotFound, new ExceptionMessage("Book Name Not Found",
          "Book with this book name does not exist"))
      }
    }

  override def getBookByAuthor(author: String): ServiceCall[NotUsed, Book] = ServiceCall { _ =>
      session.selectOne("SELECT * FROM booktable WHERE author =?", author).map {
        case Some(row) => Book.apply(row.getString("isbn"), row.getString("name"), row.getString("author"),
          row.getString("genre"))
        case None => throw new NotFound(TransportErrorCode.NotFound, new ExceptionMessage("Book Author Not Found",
          "Book with this book author does not exist"))
      }
    }

  override def deleteBook(isbn: String): ServiceCall[NotUsed, Done] = ServiceCall { _ =>
    persistentEntityRegistry.refFor[BookEntity](isbn)
      .ask(DeleteBookCommand(isbn)).map{
      case Done => log.info(s"Book with book isbn $isbn successfully deleted.")
        Done.getInstance()
      case _ =>  throw new NotFound(TransportErrorCode.NotFound, new ExceptionMessage("Book Isbn Not Found",
        "Book with this book isbn does not exist"))
    }
  }

  override def updateBook(isbn: String): ServiceCall[Book, Done] =
    ServiceCall { request =>
      persistentEntityRegistry
        .refFor[BookEntity](isbn).ask(UpdateBookCommand(request))
        .map{
          case Done => log.info(s"Book with book isbn $isbn successfully updated.")
            Done.getInstance()
          case _ =>  throw new NotFound(TransportErrorCode.NotFound, new ExceptionMessage("Book Isbn Not Found",
            "Book with this book isbn does not exist"))
        }
    }

  def ref(isbn: String): PersistentEntityRef[BookCommand[_]] = {
    persistentEntityRegistry
      .refFor[BookEntity](isbn)
  }

}
