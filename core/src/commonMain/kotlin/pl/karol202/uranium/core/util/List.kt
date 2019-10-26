package pl.karol202.uranium.core.util

fun <T> List<T>.elementInserted(element: T, index: Int) = take(index) + element + drop(index)

fun <T> List<T>.elementRemoved(element: T) = filterNot { it == element }
