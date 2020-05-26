package pl.karol202.uranium.webcanvas.util

import kotlinx.wasm.jsinterop.KtFunction

@SymbolName("uranium_performanceNow")
private external fun nativePerformanceNow(): Double

@SymbolName("uranium_setInterval")
private external fun setNativeInterval(timeout: Int, handler: KtFunction<Unit>): Int

@SymbolName("uranium_clearInterval")
private external fun clearNativeInterval(handle: Int)

internal actual fun now() = nativePerformanceNow()

internal actual fun setInterval(interval: Int, handler: () -> Unit) = setNativeInterval(interval) { handler() }

internal actual fun clearInterval(handle: Int) = clearNativeInterval(handle)
