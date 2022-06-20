package violetempiregames.view

import indigo.*
import indigoextras.geometry.Vertex
import violetempiregames.init.Assets
import violetempiregames.model.Block

object BlockView:
  def apply(blocks: Batch[Block]): Batch[SceneNode] =
    blocks flatMap apply

  def apply(block: Block): Batch[SceneNode] =
    val asset = block match
      case Block.T(_, _) => Assets.tile.pink
      case Block.Z(_, _) => Assets.tile.red
    val tileSize = View.size(Vertex(1, 1))
    val tileGraphic = Graphic(
      tileSize,
      Material.Bitmap(asset).stretch
    )
    block.allTilePositions.map(
      tileGraphic moveTo View.point(_)
    )
