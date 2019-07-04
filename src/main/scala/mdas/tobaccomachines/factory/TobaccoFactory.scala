package mdas.tobaccomachines.factory

import mdas.tobaccomachines.TobaccoProduct

trait TobaccoFactory {
  def produce: TobaccoProduct
}
