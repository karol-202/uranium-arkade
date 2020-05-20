package pl.karol202.uranium.core.util

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
actual class NativeList<out T> : Iterable<T>
{
	private val array: dynamic

	actual val size get() = array.length

	actual constructor()
	{
		measureTime {
			array = js("[]")
		}.also { console.log(1, it.inMilliseconds) }
	}

	actual constructor(source: Iterable<T>) : this()
	{
		measureTime {
			for(element in source) array.push(element)
		}.also { console.log(2, it.inMilliseconds) }
	}

	private constructor(array: Array<T>)
	{
		measureTime {
			this.array = array
		}.also { console.log(3, it.inMilliseconds) }
	}

	actual operator fun get(index: Int) = array[index]

	actual override operator fun iterator() = array.iterator()

	actual operator fun plus(element: @UnsafeVariance T) = NativeList(js("[]").concat(array, element) as Array<T>)

	actual operator fun plus(elements: NativeList<@UnsafeVariance T>) = NativeList(js("[]").concat(array, elements.array) as Array<T>)

	actual operator fun minus(element: @UnsafeVariance T) = (this.asIterable() - element).toNativeList()

	actual operator fun minus(elements: NativeList<@UnsafeVariance T>) = (this.asIterable() - elements).toNativeList()

	actual fun <R> map(transform: (T) -> R): NativeList<R>
	{
		val dest = js("[]")
		for(element in asIterable())
			dest.push(transform(element))
		return NativeList(dest as Array<R>)
	}

	actual fun <R> mapIndexed(transform: (Int, T) -> R): NativeList<R>
	{
		var index = 0
		val dest = js("[]")
		for(element in asIterable())
			dest.push(transform(index++, element))
		return NativeList(dest as Array<R>)
	}

	actual fun <R : Any> mapIndexedNotNull(transform: (Int, T) -> R?): NativeList<R>
	{
		var index = 0
		val dest = js("[]")
		for(element in asIterable())
		{
			val transformed = transform(index++, element) ?: continue
			dest.push(transformed)
		}
		return NativeList(dest as Array<R>)
	}

	actual fun <R> flatMap(transform: (T) -> Iterable<R>): NativeList<R>
	{
		val dest = js("[]")
		for(element in asIterable())
			for(newElement in transform(element))
				dest.push(newElement)
		return NativeList(dest as Array<R>)
	}

	actual fun filter(predicate: (T) -> Boolean): NativeList<T>
	{
		val dest = js("[]")
		for(element in asIterable())
			if(predicate(element)) dest.push(element)
		return NativeList(dest as Array<T>)
	}

	actual fun filterNot(predicate: (T) -> Boolean) = filter { !predicate(it) }

	actual fun take(n: Int): NativeList<T>
	{
		var currentCount = 0
		val dest = js("[]")
		for(element in asIterable())
		{
			dest.push(element)
			if(++currentCount == n) break
		}
		return NativeList(dest as Array<T>)
	}

	actual fun drop(n: Int): NativeList<T>
	{
		var currentCount = 0
		val dest = js("[]")
		for(element in asIterable())
			if (currentCount >= n) dest.push(element) else ++currentCount
		return NativeList(dest as Array<T>)
	}
}
