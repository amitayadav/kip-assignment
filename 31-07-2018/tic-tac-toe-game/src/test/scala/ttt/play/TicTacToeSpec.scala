
import akka.actor.{ActorSystem, Props}
import akka.testkit.{DefaultTimeout, ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import ttt.entites._
import scala.concurrent.duration._

class TicTacToeSpec() extends TestKit(ActorSystem("MySpec")) with DefaultTimeout with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {




  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Game actor" should {

    val gameRef = system.actorOf(Props[Game])

    /*"be able to identify valid index" in {
      within(3000 millis) {
        gameRef ! PlayStep(10, 1)
        expectMsg("invalid index")
      }
    }
*/
    "be able to change and return the state of tic tac toe game" in {
      gameRef ! PlayStep(1, 1)
      expectMsgAllClassOf(classOf[TicTacToeMap])
    }

    "be able to send Tic Tac Toe Map and a GameOver when a condition is met" in {
      gameRef ! PlayStep(7, 2)
      expectMsgAllClassOf(classOf[TicTacToeMap])
      gameRef ! PlayStep(9, 1)
      expectMsgAllClassOf(classOf[TicTacToeMap])
      gameRef ! PlayStep(3, 2)
      expectMsgAllClassOf(classOf[TicTacToeMap])
      gameRef ! PlayStep(5, 1)



      //expectMsgAllClassOf(classOf[TicTacToeMap], classOf[TicTacToeMap], classOf[TicTacToeMap], classOf[TicTacToeMap], classOf[TicTacToeMap])
      expectMsg(GameOver)

    }

    "be able to send PlaceAlreadyFilled Message when a PlayStep is sent on a non empty step" in {
      gameRef ! PlayStep(1, 1)
      expectMsgAllClassOf(classOf[PlaceAlreadyFilled])

    }
  }
}