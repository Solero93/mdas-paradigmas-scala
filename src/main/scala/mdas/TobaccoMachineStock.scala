package mdas

import java.util.UUID


case class TobaccoMachineStock(productStocks: Seq[TobaccoProductStock]) {
  def fillStock(): TobaccoMachineStock = copy(productStocks = productStocks.map(_.fill()))

  def buyProduct(productUUID: UUID): (TobaccoMachineStock, Option[TobaccoProduct]) = getProductStock(productUUID) match {
    case None => (copy(), None)
    case Some(productStock: TobaccoProductStock) =>
      val (newProductStock: TobaccoProductStock, product: Option[TobaccoProduct]) = productStock.buy()
      (copy(productStocks = substituteProductStock(productStock, newProductStock)), product)
  }

  private def getProductStock(productUUID: UUID): Option[TobaccoProductStock] = productStocks.find(_.uuid == productUUID)

  private def substituteProductStock(oldProductStock: TobaccoProductStock, newProductStock: TobaccoProductStock) = {
    val indexOfStock = productStocks.indexOf(oldProductStock)
    productStocks.patch(indexOfStock, List(newProductStock), 1)
  }
}
