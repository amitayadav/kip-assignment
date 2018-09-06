package com.knoldus.book.impl.eventsourcing

import akka.Done
import com.datastax.driver.core.{BoundStatement, PreparedStatement}
import com.knoldus.book.api.Book
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession

import scala.concurrent.{ExecutionContext, Future}

class BookRepository(session: CassandraSession)(implicit ec: ExecutionContext) {

  private var insertBook: PreparedStatement = _

  private var deleteBook: PreparedStatement = _

  private var updateBook: PreparedStatement = _

  def createTable(): Future[Done] = {
    session.executeCreateTable(
      """
        |CREATE TABLE IF NOT EXISTS booktable(
        |isbn text PRIMARY KEY,
        |name text,
        |author text,
        |genre text
        |);
      """.stripMargin)

    session.executeCreateTable(
      """
        |CREATE INDEX IF NOT EXISTS
        |name_index ON booktable (name);
      """.stripMargin)

    session.executeCreateTable(
      """
        |CREATE INDEX IF NOT EXISTS
        |author_index ON booktable (author);
      """.stripMargin)
  }

  def preparedStatements(): Future[Done] =
    session.prepare("INSERT INTO booktable(isbn, name, author , genre) VALUES (?, ?, ?, ?)")
      .map { ps =>
        insertBook = ps
        Done
      }.map(_ => session.prepare("DELETE FROM booktable where isbn = ?").map(ps => {
      deleteBook = ps
      Done
    })).map(_ => session.prepare("UPDATE booktable SET author=?,genre=?,name=? where isbn =?").map(ps => {
      updateBook = ps
      println("Successfully updated")
      Done.getInstance()
    })).flatten

  def storeBook(book: Book): Future[List[BoundStatement]] = {
    val bookBindStatement = insertBook.bind()
    bookBindStatement.setString("isbn", book.isbn)
    bookBindStatement.setString("name", book.name)
    bookBindStatement.setString("author", book.author)
    bookBindStatement.setString("genre", book.genre)
    Future.successful(List(bookBindStatement))
  }

  def getBookByName(name: String): Future[Option[Book]] =
    session.selectOne(s"SELECT * FROM booktable WHERE name = '$name'").map { optRow =>
      optRow.map { row =>
        val isbn = row.getString("isbn")
        val name = row.getString("name")
        val author = row.getString("author")
        val genre = row.getString("genre")
        Book(isbn, name, author, genre)
      }
    }

  def getBookByAuthor(author: String): Future[Option[Book]] =
    session.selectOne(s"SELECT * FROM booktable WHERE author = '$author'").map { optRow =>
      optRow.map { row =>
        val isbn = row.getString("isbn")
        val name = row.getString("name")
        val author = row.getString("author")
        val genre = row.getString("genre")
        Book(isbn, name, author, genre)
      }
    }

  def deleteBook(isbn: String): Future[List[BoundStatement]] = {
    val bindDeleteBook: BoundStatement = deleteBook.bind()
    bindDeleteBook.setString("isbn", isbn)
    Future.successful(List(bindDeleteBook))
  }

  def updateBook(book: Book): Future[List[BoundStatement]] = {
    val bindUpdateBook: BoundStatement = updateBook.bind()
    bindUpdateBook.setString("author", book.author)
    bindUpdateBook.setString("genre", book.genre)
    bindUpdateBook.setString("isbn", book.isbn)
    bindUpdateBook.setString("genre", book.genre)
    Future.successful(List(bindUpdateBook))
  }
}
