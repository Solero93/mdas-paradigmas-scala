package mdas


case class TobaccoMachineGroup(tobaccoMachines: Seq[TobaccoMachine]) {
  def addMachine(tobaccoMachine: TobaccoMachine): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines :+ tobaccoMachine)

  def removeMachine(tobaccoMachine: TobaccoMachine): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines.filter(_ != tobaccoMachine))

  def calculateEarningsOfTheDayOfAllMachines(): Float = tobaccoMachines.map(_.earnings).sum

  def calculateEarningsOfTheDayOfMachine(machineName: String): Float = getMachine(machineName).map(_.earnings).sum

  private def getMachine(machineName: String): Option[TobaccoMachine] = tobaccoMachines.find(_.name == machineName)

  def fillStocks(): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines.map(_.fillStock()))

  def buyProduct(machineName: String, productName: String): (TobaccoMachineGroup, Option[TobaccoProduct]) = getMachine(machineName) match {
    case None => (copy(), None)
    case Some(machine: TobaccoMachine) => {
      val (tobaccoMachine: TobaccoMachine, tobaccoProduct: Option[TobaccoProduct]) = machine.buyProduct(productName)
      (copy(tobaccoMachines = substituteMachine(machine, tobaccoMachine)), tobaccoProduct)
    }
  }

  private def substituteMachine(originalMachine: TobaccoMachine, newMachine: TobaccoMachine) = {
    val indexOfStock = tobaccoMachines.indexOf(originalMachine)
    tobaccoMachines.patch(indexOfStock, List(newMachine), 1)
  }
}