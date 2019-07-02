package mdas


case class TobaccoMachineGroup(tobaccoMachines: Seq[TobaccoMachine]) {
  def fillStocks(): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines.map(_.fillStock()))

  def calculateEarningsOfTheDayOfAllMachines(): Float = tobaccoMachines.map(_.earnings).sum

  def calculateEarningsOfTheDayOfMachine(machineName: String): Float = getMachine(machineName).map(_.earnings).sum

  private def getMachine(machineName: String): Option[TobaccoMachine] = tobaccoMachines.find(_.name == machineName)

  def buyProduct(machineName: String, productName: String): (TobaccoMachineGroup, Option[TobaccoProduct]) = getMachine(machineName) match {
    case None => (copy(), None)
    case Some(machine: TobaccoMachine) => {
      val (tobaccoMachine: TobaccoMachine, tobaccoProduct: Option[TobaccoProduct]) = machine.buyProduct(productName)
      (copy(tobaccoMachines = substituteMachine(tobaccoMachine)), tobaccoProduct)
    }
  }

  private def substituteMachine(machine: TobaccoMachine) = {
    val indexOfStock = tobaccoMachines.indexOf(machine)
    tobaccoMachines.patch(indexOfStock, List(machine), 1)
  }
}