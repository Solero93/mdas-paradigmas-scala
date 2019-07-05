package mdas.factory

sealed trait CigaretteIngredient


case object Paper extends CigaretteIngredient

case object TobaccoLeaves extends CigaretteIngredient

case object Nicotine extends CigaretteIngredient

case object Sugar extends CigaretteIngredient

case object Tar extends CigaretteIngredient

case object Arsenic extends CigaretteIngredient

case object Filter extends CigaretteIngredient
