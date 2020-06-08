package pl.karol202.uranium.arkade.htmlcanvas.dom.input

import kotlinx.wasm.jsinterop.Arena
import kotlinx.wasm.jsinterop.Object
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue
import pl.karol202.uranium.arkade.htmlcanvas.dom.retrieveString

@SymbolName("uranium_NativeMouseEvent_getType")
private external fun NativeMouseEvent_getType(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeMouseEvent_getClientX")
private external fun NativeMouseEvent_getClientX(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getClientY")
private external fun NativeMouseEvent_getClientY(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getScreenX")
private external fun NativeMouseEvent_getScreenX(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getScreenY")
private external fun NativeMouseEvent_getScreenY(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getMovementX")
private external fun NativeMouseEvent_getMovementX(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getMovementY")
private external fun NativeMouseEvent_getMovementY(arena: Arena, index: Object): Int

@SymbolName("uranium_NativeMouseEvent_getButton")
private external fun NativeMouseEvent_getButton(arena: Arena, index: Object): Short

@SymbolName("uranium_NativeMouseEvent_getButtons")
private external fun NativeMouseEvent_getButtons(arena: Arena, index: Object): Short

@SymbolName("uranium_NativeMouseEvent_getAltKey")
private external fun NativeMouseEvent_getAltKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeMouseEvent_getCtrlKey")
private external fun NativeMouseEvent_getCtrlKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeMouseEvent_getMetaKey")
private external fun NativeMouseEvent_getMetaKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeMouseEvent_getShiftKey")
private external fun NativeMouseEvent_getShiftKey(arena: Arena, index: Object): Boolean

actual class NativeMouseEvent(override val arena: Arena,
                              override val index: Object) : NativeValue
{
	actual val type get() = retrieveString { NativeMouseEvent_getType(arena, index, resultArena) }
	actual val clientX get() = NativeMouseEvent_getClientX(arena, index)
	actual val clientY get() = NativeMouseEvent_getClientY(arena, index)
	actual val screenX get() = NativeMouseEvent_getScreenX(arena, index)
	actual val screenY get() = NativeMouseEvent_getScreenY(arena, index)
	actual val movementX get() = NativeMouseEvent_getMovementX(arena, index)
	actual val movementY get() = NativeMouseEvent_getMovementY(arena, index)
	actual val button get() = NativeMouseEvent_getButton(arena, index)
	actual val buttons get() = NativeMouseEvent_getButtons(arena, index)
	actual val altKey get() = NativeMouseEvent_getAltKey(arena, index)
	actual val ctrlKey get() = NativeMouseEvent_getCtrlKey(arena, index)
	actual val metaKey get() = NativeMouseEvent_getMetaKey(arena, index)
	actual val shiftKey get() = NativeMouseEvent_getShiftKey(arena, index)
}
