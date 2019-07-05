package mdas.factory

import mdas.tobaccomachines.TobaccoProduct

trait TobaccoFactory {
  def produce: TobaccoProduct
}
