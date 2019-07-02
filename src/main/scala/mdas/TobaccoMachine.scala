package mdas


case class TobaccoMachine(name: String, machineStock: TobaccoMachineStock, earnings: Float = 0.0f) {
  def fillStock(): TobaccoMachine = copy(machineStock = machineStock.fillStock())

  def buyProduct(productName: String): (TobaccoMachine, Option[TobaccoProduct]) = {
    val (tobaccoMachineStock: TobaccoMachineStock, product: Option[TobaccoProduct]) = machineStock.buyProduct(productName)
    product match {
      case None => (copy(machineStock = tobaccoMachineStock), product)
      case Some(realProduct: TobaccoProduct) => (copy(machineStock = tobaccoMachineStock, earnings = earnings + realProduct.price), product)
    }
  }
}