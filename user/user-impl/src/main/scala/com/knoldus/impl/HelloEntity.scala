/*
package com.knoldus.impl

import java.time.LocalDateTime

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

class UserEntity extends PersistentEntity {

  override type Command = UserCommand[_]
  override type Event = UserEvent
  override type State = UserState

  
  override def initialState: UserState = UserState("User", LocalDateTime.now.toString)

  override def behavior: Behavior = {
    case UserState(message, _) => Actions().onCommand[UseGreetingMessage, Done] {

      // Command handler for the UseGreetingMessage command
      case (UseGreetingMessage(newMessage), ctx, state) =>
        // In response to this command, we want to first persist it as a
        // GreetingMessageChanged event
        ctx.thenPersist(
          GreetingMessageChanged(newMessage)
        ) { _ =>
          // Then once the event is successfully persisted, we respond with done.
          ctx.reply(Done)
        }

    }.onReadOnlyCommand[User, String] {

      // Command handler for the User command
      case (User(name), ctx, state) =>
        // Reply with a message built from the current message, and the name of
        // the person we're meant to say User to.
        ctx.reply(s"$message, $name!")

    }.onEvent {

      // Event handler for the GreetingMessageChanged event
      case (GreetingMessageChanged(newMessage), state) =>
        // We simply update the current state to use the greeting message from
        // the event.
        UserState(newMessage, LocalDateTime.now().toString)

    }
  }
}

/**
  * The current state held by the persistent entity.
  */
case class UserState(message: String, timestamp: String)

object UserState {
  /**
    * Format for the User state.
    *
    * Persisted entities get snapshotted every configured number of events. This
    * means the state gets stored to the database, so that when the entity gets
    * loaded, you don't need to replay all the events, just the ones since the
    * snapshot. Hence, a JSON format needs to be declared so that it can be
    * serialized and deserialized when storing to and from the database.
    */
  implicit val format: Format[UserState] = Json.format
}

/**
  * This interface defines all the events that the UserEntity supports.
  */
sealed trait UserEvent extends AggregateEvent[UserEvent] {
  def aggregateTag = UserEvent.Tag
}

object UserEvent {
  val Tag = AggregateEventTag[UserEvent]
}

/**
  * An event that represents a change in greeting message.
  */
case class GreetingMessageChanged(message: String) extends UserEvent

object GreetingMessageChanged {

  /**
    * Format for the greeting message changed event.
    *
    * Events get stored and loaded from the database, hence a JSON format
    * needs to be declared so that they can be serialized and deserialized.
    */
  implicit val format: Format[GreetingMessageChanged] = Json.format
}

/**
  * This interface defines all the commands that the UserWorld entity supports.
  */
sealed trait UserCommand[R] extends ReplyType[R]

/**
  * A command to switch the greeting message.
  *
  * It has a reply type of [[Done]], which is sent back to the caller
  * when all the events emitted by this command are successfully persisted.
  */
case class UseGreetingMessage(message: String) extends UserCommand[Done]

object UseGreetingMessage {

  /**
    * Format for the use greeting message command.
    *
    * Persistent entities get sharded across the cluster. This means commands
    * may be sent over the network to the node where the entity lives if the
    * entity is not on the same node that the command was issued from. To do
    * that, a JSON format needs to be declared so the command can be serialized
    * and deserialized.
    */
  implicit val format: Format[UseGreetingMessage] = Json.format
}

/**
  * A command to say User to someone using the current greeting message.
  *
  * The reply type is String, and will contain the message to say to that
  * person.
  */
case class User(name: String) extends UserCommand[String]

object User {

  /**
    * Format for the User command.
    *
    * Persistent entities get sharded across the cluster. This means commands
    * may be sent over the network to the node where the entity lives if the
    * entity is not on the same node that the command was issued from. To do
    * that, a JSON format needs to be declared so the command can be serialized
    * and deserialized.
    */
  implicit val format: Format[User] = Json.format
}

/**
  * Akka serialization, used by both persistence and remoting, needs to have
  * serializers registered for every type serialized or deserialized. While it's
  * possible to use any serializer you want for Akka messages, out of the box
  * Lagom provides support for JSON, via this registry abstraction.
  *
  * The serializers are registered here, and then provided to Lagom in the
  * application loader.
  */
object UserSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[UseGreetingMessage],
    JsonSerializer[User],
    JsonSerializer[GreetingMessageChanged],
    JsonSerializer[UserState]
  )
}
*/
