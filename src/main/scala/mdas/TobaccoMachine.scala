package mdas

import java.util.UUID
import java.util.UUID.randomUUID


case class TobaccoMachine(uuid: UUID = randomUUID(), machineStock: TobaccoMachineStock, earnings: Float = 0.0f) {
  def fillStock(): TobaccoMachine = copy(machineStock = machineStock.fillStock())

  def buyProduct(productUUID: UUID): (TobaccoMachine, Option[TobaccoProduct]) = {
    val (tobaccoMachineStock: TobaccoMachineStock, product: Option[TobaccoProduct]) = machineStock.buyProduct(productUUID)
    product match {
      case None => (copy(machineStock = tobaccoMachineStock), product)
      case Some(realProduct: TobaccoProduct) => (copy(machineStock = tobaccoMachineStock, earnings = earnings + realProduct.price), product)
    }
  }
}