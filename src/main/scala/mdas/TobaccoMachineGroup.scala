package mdas

import java.util.UUID
import java.util.UUID.randomUUID

case class TobaccoMachineGroup(uuid: UUID = randomUUID(), tobaccoMachines: Seq[TobaccoMachine]) {
  def addMachine(tobaccoMachine: TobaccoMachine): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines :+ tobaccoMachine)

  def removeMachine(tobaccoMachine: TobaccoMachine): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines.filter(_ != tobaccoMachine))

  def calculateEarningsOfTheDayOfAllMachines(): Float = tobaccoMachines.map(_.earnings).sum

  def calculateEarningsOfTheDayOfMachine(machineUUID: UUID): Float = getMachine(machineUUID).map(_.earnings).sum

  def fillStocks(): TobaccoMachineGroup = copy(tobaccoMachines = tobaccoMachines.map(_.fillStock()))

  def buyProduct(machineUUID: UUID, productUUID: UUID): (TobaccoMachineGroup, Option[TobaccoProduct]) = getMachine(machineUUID) match {
    case None => (copy(), None)
    case Some(machine: TobaccoMachine) =>
      val (tobaccoMachine: TobaccoMachine, tobaccoProduct: Option[TobaccoProduct]) = machine.buyProduct(productUUID)
      (copy(tobaccoMachines = substituteMachine(machine, tobaccoMachine)), tobaccoProduct)
  }

  private def getMachine(machineUUID: UUID): Option[TobaccoMachine] = tobaccoMachines.find(_.uuid == machineUUID)

  private def substituteMachine(originalMachine: TobaccoMachine, newMachine: TobaccoMachine) = {
    val indexOfStock = tobaccoMachines.indexOf(originalMachine)
    tobaccoMachines.patch(indexOfStock, List(newMachine), 1)
  }
}