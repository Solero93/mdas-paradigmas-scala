package mdas.tobaccomachines.factory

import akka.actor.Actor
import mdas.tobaccomachines.TobaccoProduct
import mdas.tobaccomachines.messages.{CreateTobaccoRequest, NewProductResponse}

class TobaccoRubioFactory extends Actor with TobaccoFactory {
  override def receive: Receive = {
    case CreateTobaccoRequest =>
      sender.tell(NewProductResponse(produce), self)
    case _ =>
      println("I cannot understand", sender)
  }

  override def produce: TobaccoProduct = {
    val cigaretteIngredients = Seq(Paper, TobaccoLeaves, Nicotine, Sugar, Tar, Arsenic, Filter)
    val cigarette = Cigarette(cigaretteIngredients)
    val tobaccoRubioCigarettePack = CigarettePack(Seq.fill(20)(cigarette))
    tobaccoRubioCigarettePack.toTobaccoProduct
  }
}
