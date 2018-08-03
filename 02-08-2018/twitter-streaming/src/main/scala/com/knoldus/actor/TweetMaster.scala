
package com.knoldus.actor

import akka.actor.{Actor, Props, Terminated}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}
import com.knoldus.app.Tweet

class TweetMaster extends Actor {
  val routeeNo = 5
  var router = {
    val routees = Vector.fill(routeeNo) {
      val r = context.actorOf(Props[TweetWorker].withDispatcher("akka.my-dispatcher"))
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  def receive: PartialFunction[Any, Unit] = {
    case buffer: List[Tweet] =>
      for (i <- buffer.indices)
        router.route(buffer(i), sender())
    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(Props[TweetWorker])
      context watch r
      router = router.addRoutee(r)
  }

}
