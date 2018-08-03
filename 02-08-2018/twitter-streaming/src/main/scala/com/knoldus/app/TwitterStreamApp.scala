
package com.knoldus.app


import akka.actor.{ActorSystem, Props}
import com.knoldus.actor.TweetMaster
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._


case class Tweet(tweetId: String, createdAt: Long, userId: Long, tweetUserName: String, countryName: String, friendsCount: Long)

object TwitterStreamApp extends App {

  val system = ActorSystem("tweetAppSystem")
  val tweetRef= system.actorOf(Props[TweetMaster])
  val initialTime=0
  val timePeriod=50
  val sendTimePeriod=1000



  implicit val executionContext = system.dispatcher
  val buffer: ListBuffer[Tweet] = scala.collection.mutable.ListBuffer[Tweet]()

  system.scheduler.schedule(initialTime millis, timePeriod millis) {
    val rg = scala.util.Random
    buffer += Tweet(java.util.UUID.randomUUID().toString, rg.nextLong(), rg.nextLong(), rg.nextString(10), "India", rg.nextInt(2000))
  }


  system.scheduler.schedule(initialTime millis, sendTimePeriod millis) {

       tweetRef ! buffer.toList
     // buffer.clear()
  }

}


