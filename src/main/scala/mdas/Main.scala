package mdas

object Main {
  def main(args: Array[String]): Unit = {
    val tobaccoRubio = TobaccoProduct("Tabaco Rubio", 1.50f)
    val purito = TobaccoProduct("Purito", 0.90f)

    val tobaccoRubioStock = TobaccoProductStock(tobaccoRubio, 100)
    val puritoStock = TobaccoProductStock(purito, 30)

    val machineStock = TobaccoMachineStock(Seq(tobaccoRubioStock, puritoStock))

    val tobaccoMachine = TobaccoMachine("Machine 1", machineStock)
    val tobaccoMachine2 = tobaccoMachine.copy(name = "Machine 2")
    val tobaccoMachine3 = tobaccoMachine.copy(name = "Machine 3")

    // Para demostrar que las máquinas pueden variar
    val tobaccoMachineGroup = TobaccoMachineGroup(Seq(tobaccoMachine, tobaccoMachine2))
      .addMachine(tobaccoMachine3)
      .removeMachine(tobaccoMachine2)
      .fillStocks()

    val (machineGroupAfterBuyingPurito: TobaccoMachineGroup, product: Option[TobaccoProduct]) =
      tobaccoMachineGroup.buyProduct("Machine 1", "Purito")

    println(s"${product.get} bought from $machineGroupAfterBuyingPurito")

    val (machineGroupAfterBuyingPuritoAndTabacoRubio: TobaccoMachineGroup, product2: Option[TobaccoProduct]) =
      machineGroupAfterBuyingPurito.buyProduct("Machine 3", "Tabaco Rubio")

    println(s"${product2.get} bought from $machineGroupAfterBuyingPuritoAndTabacoRubio")

    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfTheDayOfAllMachines())
    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfTheDayOfMachine("Machine 1"))
    println(machineGroupAfterBuyingPuritoAndTabacoRubio.calculateEarningsOfTheDayOfMachine("Machine 3"))
  }
}

