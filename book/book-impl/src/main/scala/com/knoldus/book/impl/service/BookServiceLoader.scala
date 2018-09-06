
package com.knoldus.book.impl.service

import com.knoldus.book.api.BookService
import com.knoldus.book.impl.eventsourcing.{BookEntity, BookProcessor, BookRepository}
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

class BookServiceLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new BookServiceApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new BookServiceApplication(context) with LagomDevModeComponents
}

abstract class BookServiceApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with AhcWSComponents {

  override lazy val lagomServer: LagomServer = serverFor[BookService](wire[BookServiceImpl])

  override lazy val jsonSerializerRegistry: BookSerializerRegistry.type = BookSerializerRegistry

  persistentEntityRegistry.register(wire[BookEntity])

  lazy val repository: BookRepository = wire[BookRepository]

  readSide.register(wire[BookProcessor])
}

