package violetempiregames.init

import indigo.*

object Assets:
  private def png(
      assetPathWithoutPrefixAndExtension: AssetName
  ): AssetType.Image = AssetType.Image(
    assetPathWithoutPrefixAndExtension,
    AssetPath(s"assets/$assetPathWithoutPrefixAndExtension.png")
  )

  object Tile:
    private val prefix   = "tile"
    val green: AssetName = AssetName(s"$prefix/green")
    val pink: AssetName  = AssetName(s"$prefix/pink")
    val red: AssetName   = AssetName(s"$prefix/red")
  val tile: Tile.type = Tile

  val assets: Set[AssetType] = Set(
    png(tile.green),
    png(tile.pink),
    png(tile.red)
  )
