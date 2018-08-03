
package com.knoldus.actor

import akka.actor.{ActorSystem, Props}
import akka.testkit.{DefaultTimeout, ImplicitSender, TestKit}
import com.knoldus.app.Tweet
import org.scalatest.{Matchers, WordSpecLike}
import scala.concurrent.duration._

class TweetWorkerSpec extends TestKit(ActorSystem(
  "TweetMasterSpec") ) with DefaultTimeout with ImplicitSender with WordSpecLike with Matchers {

  def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An TweetWorker" should {

    val tweetWorkerRef = system.actorOf(Props[TweetWorker])
    val createdAt:Long = 2018
    val userId:Long = 285086
    val friendsCount1:Long = 501
    val friendsCount2:Long = 400
    val friendsCount3: Long=500


    "Respond with the no of tweets which has friend count greater than 500" in {

        tweetWorkerRef ! Tweet("AmitaYa02850861", createdAt, userId, "Amita Yadav", "India", friendsCount1)
        expectMsg("tweets which has friend count greater than 500")

    }

    "Respond with the no of tweets which has friend count less than 500" in {

        tweetWorkerRef ! Tweet("AmitaYa02850861", createdAt, userId, "Amita Yadav", "India", friendsCount2)
        expectMsg("tweets which has friend count less than 500")
    }


    "Respond with the no of tweets " in {

        tweetWorkerRef ! Tweet("AmitaYa02850861", createdAt, userId, "Amita Yadav", "India", friendsCount3)
        expectMsg("total tweets")

    }
  }

}
