package mdas.tobaccomachines.factory
import mdas.tobaccomachines.TobaccoProduct

case class CigarettePack(cigarettes: Seq[Cigarette]) extends FactoryProduct {
  override def toTobaccoProduct: TobaccoProduct = TobaccoProduct(price = 1.5f)
}
