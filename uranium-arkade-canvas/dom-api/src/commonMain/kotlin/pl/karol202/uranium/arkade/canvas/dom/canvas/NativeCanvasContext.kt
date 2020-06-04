package pl.karol202.uranium.arkade.canvas.dom.canvas

import pl.karol202.uranium.arkade.canvas.dom.assets.NativeImage
import pl.karol202.uranium.arkade.canvas.dom.values.NativeFillStyle
import pl.karol202.uranium.arkade.canvas.dom.values.NativeGradient
import pl.karol202.uranium.arkade.canvas.dom.values.NativePath

expect class NativeCanvasContext
{
	val canvas: NativeCanvas

	var fillStyle: NativeFillStyle
	var font: String
	var textAlign: String
	var textBaseline: String
	var direction: String

	fun fillRect(x: Double, y: Double, width: Double, height: Double)

	fun clearRect(x: Double, y: Double, width: Double, height: Double)

	fun drawImage(image: NativeImage, drawX: Double, drawY: Double)

	fun drawImage(image: NativeImage, drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double)

	fun drawImage(image: NativeImage, sourceX: Double, sourceY: Double, sourceWidth: Double, sourceHeight: Double,
	              drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double)

	fun fillText(text: String, x: Double, y: Double)

	fun fillText(text: String, x: Double, y: Double, maxWidth: Double)

	fun beginPath()

	fun moveTo(x: Double, y: Double)

	fun lineTo(x: Double, y: Double)

	fun arc(x: Double, y: Double, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean)

	fun closePath()

	fun fill(fillRule: String)

	fun fill(path: NativePath, fillRule: String)

	fun save()

	fun restore()

	fun translate(x: Double, y: Double)

	fun scale(x: Double, y: Double)

	fun createLinearGradient(x0: Double, y0: Double, x1: Double, y1: Double): NativeGradient

	fun createRadialGradient(x0: Double, y0: Double, r0: Double, x1: Double, y1: Double, r1: Double): NativeGradient
}
