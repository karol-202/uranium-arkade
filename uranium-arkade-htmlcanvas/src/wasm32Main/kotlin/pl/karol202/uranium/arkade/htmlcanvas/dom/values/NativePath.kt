package pl.karol202.uranium.arkade.htmlcanvas.dom.values

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue
import pl.karol202.uranium.arkade.htmlcanvas.dom.retrieveObject

@SymbolName("uranium_NativePath_Companion_create")
private external fun NativePath_Companion_create(resultArena: Arena): Int

@SymbolName("uranium_NativePath_Companion_fromData")
private external fun NativePath_Companion_fromData(dataPtr: Pointer, dataLength: Int, resultArena: Arena): Int

@SymbolName("uranium_NativePath_moveTo")
private external fun NativePath_moveTo(arena: Arena, index: Object, x: Double, y: Double)

@SymbolName("uranium_NativePath_lineTo")
private external fun NativePath_lineTo(arena: Arena, index: Object, x: Double, y: Double)

@SymbolName("uranium_NativePath_closePath")
private external fun NativePath_closePath(arena: Arena, index: Object)

internal actual class NativePath(override val arena: Arena,
                                 override val index: Object) : NativeValue
{
	actual companion object
	{
		actual fun create() = retrieveObject(::NativePath) { NativePath_Companion_create(resultArena) }

		actual fun fromData(data: String) = retrieveObject(::NativePath) {
			NativePath_Companion_fromData(stringPointer(data), stringLengthBytes(data), resultArena)
		}
	}

	actual fun moveTo(x: Double, y: Double) = NativePath_moveTo(arena, index, x, y)

	actual fun lineTo(x: Double, y: Double) = NativePath_lineTo(arena, index, x, y)

	actual fun closePath() = NativePath_closePath(arena, index)
}
