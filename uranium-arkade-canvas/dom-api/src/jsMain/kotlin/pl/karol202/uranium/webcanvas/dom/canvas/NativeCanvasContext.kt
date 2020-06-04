package pl.karol202.uranium.webcanvas.dom.canvas

import org.w3c.dom.*
import pl.karol202.uranium.webcanvas.dom.assets.NativeImage
import pl.karol202.uranium.webcanvas.dom.values.NativeFillStyle
import pl.karol202.uranium.webcanvas.dom.values.NativeGradient
import pl.karol202.uranium.webcanvas.dom.values.NativePath

actual class NativeCanvasContext(private val context: CanvasRenderingContext2D)
{
	actual val canvas get() = NativeCanvas(context.canvas)

	actual var fillStyle: NativeFillStyle
		get() = NativeFillStyle(context.fillStyle as Any)
		set(value) { context.fillStyle = value.fillStyle }
	actual var font: String
		get() = context.font
		set(value) { context.font = value }
	actual var textAlign: String
		get() = context.textAlign.unsafeCast<String>()
		set(value) { context.textAlign = value.unsafeCast<CanvasTextAlign>() }
	actual var textBaseline: String
		get() = context.textBaseline.unsafeCast<String>()
		set(value) { context.textBaseline = value.unsafeCast<CanvasTextBaseline>() }
	actual var direction: String
		get() = context.direction.unsafeCast<String>()
		set(value) { context.direction = value.unsafeCast<CanvasDirection>() }

	actual fun fillRect(x: Double, y: Double, width: Double, height: Double) = context.fillRect(x, y, width, height)

	actual fun clearRect(x: Double, y: Double, width: Double, height: Double) = context.clearRect(x, y, width, height)

	actual fun drawImage(image: NativeImage, drawX: Double, drawY: Double) =
			context.drawImage(image.image, drawX, drawY)

	actual fun drawImage(image: NativeImage, drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double) =
			context.drawImage(image.image, drawX, drawY, drawWidth, drawHeight)

	actual fun drawImage(image: NativeImage, sourceX: Double, sourceY: Double, sourceWidth: Double, sourceHeight: Double,
	                     drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double) =
			context.drawImage(image.image, sourceX, sourceY, sourceWidth, sourceHeight, drawX, drawY, drawWidth, drawHeight)

	actual fun fillText(text: String, x: Double, y: Double) = context.fillText(text, x, y)

	actual fun fillText(text: String, x: Double, y: Double, maxWidth: Double) = context.fillText(text, x, y, maxWidth)

	actual fun beginPath() = context.beginPath()

	actual fun moveTo(x: Double, y: Double) = context.moveTo(x, y)

	actual fun lineTo(x: Double, y: Double) = context.lineTo(x, y)

	actual fun arc(x: Double, y: Double, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean) =
			context.arc(x, y, radius, startAngle, endAngle, anticlockwise)

	actual fun closePath() = context.closePath()

	actual fun fill(fillRule: String) = context.fill(fillRule.unsafeCast<CanvasFillRule>())

	actual fun fill(path: NativePath, fillRule: String) = context.fill(path.path2D, fillRule.unsafeCast<CanvasFillRule>())

	actual fun save() = context.save()

	actual fun restore() = context.restore()

	actual fun translate(x: Double, y: Double) = context.translate(x, y)

	actual fun scale(x: Double, y: Double) = context.scale(x, y)

	actual fun createLinearGradient(x0: Double, y0: Double, x1: Double, y1: Double) =
			NativeGradient(context.createLinearGradient(x0, y0, x1, y1))

	actual fun createRadialGradient(x0: Double, y0: Double, r0: Double, x1: Double, y1: Double, r1: Double) =
			NativeGradient(context.createRadialGradient(x0, y0, r0, x1, y1, r1))
}
