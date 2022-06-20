package violetempiregames.model

import indigoextras.geometry.BoundingBox
import indigoextras.geometry.LineSegment
import indigoextras.geometry.Vertex

object PlayArea:
  val height: Double = 20
  val width: Double  = 10

  val horizontalCenter: Double = width / 2 - 1
  val verticalMiddle: Double   = height / 2 - 1

  val topLeft: Vertex     = Vertex(0, 0)
  val topRight: Vertex    = Vertex(width, 0)
  val bottomLeft: Vertex  = Vertex(0, height)
  val bottomRight: Vertex = Vertex(width, height)

  val top: Double    = 0
  val bottom: Double = height

  val boundingBox: BoundingBox = BoundingBox(width, height)
