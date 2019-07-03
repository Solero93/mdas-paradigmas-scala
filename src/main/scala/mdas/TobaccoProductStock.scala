package mdas

import java.util.UUID

case class TobaccoProductStock(product: TobaccoProduct, maxUnits: Int, var units: Int = 0) {
  val uuid: UUID = product.uuid

  def fill(): TobaccoProductStock = copy(units = maxUnits)

  def buy(): (TobaccoProductStock, Option[TobaccoProduct]) = units match {
    case 0 => (copy(), None)
    case _ => (copy(units = units - 1), Some(product))
  }
}
