package pl.karol202.uranium.webcanvas.util

import kotlin.browser.window

internal actual fun now() = window.performance.now()

internal actual fun setInterval(interval: Int, handler: () -> Unit) = window.setInterval(handler, interval)

internal actual fun clearInterval(handle: Int) = window.clearInterval(handle)
