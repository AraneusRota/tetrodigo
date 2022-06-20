package violetempiregames.model

import indigo.*
import indigo.syntax.*

import scala.language.postfixOps

final case class Model(blocks: Batch[Block], timeTicks: TimeTicks):
  def update(context: FrameContext[Unit], event: GlobalEvent): Model =
    Model(
      Block.update(blocks, timeTicks.tickOccurred(context.gameTime), event),
      timeTicks.update(context.gameTime)
    )

object Model:
  val initial: Model = Model(Batch(Block.t), TimeTicks.initial)
