package mdas.factory

import mdas.tobaccomachines.TobaccoProduct

trait FactoryProduct {
  def toTobaccoProduct: TobaccoProduct
}
