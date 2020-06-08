package pl.karol202.uranium.arkade.htmlcanvas.dom.input

import kotlinx.wasm.jsinterop.Arena
import kotlinx.wasm.jsinterop.Object
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue
import pl.karol202.uranium.arkade.htmlcanvas.dom.retrieveString

@SymbolName("uranium_NativeKeyboardEvent_getType")
private external fun NativeKeyboardEvent_getType(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeKeyboardEvent_getKey")
private external fun NativeKeyboardEvent_getKey(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeKeyboardEvent_getCode")
private external fun NativeKeyboardEvent_getCode(arena: Arena, index: Object, resultArena: Arena): Int

@SymbolName("uranium_NativeKeyboardEvent_getRepeat")
private external fun NativeKeyboardEvent_getRepeat(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeKeyboardEvent_getAltKey")
private external fun NativeKeyboardEvent_getAltKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeKeyboardEvent_getCtrlKey")
private external fun NativeKeyboardEvent_getCtrlKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeKeyboardEvent_getMetaKey")
private external fun NativeKeyboardEvent_getMetaKey(arena: Arena, index: Object): Boolean

@SymbolName("uranium_NativeKeyboardEvent_getShiftKey")
private external fun NativeKeyboardEvent_getShiftKey(arena: Arena, index: Object): Boolean

actual class NativeKeyboardEvent(override val arena: Arena,
                                 override val index: Object) : NativeValue
{
	actual val type get() = retrieveString { NativeKeyboardEvent_getType(arena, index, resultArena) }
	actual val key get() = retrieveString { NativeKeyboardEvent_getKey(arena, index, resultArena) }
	actual val code get() = retrieveString { NativeKeyboardEvent_getCode(arena, index, resultArena) }
	actual val repeat get() = NativeKeyboardEvent_getRepeat(arena, index)
	actual val altKey get() = NativeKeyboardEvent_getAltKey(arena, index)
	actual val ctrlKey get() = NativeKeyboardEvent_getCtrlKey(arena, index)
	actual val metaKey get() = NativeKeyboardEvent_getMetaKey(arena, index)
	actual val shiftKey get() = NativeKeyboardEvent_getShiftKey(arena, index)
}
