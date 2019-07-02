package mdas

object Main {
  def main(args: Array[String]): Unit = {
    val tobaccoRubio = TobaccoProduct("Tabaco Rubio", 1.50f)
    val purito = TobaccoProduct("Purito", 0.90f)

    val tobaccoRubioList = TobaccoProductStock(tobaccoRubio, 100)
    val puritoList = TobaccoProductStock(purito, 30)

    val machineStock = TobaccoMachineStock(Seq(tobaccoRubioList, puritoList))

    val tobaccoMachine = TobaccoMachine("Machine 1", machineStock)
    val tobaccoMachine2 = tobaccoMachine.copy(name = "Machine 2")
    val tobaccoMachine3 = tobaccoMachine.copy(name = "Machine 3")

    val tobaccoMachineGroup = TobaccoMachineGroup(Seq(tobaccoMachine, tobaccoMachine2, tobaccoMachine3))
    val tobaccoMachineGroup2 = tobaccoMachineGroup.fillStocks()

    val (tobaccoMachineGroup3: TobaccoMachineGroup, product: Option[TobaccoProduct]) =
      tobaccoMachineGroup2.buyProduct("Machine 1", "Purito")

    println(tobaccoMachineGroup3.calculateEarningsOfTheDayOfAllMachines())
    println(tobaccoMachineGroup3.calculateEarningsOfTheDayOfMachine("Machine 1"))
    println(tobaccoMachineGroup3.calculateEarningsOfTheDayOfMachine("Machine 2"))
  }
}

