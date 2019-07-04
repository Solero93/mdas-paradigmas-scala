package mdas

import java.util.UUID

trait TobaccoMachineHierarchyNode {
  def fillStock(): TobaccoMachineHierarchyNode

  def calculateEarningsOfMachine(machineUUID: UUID): Float

  def calculateEarnings(): Float

  def buyProduct(machineUUID: UUID, productUUID: UUID): (TobaccoMachineHierarchyNode, Option[TobaccoProduct])
}
