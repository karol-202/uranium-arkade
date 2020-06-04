package pl.karol202.uranium.arkade.canvas.dom

import kotlinx.wasm.jsinterop.Arena
import kotlinx.wasm.jsinterop.ArenaManager
import kotlinx.wasm.jsinterop.Object

@SymbolName("uranium_string_length")
private external fun string_length(arena: Arena, index: Object): Int

@SymbolName("uranium_string_data")
private external fun string_data(arena: Arena, index: Object, charIndex: Int): Char

internal interface NativeValue
{
	val arena: Arena
	val index: Object
}

internal data class ObjectRetrieveContext(val resultArena: Arena)

internal fun <R> retrieveObject(constructor: (Arena, Object) -> R, provider: ObjectRetrieveContext.() -> Int): R
{
	val resultArena = ArenaManager.globalArena
	val resultIndex = ObjectRetrieveContext(resultArena).provider()
	return constructor(resultArena, resultIndex)
}

internal fun retrieveString(provider: ObjectRetrieveContext.() -> Int): String
{
	val resultArena = ArenaManager.globalArena
	val resultIndex = ObjectRetrieveContext(resultArena).provider()
	val length = string_length(resultArena, resultIndex)
	return CharArray(length) { string_data(resultArena, resultIndex, it) }.concatToString()
}
