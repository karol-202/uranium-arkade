package pl.karol202.uranium.arkade.htmlcanvas.values

internal fun ClosedRange<Double>.lerp(value: Double) = (value * (endInclusive - start)) + start
