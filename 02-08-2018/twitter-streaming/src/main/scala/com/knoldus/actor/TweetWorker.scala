
package com.knoldus.actor

import akka.actor.Actor
import akka.event.Logging
import com.knoldus.app.Tweet

import scala.collection.mutable.ListBuffer

class TweetWorker extends Actor{

  var counter=0
  val logger = Logging(context.system,this)

  def receive:PartialFunction[Any,Unit]={
    case tweet:  Tweet if tweet.friendsCount > 500 =>
      counter += 1
      logger.info(s"no of tweets which has friend count greater than 500 : $counter")
      sender() ! "tweets which has friend count greater than 500"

    case tweet:  Tweet if tweet.friendsCount < 500 =>
      counter += 1
      logger.info(s"no of tweets which has friend count less than 500 : $counter")
      logger.info(s"$sender() ! tweets which has friend count less than 500")

    case _=>
      counter +=1
      logger.info(s"total tweets: $counter")
      sender() ! "total tweets"

  }

}
