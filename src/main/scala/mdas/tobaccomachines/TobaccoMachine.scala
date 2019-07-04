package mdas.tobaccomachines

import java.util.UUID
import java.util.UUID.randomUUID

case class TobaccoMachine(uuid: UUID = randomUUID(), machineStock: TobaccoMachineStock, earnings: Float = 0.0f) extends TobaccoMachineHierarchyNode {
  override def fillStock(): TobaccoMachine = copy(machineStock = machineStock.fillStock())

  override def buyProduct(machineUUID: UUID, productUUID: UUID): (TobaccoMachine, Option[TobaccoProduct]) = {
    if (this.uuid != machineUUID) {
      return (copy(), None)
    }
    val (tobaccoMachineStock: TobaccoMachineStock, product: Option[TobaccoProduct]) = machineStock.buyProduct(productUUID)
    product match {
      case None => (copy(), None)
      case Some(realProduct: TobaccoProduct) => (copy(machineStock = tobaccoMachineStock, earnings = earnings + realProduct.price), product)
    }
  }

  override def calculateEarningsOfMachine(machineUUID: UUID): Float = if (this.uuid == machineUUID) earnings else 0.0f

  override def calculateEarnings(): Float = earnings
}