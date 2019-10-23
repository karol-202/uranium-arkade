package pl.karol202.uranium.core.util

fun <T> List<T>.addAtIndex(element: T, index: Int) = take(index) + element + drop(index)
