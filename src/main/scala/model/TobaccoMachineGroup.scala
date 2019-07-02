package model

case class TobaccoMachineGroup(tobaccoMachines: Seq[TobaccoMachine] = Seq()) {
  def addMachine(tobaccoMachineToAdd: TobaccoMachine*): Unit = copy(tobaccoMachines = tobaccoMachines ++ tobaccoMachineToAdd)

  def removeMachine(tobaccoMachinesToAdd: TobaccoMachine*): Unit = copy(tobaccoMachines = tobaccoMachines diff tobaccoMachinesToAdd)

  def calculateEarningsOfTheDayOfAllMachines(): Float = tobaccoMachines.map(_.earnings).sum

  def calculateEarningsOfTheDayOfMachine(machineName: String): Float = getMachine(machineName).map(_.earnings).sum

  def buyProduct(machineName: String, productName: String): (TobaccoMachineGroup, Option[TobaccoProduct]) = getMachine(machineName) match {
    case None => (copy(), None)
    case Some(machine: TobaccoMachine) => {
      val (tobaccoMachine: TobaccoMachine, tobaccoProduct: Option[TobaccoProduct]) = machine.buyProduct(productName)
      (copy(tobaccoMachines = substituteMachine(tobaccoMachine)), tobaccoProduct)
    }
  }

  private def getMachine(machineName: String): Option[TobaccoMachine] = tobaccoMachines.find(_.name == machineName)

  private def substituteMachine(machine: TobaccoMachine) = {
    val indexOfStock = tobaccoMachines.indexOf(machine)
    tobaccoMachines.patch(indexOfStock, List(machine), 1)
  }
}