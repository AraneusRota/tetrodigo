package violetempiregames.model

import indigo.*
import indigo.syntax.*

import scala.language.postfixOps

final case class TimeTicks(last: Seconds, rate: Seconds):
  def tickOccurred(gameTime: GameTime): Boolean =
    last + rate < gameTime.running

  def update(gameTime: GameTime): TimeTicks =
    if tickOccurred(gameTime) then TimeTicks(gameTime.running, rate)
    else this

object TimeTicks:
  val initial: TimeTicks = TimeTicks(0 seconds, 0.2 seconds)
