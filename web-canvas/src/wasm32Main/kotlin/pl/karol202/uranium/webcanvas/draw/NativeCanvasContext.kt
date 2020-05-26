package pl.karol202.uranium.webcanvas.draw

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.webcanvas.NativeValue
import pl.karol202.uranium.webcanvas.assets.NativeImage
import pl.karol202.uranium.webcanvas.retrieveObject
import pl.karol202.uranium.webcanvas.values.NativeFillStyle
import pl.karol202.uranium.webcanvas.values.NativeGradient

@SymbolName("uranium_NativeCanvasContext_getCanvas")
private external fun NativeCanvasContext_getCanvas(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvasContext_getFillStyle")
private external fun NativeCanvasContext_getFillStyle(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvasContext_setFillStyle")
private external fun NativeCanvasContext_setFillStyle(arena: Arena, index: Object, styleArena: Arena, styleIndex: Object)

@SymbolName("uranium_NativeCanvasContext_setFont")
private external fun NativeCanvasContext_setFont(arena: Arena, index: Object, fontPtr: Pointer, fontLength: Int)

@SymbolName("uranium_NativeCanvasContext_fillRect")
private external fun NativeCanvasContext_fillRect(arena: Arena, index: Int,
                                                  x: Double, y: Double, width: Double, height: Double)

@SymbolName("uranium_NativeCanvasContext_clearRect")
private external fun NativeCanvasContext_clearRect(arena: Arena, index: Int,
                                                   x: Double, y: Double, width: Double, height: Double)

@SymbolName("uranium_NativeCanvasContext_drawImage")
private external fun NativeCanvasContext_drawImage(arena: Arena, index: Int, imageArena: Arena, imageIndex: Object,
                                                   drawX: Double, drawY: Double)

@SymbolName("uranium_NativeCanvasContext_drawImage")
private external fun NativeCanvasContext_drawImage(arena: Arena, index: Int, imageArena: Arena, imageIndex: Object,
                                                   drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double)

@SymbolName("uranium_NativeCanvasContext_drawImage")
private external fun NativeCanvasContext_drawImage(arena: Arena, index: Int, imageArena: Arena, imageIndex: Object,
                                                   srcX: Double, srcY: Double, srcWidth: Double, srcHeight: Double,
                                                   drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double)

@SymbolName("uranium_NativeCanvasContext_fillText")
private external fun NativeCanvasContext_fillText(arena: Arena, index: Int, textPtr: Pointer, textLength: Int,
                                                  x: Double, y: Double)

@SymbolName("uranium_NativeCanvasContext_fillText")
private external fun NativeCanvasContext_fillText(arena: Arena, index: Int, textPtr: Pointer, textLength: Int,
                                                  x: Double, y: Double, maxWidth: Double)

@SymbolName("uranium_NativeCanvasContext_beginPath")
private external fun NativeCanvasContext_beginPath(arena: Arena, index: Int)

@SymbolName("uranium_NativeCanvasContext_moveTo")
private external fun NativeCanvasContext_moveTo(arena: Arena, index: Int, x: Double, y: Double)

@SymbolName("uranium_NativeCanvasContext_lineTo")
private external fun NativeCanvasContext_lineTo(arena: Arena, index: Int, x: Double, y: Double)

@SymbolName("uranium_NativeCanvasContext_arc")
private external fun NativeCanvasContext_arc(arena: Arena, index: Int,
                                             x: Double, y: Double, radius: Double, startAngle: Double, endAngle: Double,
                                             anticlockwise: Boolean)

@SymbolName("uranium_NativeCanvasContext_closePath")
private external fun NativeCanvasContext_closePath(arena: Arena, index: Int)

@SymbolName("uranium_NativeCanvasContext_fill")
private external fun NativeCanvasContext_fill(arena: Arena, index: Int)

@SymbolName("uranium_NativeCanvasContext_fill")
private external fun NativeCanvasContext_fill(arena: Arena, index: Int, fillRulePtr: Pointer, fillRuleLength: Int)

@SymbolName("uranium_NativeCanvasContext_save")
private external fun NativeCanvasContext_save(arena: Arena, index: Int)

@SymbolName("uranium_NativeCanvasContext_restore")
private external fun NativeCanvasContext_restore(arena: Arena, index: Int)

@SymbolName("uranium_NativeCanvasContext_translate")
private external fun NativeCanvasContext_translate(arena: Arena, index: Int, x: Double, y: Double)

@SymbolName("uranium_NativeCanvasContext_scale")
private external fun NativeCanvasContext_scale(arena: Arena, index: Int, x: Double, y: Double)

@SymbolName("uranium_NativeCanvasContext_createLinearGradient")
private external fun NativeCanvasContext_createLinearGradient(arena: Arena, index: Int,
                                                              x0: Double, y0: Double,
                                                              x1: Double, y1: Double, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvasContext_createRadialGradient")
private external fun NativeCanvasContext_createRadialGradient(arena: Arena, index: Int,
                                                              x0: Double, y0: Double, r0: Double,
                                                              x1: Double, y1: Double, r1: Double, resultArena: Arena): Int

actual class NativeCanvasContext(override val arena: Arena,
                                 override val index: Object) : NativeValue
{
	actual val canvas get() = retrieveObject(::NativeCanvas) { NativeCanvasContext_getCanvas(arena, index, resultArena) }

	actual var fillStyle: NativeFillStyle
		get() = retrieveObject(::NativeFillStyle) { NativeCanvasContext_getFillStyle(arena, index, resultArena) }
		set(value) { NativeCanvasContext_setFillStyle(arena, index, value.arena, value.index) }
	actual var font: String
		get() = TODO()
		set(value) { NativeCanvasContext_setFont(arena, index, stringPointer(value), stringLengthBytes(value)) }

	actual fun fillRect(x: Double, y: Double, width: Double, height: Double) =
			NativeCanvasContext_fillRect(arena, index, x, y, width, height)

	actual fun clearRect(x: Double, y: Double, width: Double, height: Double) =
			NativeCanvasContext_clearRect(arena, index, x, y, width, height)

	actual fun drawImage(image: NativeImage, drawX: Double, drawY: Double) =
			NativeCanvasContext_drawImage(arena, index, image.arena, image.index, drawX, drawY)

	actual fun drawImage(image: NativeImage, drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double) =
			NativeCanvasContext_drawImage(arena, index, image.arena, image.index, drawX, drawY, drawWidth, drawHeight)

	actual fun drawImage(image: NativeImage, sourceX: Double, sourceY: Double, sourceWidth: Double, sourceHeight: Double,
	                     drawX: Double, drawY: Double, drawWidth: Double, drawHeight: Double) =
			NativeCanvasContext_drawImage(arena, index, image.arena, image.index,
			                              sourceX, sourceY, sourceWidth, sourceHeight, drawX, drawY, drawWidth, drawHeight)

	actual fun fillText(text: String, x: Double, y: Double) =
			NativeCanvasContext_fillText(arena, index, stringPointer(text), stringLengthBytes(text), x, y)

	actual fun fillText(text: String, x: Double, y: Double, maxWidth: Double) =
			NativeCanvasContext_fillText(arena, index, stringPointer(text), stringLengthBytes(text), x, y, maxWidth)

	actual fun beginPath() =
			NativeCanvasContext_beginPath(arena, index)

	actual fun moveTo(x: Double, y: Double) =
			NativeCanvasContext_moveTo(arena, index, x, y)

	actual fun lineTo(x: Double, y: Double) =
			NativeCanvasContext_lineTo(arena, index, x, y)

	actual fun arc(x: Double, y: Double, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean) =
			NativeCanvasContext_arc(arena, index, x, y, radius, startAngle, endAngle, anticlockwise)

	actual fun closePath() =
			NativeCanvasContext_closePath(arena, index)

	actual fun fill() =
			NativeCanvasContext_fill(arena, index)

	actual fun fill(fillRule: String) =
			NativeCanvasContext_fill(arena, index, stringPointer(fillRule), stringLengthBytes(fillRule))

	actual fun save() =
			NativeCanvasContext_save(arena, index)

	actual fun restore() =
			NativeCanvasContext_restore(arena, index)

	actual fun translate(x: Double, y: Double) =
			NativeCanvasContext_translate(arena, index, x, y)

	actual fun scale(x: Double, y: Double) =
			NativeCanvasContext_scale(arena, index, x, y)

	actual fun createLinearGradient(x0: Double, y0: Double, x1: Double, y1: Double) = retrieveObject(::NativeGradient) {
		NativeCanvasContext_createLinearGradient(arena, index, x0, y0, x1, y1, resultArena)
	}

	actual fun createRadialGradient(x0: Double, y0: Double, r0: Double,
	                                x1: Double, y1: Double, r1: Double) = retrieveObject(::NativeGradient) {
		NativeCanvasContext_createRadialGradient(arena, index, x0, y0, r0, x1, y1, r1, resultArena)
	}
}
