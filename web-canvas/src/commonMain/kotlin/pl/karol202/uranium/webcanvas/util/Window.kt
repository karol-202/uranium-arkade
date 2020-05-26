package pl.karol202.uranium.webcanvas.util

internal expect fun now(): Double

internal expect fun setInterval(interval: Int, handler: () -> Unit): Int

internal expect fun clearInterval(handle: Int)
