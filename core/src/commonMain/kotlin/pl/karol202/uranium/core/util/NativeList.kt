package pl.karol202.uranium.core.util

expect class NativeList<out T>() : Iterable<T>
{
	val size: Int

	constructor(source: Iterable<T>)

	operator fun get(index: Int): T

	override operator fun iterator(): Iterator<T>

	operator fun plus(element: @UnsafeVariance T): NativeList<T>

	operator fun plus(elements: NativeList<@UnsafeVariance T>): NativeList<T>

	operator fun minus(element: @UnsafeVariance T): NativeList<T>

	operator fun minus(elements: NativeList<@UnsafeVariance T>): NativeList<T>

	fun <R> map(transform: (T) -> R): NativeList<R>

	fun <R> mapIndexed(transform: (Int, T) -> R): NativeList<R>

	fun <R : Any> mapIndexedNotNull(transform: (Int, T) -> R?): NativeList<R>

	fun <R> flatMap(transform: (T) -> Iterable<R>): NativeList<R>

	fun filter(predicate: (T) -> Boolean): NativeList<T>

	fun filterNot(predicate: (T) -> Boolean): NativeList<T>

	fun take(n: Int): NativeList<T>

	fun drop(n: Int): NativeList<T>
}

fun <T> emptyNativeList() = NativeList<T>()

fun <T> nativeListOf(vararg elements: T) = elements.asIterable().toNativeList()

fun <T> Iterable<T>.toNativeList() = NativeList(this)

fun <T> NativeList<T>.inserted(element: T, index: Int) = take(index) + element + drop(index)

fun <T> NativeList<T>.removed(element: T) = filterNot { it == element }

fun <T> NativeList<T>.replaced(oldElement: T, newElement: T) = map { if(it == oldElement) newElement else it }
