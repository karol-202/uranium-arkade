package pl.karol202.uranium.webcanvas.draw

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.webcanvas.NativeValue
import pl.karol202.uranium.webcanvas.retrieveObject

@SymbolName("uranium_getNativeCanvas")
private external fun getNativeCanvas(canvasIdPtr: Pointer, canvasIdLength: Int, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvas_getContext")
private external fun NativeCanvas_getContext(canvasArena: Arena, canvasObjectIndex: Int, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvas_getWidth")
private external fun NativeCanvas_getWidth(canvasArena: Arena, canvasIndex: Object): Int

@SymbolName("uranium_NativeCanvas_setWidth")
private external fun NativeCanvas_setWidth(canvasArena: Arena, canvasIndex: Object, width: Int)

@SymbolName("uranium_NativeCanvas_getHeight")
private external fun NativeCanvas_getHeight(canvasArena: Arena, canvasIndex: Object): Int

@SymbolName("uranium_NativeCanvas_setHeight")
private external fun NativeCanvas_setHeight(canvasArena: Arena, canvasIndex: Object, height: Int)

@SymbolName("uranium_NativeCanvas_getClientWidth")
private external fun NativeCanvas_getClientWidth(canvasArena: Arena, canvasIndex: Object): Int

@SymbolName("uranium_NativeCanvas_getClientHeight")
private external fun NativeCanvas_getClientHeight(canvasArena: Arena, canvasIndex: Object): Int

actual class NativeCanvas(override val arena: Arena,
                          override val index: Object) : NativeValue
{
	actual val context: NativeCanvasContext
		get() = retrieveObject(::NativeCanvasContext) {
			NativeCanvas_getContext(arena, index, resultArena)
		}

	actual var width
		get() = NativeCanvas_getWidth(arena, index)
		set(value) { NativeCanvas_setWidth(arena, index, value) }
	actual var height
		get() = NativeCanvas_getHeight(arena, index)
		set(value) { NativeCanvas_setHeight(arena, index, value) }
	actual val clientWidth get() = NativeCanvas_getClientWidth(arena, index)
	actual val clientHeight get() = NativeCanvas_getClientHeight(arena, index)
}

internal actual fun getNativeCanvas(canvasId: String) = retrieveObject(::NativeCanvas) {
	getNativeCanvas(stringPointer(canvasId), stringLengthBytes(canvasId), resultArena)
}
