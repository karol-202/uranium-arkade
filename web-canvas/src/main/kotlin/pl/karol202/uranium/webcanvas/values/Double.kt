package pl.karol202.uranium.webcanvas.values

fun clamp(value: Double, min: Double, max: Double) = kotlin.math.min(kotlin.math.max(value, min), max)
