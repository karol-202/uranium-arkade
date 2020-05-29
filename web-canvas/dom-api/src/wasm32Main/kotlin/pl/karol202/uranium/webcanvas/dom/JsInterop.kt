package pl.karol202.uranium.webcanvas.dom

import kotlinx.cinterop.toKString
import kotlinx.wasm.jsinterop.*

internal interface NativeValue
{
	val arena: Arena
	val index: Object
}

/*internal val JsValue.asArray get() = JsArray(this)

internal fun JsArray.toList() = List(size) { this[it] }*/

internal data class ObjectRetrieveContext(val resultArena: Arena)
internal fun <R> retrieveObject(constructor: (Arena, Object) -> R, provider: ObjectRetrieveContext.() -> Int): R
{
	val resultArena = ArenaManager.currentArena
	val resultIndex = ObjectRetrieveContext(resultArena).provider()
	return constructor(resultArena, resultIndex)
}

@SymbolName("uranium_string_length")
private external fun string_length(arena: Arena, index: Object): Int

@SymbolName("uranium_string_data")
private external fun string_data(arena: Arena, index: Object, byteIndex: Int): Byte

internal fun retrieveString(provider: ObjectRetrieveContext.() -> Int): String
{
	val resultArena = ArenaManager.currentArena
	val resultIndex = ObjectRetrieveContext(resultArena).provider()
	val length = string_length(resultArena, resultIndex)
	return ByteArray(length) { string_data(resultArena, resultIndex, it) }.decodeToString()
}
