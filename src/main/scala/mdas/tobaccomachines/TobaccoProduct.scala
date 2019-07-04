package mdas.tobaccomachines

import java.util.UUID
import java.util.UUID.randomUUID

case class TobaccoProduct(uuid: UUID = randomUUID(), price: Float)