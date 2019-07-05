package mdas.tobaccomachines

import java.util.UUID.randomUUID

import akka.actor.{ActorRef, ActorSystem, Props}
import mdas.tobaccomachines.factory.TobaccoRubioFactory
import mdas.tobaccomachines.messages.CreateTobaccoRequest
import mdas.tobaccomachines.replenisher.TobaccoReplenisher

object Main {
  def main(args: Array[String]): Unit = {
    val tobaccoRubio = TobaccoProduct(price = 1.50f)
    val purito = TobaccoProduct(price = 0.90f)

    val tobaccoRubioStock = TobaccoProductStock(product = tobaccoRubio, maxUnits = 100)
    val puritoStock = TobaccoProductStock(product = purito, maxUnits = 30)

    val machineStock = TobaccoMachineStock(productStocks = Seq(tobaccoRubioStock, puritoStock))

    val tobaccoMachine = TobaccoMachine(machineStock = machineStock).fillStock()
    val tobaccoMachine2 = tobaccoMachine.copy(uuid = randomUUID())
    val tobaccoMachine3 = tobaccoMachine.copy(uuid = randomUUID())

    val tobaccoMachinesOfMall1 = TobaccoMachineGroup(children = Seq(tobaccoMachine, tobaccoMachine2))
      .fillStock()

    val tobaccoMachineOfMall2 = TobaccoMachineGroup(children = Seq(tobaccoMachine3))
      .fillStock()

    val tobaccoMachinesOfCity = TobaccoMachineGroup(children = Seq(tobaccoMachinesOfMall1, tobaccoMachineOfMall2))

    val (machinesOfCityAfterBuyingPurito: TobaccoMachineGroup, product: Option[TobaccoProduct]) =
      tobaccoMachinesOfCity.buyProduct(tobaccoMachine.uuid, purito.uuid)

    val (machinesOfCityAfterBuyingPuritoAndTabacoRubio: TobaccoMachineGroup, product2: Option[TobaccoProduct]) =
      machinesOfCityAfterBuyingPurito.buyProduct(tobaccoMachine3.uuid, tobaccoRubio.uuid)

    println(machinesOfCityAfterBuyingPuritoAndTabacoRubio.calculateEarnings())
    println(machinesOfCityAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfMachine(tobaccoMachine.uuid))
    println(machinesOfCityAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfMachine(tobaccoMachine3.uuid))
  }

  val system = ActorSystem("TobaccoFactorySystem")
  val tobaccoReplenisher: ActorRef = system.actorOf(Props[TobaccoReplenisher], name = "tobaccoReplenisher")
  val factory: ActorRef = system.actorOf(Props[TobaccoRubioFactory], name = "tobaccoRubioFactory")
  factory.tell(CreateTobaccoRequest, tobaccoReplenisher)
  system.terminate()
}
