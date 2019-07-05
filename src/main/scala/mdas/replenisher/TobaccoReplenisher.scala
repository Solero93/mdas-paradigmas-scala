package mdas.replenisher

import akka.actor.Actor
import mdas.messages.NewProductResponse
import mdas.tobaccomachines.TobaccoProduct

class TobaccoReplenisher extends Actor {
  override def receive: Receive = {
    case NewProductResponse(t: TobaccoProduct) => println("We have a new product!", t)
    case _ => println("Unknown message")
  }
}
