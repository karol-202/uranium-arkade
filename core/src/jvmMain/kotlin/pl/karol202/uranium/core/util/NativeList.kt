package pl.karol202.uranium.core.util

actual class NativeList<out T> actual constructor() : Iterable<T>
{
	private val list = mutableListOf<T>()

	actual val size = list.size

	actual constructor(source: Iterable<T>) : this()
	{
		list.addAll(source)
	}

	actual operator fun get(index: Int) = list[index]

	actual override operator fun iterator() = list.iterator() as Iterator<T>

	actual operator fun plus(element: @UnsafeVariance T) = (list + element).toNativeList()

	actual operator fun plus(elements: NativeList<@UnsafeVariance T>) = (list + elements).toNativeList()

	actual operator fun minus(element: @UnsafeVariance T) = (list - element).toNativeList()

	actual operator fun minus(elements: NativeList<@UnsafeVariance T>) = (list - elements).toNativeList()

	actual fun <R> map(transform: (T) -> R) = list.map(transform).toNativeList()

	actual fun <R> mapIndexed(transform: (Int, T) -> R) = list.mapIndexed(transform).toNativeList()

	actual fun <R : Any> mapIndexedNotNull(transform: (Int, T) -> R?) = list.mapIndexedNotNull(transform).toNativeList()

	actual fun <R> flatMap(transform: (T) -> Iterable<R>) = list.flatMap(transform).toNativeList()

	actual fun filter(predicate: (T) -> Boolean) = list.filter(predicate).toNativeList()

	actual fun filterNot(predicate: (T) -> Boolean) = list.filterNot(predicate).toNativeList()

	actual fun take(n: Int) = list.take(n).toNativeList()

	actual fun drop(n: Int) = list.drop(n).toNativeList()
}
