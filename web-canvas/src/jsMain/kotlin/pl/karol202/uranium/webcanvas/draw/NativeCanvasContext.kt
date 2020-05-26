package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasFillRule
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import pl.karol202.uranium.webcanvas.assets.NativeImage
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.NativeFillStyle
import pl.karol202.uranium.webcanvas.values.NativeGradient
import kotlin.browser.document

actual class NativeCanvasContext(private val context: CanvasRenderingContext2D)
{
	actual val canvas get() = NativeCanvas(context.canvas)

	actual var fillStyle: NativeFillStyle
		get() = NativeFillStyle(context.fillStyle as Any)
		set(value) { context.fillStyle = value.fillStyle }
	actual var font: String
		get() = context.font
		set(value) { context.font = value }

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

	actual fun fill() = context.fill()

	actual fun fill(fillRule: String) = context.fill(fillRule.unsafeCast<CanvasFillRule>())

	actual fun save() = context.save()

	actual fun restore() = context.restore()

	actual fun translate(x: Double, y: Double) = context.translate(x, y)

	actual fun scale(x: Double, y: Double) = context.scale(x, y)

	actual fun createLinearGradient(x0: Double, y0: Double, x1: Double, y1: Double) =
			NativeGradient(context.createLinearGradient(x0, y0, x1, y1))

	actual fun createRadialGradient(x0: Double, y0: Double, r0: Double, x1: Double, y1: Double, r1: Double) =
			NativeGradient(context.createRadialGradient(x0, y0, r0, x1, y1, r1))
}
