package violetempiregames.model

import indigo.*
import indigo.shared.events.InputState
import indigoextras.geometry.Vertex

enum Block(
    val mainTile: Vertex,
    val otherTilesOffsets: (Vertex, Vertex, Vertex)
):
  case T(
      centerTile: Vertex,
      override val otherTilesOffsets: (Vertex, Vertex, Vertex)
  ) extends Block(centerTile, otherTilesOffsets)
  case Z(
      centerTile: Vertex,
      override val otherTilesOffsets: (Vertex, Vertex, Vertex)
  ) extends Block(centerTile, otherTilesOffsets)

  def allTilePositions: Batch[Vertex] =
    val otherTilesPositions =
      Batch(otherTilesOffsets._1, otherTilesOffsets._2, otherTilesOffsets._3)
        .map(mainTile + _)
    mainTile :: otherTilesPositions

  def update(tickOccurred: Boolean, blocks: Batch[Block]): Block =
    if !tickOccurred then this
    else
      val fallen: Block =
        val fallenMainTile = mainTile + Vertex(0, 1)
        this match
          case T(_, _) => T(fallenMainTile, otherTilesOffsets)
          case Z(_, _) => Z(fallenMainTile, otherTilesOffsets)

      val outOfBounds = fallen.allTilePositions.exists(_.y >= PlayArea.bottom)

      val otherBlocks = blocks.filter(_ != this)
      val touchesOtherBlock = otherBlocks
        .map {
          _.allTilePositions.exists { otherBlockTilePosition =>
            fallen.allTilePositions.exists(_ == otherBlockTilePosition)
          }
        }
        .exists(identity)

      if outOfBounds || touchesOtherBlock then this
      else fallen
    end if
end Block

object Block:
  val initialCenterTile: Vertex = Vertex(PlayArea.horizontalCenter, -1)

  val t: T = T(initialCenterTile, (Vertex(-1, 0), Vertex(0, -1), Vertex(1, 0)))
  val z: Z = Z(initialCenterTile, (Vertex(-1, -1), Vertex(0, -1), Vertex(1, 0)))

  def update(blocks: Batch[Block], tickOccurred: Boolean, event: GlobalEvent): Batch[Block] =
//    event match
//      case KeyboardEvent.KeyDown(Key.KEY_S) => ??? // TODO: hard fall etc

    val updatedBlocks = blocks.map(_.update(tickOccurred, blocks))
    if tickOccurred && blocks == updatedBlocks then t :: updatedBlocks
    else updatedBlocks
