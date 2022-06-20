package violetempiregames.view

import indigo.shared.datatypes.Point
import indigo.shared.datatypes.Rectangle
import indigo.shared.datatypes.Size
import indigoextras.geometry.BoundingBox
import indigoextras.geometry.Vertex
import violetempiregames.init.ViewConfig
import violetempiregames.model.PlayArea

object View:
  private val viewport = ViewConfig.default.viewport
  private val horizontalScale = viewport.width / PlayArea.width
  private val verticalScale   = viewport.height / PlayArea.height

  def rectangle(boundingBox: BoundingBox): Rectangle =
    def scale(vertex: Vertex) = Vertex(x(vertex.x), y(vertex.y))

    BoundingBox(scale(boundingBox.size))
      .moveTo(scale(boundingBox.position))
      .toRectangle

  def point(vertex: Vertex): Point = Point(x(vertex.x), y(vertex.y))
  def size(vertex: Vertex): Size   = point(vertex).toSize

  def x(x: Double): Int = (x * horizontalScale).round.toInt
  def y(y: Double): Int = (y * verticalScale).round.toInt
