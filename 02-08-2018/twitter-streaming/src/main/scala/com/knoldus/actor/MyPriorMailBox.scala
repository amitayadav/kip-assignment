
package com.knoldus.actor

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedStablePriorityMailbox}
import com.knoldus.app.Tweet
import com.typesafe.config.Config


class MyPriorMailBox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedStablePriorityMailbox(

    PriorityGenerator {

      case tweet: Tweet if tweet.friendsCount > 500 => 0

      case _ => 1
    }
  )