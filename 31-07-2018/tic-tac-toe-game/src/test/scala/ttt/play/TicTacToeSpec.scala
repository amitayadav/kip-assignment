
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import ttt.entites._

class TicTacToeSpec() extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {


  val gameRef = system.actorOf(Props[Game])

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Game actor" should {

    "be able to change and return the state of tic tac toe game" in {
      gameRef ! PlayStep(1,1)
      expectMsgAllClassOf(classOf[TicTacToeMap])
    }

    "be able to send Tic Tac Toe Map and a GameOver when a condition is met" in {
      gameRef ! PlayStep(5,1)
      gameRef ! PlayStep(7,2)
      gameRef ! PlayStep(1,1)
      gameRef ! PlayStep(9,2)
      gameRef ! PlayStep(3,1)
      gameRef ! PlayStep(8,2)
      expectMsgAllClassOf(classOf[TicTacToeMap],classOf[TicTacToeMap],classOf[TicTacToeMap],classOf[TicTacToeMap],classOf[TicTacToeMap])
      expectMsg(GameOver)

    }

    "be able to send PlaceAlreadyFilled Message when a PlayStep is sent on a non empty step" in {

      gameRef ! PlayStep(2,1)
      gameRef ! PlayStep(2,2)
      expectMsgAllClassOf(classOf[TicTacToeMap],classOf[PlaceAlreadyFilled])



    }
  }
}