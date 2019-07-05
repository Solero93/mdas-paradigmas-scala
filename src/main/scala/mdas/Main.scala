package mdas

import akka.actor.{ActorRef, ActorSystem, Props}
import mdas.factory.TobaccoRubioFactory
import mdas.messages.CreateTobaccoRequest
import mdas.replenisher.TobaccoReplenisher

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("TobaccoFactorySystem")
    val tobaccoReplenisher: ActorRef = system.actorOf(Props[TobaccoReplenisher], name = "tobaccoReplenisher")
    val factory: ActorRef = system.actorOf(Props[TobaccoRubioFactory], name = "tobaccoRubioFactory")
    factory.tell(CreateTobaccoRequest, tobaccoReplenisher)
    system.terminate()
  }
}
