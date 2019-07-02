package mdas


case class TobaccoMachineStock(productStocks: Seq[TobaccoProductStock]) {
  def fillStock(): TobaccoMachineStock = copy(productStocks = productStocks.map(_.fill()))

  def buyProduct(productName: String): (TobaccoMachineStock, Option[TobaccoProduct]) = getProductStock(productName) match {
    case None => (copy(), None)
    case Some(productStock: TobaccoProductStock) => {
      val (newProductStock: TobaccoProductStock, product: Option[TobaccoProduct]) = productStock.buy()
      (copy(productStocks = substituteProductStock(productStock, newProductStock)), product)
    }
  }

  private def getProductStock(productName: String): Option[TobaccoProductStock] = productStocks.find(_.name == productName)

  private def substituteProductStock(oldProductStock: TobaccoProductStock, newProductStock: TobaccoProductStock) = {
    val indexOfStock = productStocks.indexOf(oldProductStock)
    productStocks.patch(indexOfStock, List(newProductStock), 1)
  }
}
