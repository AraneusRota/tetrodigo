package violetempiregames.init

import indigo.*

final case class ViewConfig(
    magnificationLevel: Int,
    viewport: GameViewport
)

object ViewConfig:
  val default: ViewConfig = ViewConfig(1, GameViewport(400, 800))
