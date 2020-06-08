package pl.karol202.uranium.arkade.htmlcanvas.dom.canvas

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue
import pl.karol202.uranium.arkade.htmlcanvas.dom.input.NativeMouseEvent
import pl.karol202.uranium.arkade.htmlcanvas.dom.retrieveObject

@SymbolName("uranium_getNativeCanvas")
private external fun getNativeCanvas(canvasIdPtr: Pointer, canvasIdLength: Int, resultArena: Arena): Int

@SymbolName("uranium_NativeCanvas_getContext")
private external fun NativeCanvas_getContext(canvasArena: Arena, canvasIndex: Int, resultArena: Arena): Int

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

@SymbolName("uranium_NativeCanvas_setOnMouseDownListener")
private external fun NativeCanvas_setOnMouseDownListener(canvasArena: Arena, canvasIndex: Object,
                                                         handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeCanvas_setOnMouseMoveListener")
private external fun NativeCanvas_setOnMouseMoveListener(canvasArena: Arena, canvasIndex: Object,
                                                         handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeCanvas_setOnMouseUpListener")
private external fun NativeCanvas_setOnMouseUpListener(canvasArena: Arena, canvasIndex: Object,
                                                       handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeCanvas_setOnMouseEnterListener")
private external fun NativeCanvas_setOnMouseEnterListener(canvasArena: Arena, canvasIndex: Object,
                                                          handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeCanvas_setOnMouseLeaveListener")
private external fun NativeCanvas_setOnMouseLeaveListener(canvasArena: Arena, canvasIndex: Object,
                                                          handlerIndex: Int, handlerArena: Arena)

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

	actual fun setOnMouseDownListener(listener: ((NativeMouseEvent) -> Unit)?) =
			NativeCanvas_setOnMouseDownListener(arena, index, wrapFunction(mouseHandler(listener)), ArenaManager.globalArena)

	actual fun setOnMouseMoveListener(listener: ((NativeMouseEvent) -> Unit)?) =
			NativeCanvas_setOnMouseMoveListener(arena, index, wrapFunction(mouseHandler(listener)), ArenaManager.globalArena)

	actual fun setOnMouseUpListener(listener: ((NativeMouseEvent) -> Unit)?) =
			NativeCanvas_setOnMouseUpListener(arena, index, wrapFunction(mouseHandler(listener)), ArenaManager.globalArena)

	actual fun setOnMouseEnterListener(listener: ((NativeMouseEvent) -> Unit)?) =
			NativeCanvas_setOnMouseEnterListener(arena, index, wrapFunction(mouseHandler(listener)), ArenaManager.globalArena)

	actual fun setOnMouseLeaveListener(listener: ((NativeMouseEvent) -> Unit)?) =
			NativeCanvas_setOnMouseLeaveListener(arena, index, wrapFunction(mouseHandler(listener)), ArenaManager.globalArena)

	private fun mouseHandler(listener: ((NativeMouseEvent) -> Unit)?): KtFunction<Unit> = { (eventJsValue) ->
		listener?.invoke(NativeMouseEvent(eventJsValue.arena, eventJsValue.index))
	}
}

actual fun getNativeCanvas(canvasId: String) = retrieveObject(::NativeCanvas) {
	getNativeCanvas(stringPointer(canvasId), stringLengthBytes(canvasId), resultArena)
}
