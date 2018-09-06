
package com.knoldus.book.impl.eventsourcing

import com.knoldus.book.api.Book
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventShards, AggregateEventTag, AggregateEventTagger}
import play.api.libs.json.{Format, Json}

trait BookEvent extends AggregateEvent[BookEvent] {
  override def aggregateTag: AggregateEventTagger[BookEvent] = BookEvent.Tag
}

object BookEvent {
  val numberOfShards = 4
  val Tag: AggregateEventShards[BookEvent] = AggregateEventTag.sharded[BookEvent](numberOfShards)
}

case class BookCreated(book: Book) extends BookEvent

object BookCreated{
  implicit val format: Format[BookCreated] = Json.format
}
