package pl.karol202.uranium.webcanvas.values

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.webcanvas.NativeValue
import pl.karol202.uranium.webcanvas.retrieveObject

@SymbolName("uranium_NativeFillStyle_Companion_fromString")
private external fun NativeFillStyle_Companion_fromString(strPtr: Pointer, strLength: Int, resultArena: Arena): Int

actual class NativeFillStyle(override val arena: Arena,
                             override val index: Object) : NativeValue
{
	actual companion object
	{
		internal actual fun fromString(string: String) = retrieveObject(::NativeFillStyle) {
			NativeFillStyle_Companion_fromString(stringPointer(string), stringLengthBytes(string), resultArena)
		}
	}
}
