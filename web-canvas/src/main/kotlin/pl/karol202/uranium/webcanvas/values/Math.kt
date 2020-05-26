package pl.karol202.uranium.webcanvas.values

internal fun ClosedRange<Double>.lerp(value: Double) = (value * (endInclusive - start)) + start
