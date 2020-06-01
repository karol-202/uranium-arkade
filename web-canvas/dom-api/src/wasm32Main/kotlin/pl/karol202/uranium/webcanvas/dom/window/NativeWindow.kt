package pl.karol202.uranium.webcanvas.dom.window

import kotlinx.wasm.jsinterop.Arena
import kotlinx.wasm.jsinterop.ArenaManager
import kotlinx.wasm.jsinterop.KtFunction
import kotlinx.wasm.jsinterop.wrapFunction
import pl.karol202.uranium.webcanvas.dom.input.NativeKeyboardEvent

@SymbolName("uranium_NativeWindow_now")
private external fun NativeWindow_now(): Double

@SymbolName("uranium_NativeWindow_setInterval")
private external fun NativeWindow_setInterval(timeout: Int, handlerIndex: Int, handlerArena: Arena): Int

@SymbolName("uranium_NativeWindow_clearInterval")
private external fun NativeWindow_clearInterval(handle: Int)

@SymbolName("uranium_NativeWindow_setOnKeyDownListener")
private external fun NativeWindow_setOnKeyDownListener(handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeWindow_setOnKeyPressListener")
private external fun NativeWindow_setOnKeyPressListener(handlerIndex: Int, handlerArena: Arena)

@SymbolName("uranium_NativeWindow_setOnKeyUpListener")
private external fun NativeWindow_setOnKeyUpListener(handlerIndex: Int, handlerArena: Arena)

actual object NativeWindow
{
	actual fun now() = NativeWindow_now()

	actual fun setInterval(interval: Int, handler: () -> Unit) =
			NativeWindow_setInterval(interval, wrapFunction { handler() }, ArenaManager.globalArena)

	actual fun clearInterval(handle: Int) = NativeWindow_clearInterval(handle)

	actual fun setOnKeyDownListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyDownListener(wrapFunction(keyHandler(listener)), ArenaManager.globalArena)

	actual fun setOnKeyPressListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyPressListener(wrapFunction(keyHandler(listener)), ArenaManager.globalArena)

	actual fun setOnKeyUpListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyUpListener(wrapFunction(keyHandler(listener)), ArenaManager.globalArena)

	private fun keyHandler(listener: ((NativeKeyboardEvent) -> Unit)?): KtFunction<Unit> = { (eventJsValue) ->
		listener?.invoke(NativeKeyboardEvent(eventJsValue.arena, eventJsValue.index))
	}
}
