package mdas.tobaccomachines.replenisher

import akka.actor.Actor
import mdas.tobaccomachines.TobaccoProduct
import mdas.tobaccomachines.messages.NewProductResponse

class TobaccoReplenisher extends Actor {
  override def receive: Receive = {
    case NewProductResponse(t: TobaccoProduct) => println("We have a new product!", t)
    case _ => println("Unknown message")
  }
}
