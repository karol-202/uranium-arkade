package pl.karol202.uranium.webcanvas.assets

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.webcanvas.NativeValue
import pl.karol202.uranium.webcanvas.retrieveObject

@SymbolName("uranium_NativeImage_Companion_load")
private external fun NativeImage_Companion_load(srcPtr: Pointer, srcLength: Int, resultArena: Arena): Int

actual class NativeImage(override val arena: Arena,
                         override val index: Object) : NativeValue
{
	actual companion object
	{
		actual fun load(src: String) = retrieveObject(::NativeImage) {
			NativeImage_Companion_load(stringPointer(src), stringLengthBytes(src), resultArena)
		}
	}
}


