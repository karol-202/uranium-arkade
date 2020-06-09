package pl.karol202.uranium.arkade.htmlcanvas.draw

import pl.karol202.uranium.arkade.htmlcanvas.assets.Image
import pl.karol202.uranium.arkade.htmlcanvas.dom.canvas.NativeCanvasContext
import pl.karol202.uranium.arkade.htmlcanvas.values.*

internal val NativeCanvasContext.asDrawContext get() = DrawContext(this)

class DrawContext internal constructor(internal val native: NativeCanvasContext)
{
	val clientSize: Vector
		get() = Vector(native.canvas.clientWidth.toDouble(), native.canvas.clientHeight.toDouble())
	var size: Vector
		get() = Vector(native.canvas.width.toDouble(), native.canvas.height.toDouble())
		set(value)
		{
			native.canvas.width = value.x.toInt()
			native.canvas.height = value.y.toInt()
		}

	fun fillRect(start: Vector, size: Vector) =
			native.fillRect(start.x, start.y, size.x, size.y)

	fun fillViewport() = fillRect(Vector.ZERO, size)

	fun clearRect(start: Vector, size: Vector) =
			native.clearRect(start.x, start.y, size.x, size.y)

	fun clearViewport() = clearRect(Vector.ZERO, size)

	fun drawImage(image: Image, drawPosition: Vector) =
			native.drawImage(image.native, drawPosition.x, drawPosition.y)

	fun drawImage(image: Image, drawPosition: Vector, drawSize: Vector) =
			native.drawImage(image.native, drawPosition.x, drawPosition.y, drawSize.x, drawSize.y)

	fun drawImage(image: Image,
	              sourcePosition: Vector, sourceSize: Vector,
	              drawPosition: Vector, drawSize: Vector) =
			native.drawImage(image.native,
			                 sourcePosition.x, sourcePosition.y, sourceSize.x, sourceSize.y,
			                 drawPosition.x, drawPosition.y, drawSize.x, drawSize.y)

	fun fillText(text: String, position: Vector) =
			native.fillText(text, position.x, position.y)

	fun fillText(text: String, position: Vector, maxWidth: Double) =
			native.fillText(text, position.x, position.y, maxWidth)

	fun beginPath() = native.beginPath()

	fun moveTo(position: Vector) =
			native.moveTo(position.x, position.y)

	fun lineTo(position: Vector) =
			native.lineTo(position.x, position.y)

	fun arc(center: Vector, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean = false) =
			native.arc(center.x, center.y, radius, startAngle, endAngle, anticlockwise)

	fun closePath() = native.closePath()

	fun fill(fillRule: PathFillRule = PathFillRule.NONZERO) =
			native.fill(fillRule.native)

	fun fill(path: Path, fillRule: PathFillRule = PathFillRule.NONZERO) =
			native.fill(path.native, fillRule.native)

	fun save() =
			native.save()

	fun restore() =
			native.restore()

	fun translate(vector: Vector) =
			native.translate(vector.x, vector.y)

	fun scale(vector: Vector) =
			native.scale(vector.x, vector.y)

	internal fun createLinearGradient(start: Vector, end: Vector) =
			native.createLinearGradient(start.x, start.y, end.x, end.y)

	internal fun createRadialGradient(start: Vector, startRadius: Double, end: Vector, endRadius: Double) =
			native.createRadialGradient(start.x, start.y, startRadius, end.x, end.y, endRadius)

	fun setFillStyle(fillStyle: FillStyle)
	{
		native.fillStyle = fillStyle.createNativeStyle(this)
	}

	fun setFont(font: Font)
	{
		native.font = font.asText
	}

	fun setTextAlign(textAlign: TextAlign)
	{
		native.textAlign = textAlign.native
	}

	fun setTextBaseline(textBaseline: TextBaseline)
	{
		native.textBaseline = textBaseline.native
	}

	fun setDirection(direction: TextDirection)
	{
		native.direction = direction.native
	}
}
