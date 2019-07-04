package mdas.tobaccomachines.factory

import mdas.tobaccomachines.TobaccoProduct

trait FactoryProduct {
  def toTobaccoProduct: TobaccoProduct
}
