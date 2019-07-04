package mdas

import java.util.UUID
import java.util.UUID.randomUUID

case class TobaccoMachineGroup(uuid: UUID = randomUUID(), children: Seq[TobaccoMachineHierarchyNode]) extends TobaccoMachineHierarchyNode {
  def addChild(tobaccoMachineHierarchyNode: TobaccoMachineHierarchyNode): TobaccoMachineGroup = copy(children = children :+ tobaccoMachineHierarchyNode)

  def removeChild(tobaccoMachineHierarchyNode: TobaccoMachineHierarchyNode): TobaccoMachineGroup = copy(children = children.filter(_ != tobaccoMachineHierarchyNode))

  override def fillStock(): TobaccoMachineHierarchyNode = copy(children = children.map(_.fillStock()))

  override def buyProduct(machineUUID: UUID, productUUID: UUID): (TobaccoMachineHierarchyNode, Option[TobaccoProduct]) = {
    val childrenAfterBuyProduct = children.map(_.buyProduct(machineUUID, productUUID))
    (copy(children = childrenAfterBuyProduct.map(_._1)), childrenAfterBuyProduct.map(_._2).find(_.isDefined).head)
  }

  override def calculateEarningsOfMachine(machineUUID: UUID): Float = children.map(_.calculateEarningsOfMachine(machineUUID)).sum

  override def calculateEarnings(): Float = children.map(_.calculateEarnings()).sum
}