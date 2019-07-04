package mdas

import java.util.UUID.randomUUID

object Main {
  def main(args: Array[String]): Unit = {
    val tobaccoRubio = TobaccoProduct(price = 1.50f)
    val purito = TobaccoProduct(price = 0.90f)

    val tobaccoRubioStock = TobaccoProductStock(product = tobaccoRubio, maxUnits = 100)
    val puritoStock = TobaccoProductStock(product = purito, maxUnits = 30)

    val machineStock = TobaccoMachineStock(productStocks = Seq(tobaccoRubioStock, puritoStock))

    val tobaccoMachine = TobaccoMachine(machineStock = machineStock)
    val tobaccoMachine2 = tobaccoMachine.copy(uuid = randomUUID())
    val tobaccoMachine3 = tobaccoMachine.copy(uuid = randomUUID())

    // Para demostrar que las máquinas pueden variar
    val tobaccoMachineGroup = TobaccoMachineGroup(children = Seq(tobaccoMachine, tobaccoMachine2))
      .addChild(tobaccoMachine3)
      .removeChild(tobaccoMachine2)
      .fillStock()

    val (machineGroupAfterBuyingPurito: TobaccoMachineGroup, product: Option[TobaccoProduct]) =
      tobaccoMachineGroup.buyProduct(tobaccoMachine.uuid, purito.uuid)

    println(s"${product.get} bought from $machineGroupAfterBuyingPurito")

    val (machineGroupAfterBuyingPuritoAndTabacoRubio: TobaccoMachineGroup, product2: Option[TobaccoProduct]) =
      machineGroupAfterBuyingPurito.buyProduct(tobaccoMachine3.uuid, tobaccoRubio.uuid)

    println(s"${product2.get} bought from $machineGroupAfterBuyingPuritoAndTabacoRubio")

    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarnings())
    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfMachine(tobaccoMachine.uuid))
    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfMachine(tobaccoMachine3.uuid))
  }
}

