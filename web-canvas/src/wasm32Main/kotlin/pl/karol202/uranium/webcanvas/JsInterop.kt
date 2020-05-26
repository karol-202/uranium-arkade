package pl.karol202.uranium.webcanvas

import kotlinx.wasm.jsinterop.*

internal interface NativeValue
{
	val arena: Arena
	val index: Object
}

internal val JsValue.asArray get() = JsArray(this)

internal fun JsArray.toList() = List(size) { this[it] }

internal data class RetrieveObjectContext(val resultArena: Arena)
internal fun <R> retrieveObject(constructor: (Arena, Object) -> R, provider: RetrieveObjectContext.() -> Int): R
{
	val resultArena = ArenaManager.currentArena
	val resultIndex = RetrieveObjectContext(resultArena).provider()
	return constructor(resultArena, resultIndex)
}
