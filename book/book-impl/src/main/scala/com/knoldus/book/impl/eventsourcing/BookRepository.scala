package com.knoldus.book.impl.eventsourcing

import akka.Done
import com.datastax.driver.core.{BoundStatement, PreparedStatement}
import com.knoldus.book.api.Book
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraSession

import scala.concurrent.{ExecutionContext, Future}

class BookRepository(session: CassandraSession)(implicit ec: ExecutionContext) {

  var bookStatement: PreparedStatement = _

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
  }

  def createPreparedStatements: Future[Done] = {
    for{
      bookPreparedStatement <- session.prepare("INSERT INTO booktable(isbn, name, author , genre) VALUES (?, ?, ?, ?)")
    } yield{
      bookStatement = bookPreparedStatement
      Done
    }
  }

  def storeBook(book: Book): Future[List[BoundStatement]] = {
    val bookBindStatement = bookStatement.bind()
    bookBindStatement.setString("isbn", book.isbn)
    bookBindStatement.setString("name", book.name)
    bookBindStatement.setString("author", book.author)
    bookBindStatement.setString("genre", book.genre)
    Future.successful(List(bookBindStatement))
  }

  def getBookByName(name: String): Future[Option[Book]] =
    session.selectOne(s"SELECT * FROM booktable WHERE name = '$name'").map{optRow =>
      optRow.map{row =>
        val isbn = row.getString("isbn")
        val name = row.getString("name")
        val author = row.getString("author")
        val genre = row.getString("genre")
        Book(isbn, name, author , genre)
      }
    }
}
