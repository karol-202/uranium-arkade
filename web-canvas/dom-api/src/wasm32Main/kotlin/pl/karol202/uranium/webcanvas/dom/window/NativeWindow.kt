package pl.karol202.uranium.webcanvas.dom.window

import kotlinx.wasm.jsinterop.JsValue
import kotlinx.wasm.jsinterop.KtFunction
import pl.karol202.uranium.webcanvas.dom.input.NativeKeyboardEvent

@SymbolName("uranium_NativeWindow_now")
private external fun NativeWindow_now(): Double

@SymbolName("uranium_NativeWindow_setInterval")
private external fun NativeWindow_setInterval(timeout: Int, handler: KtFunction<Unit>): Int

@SymbolName("uranium_NativeWindow_clearInterval")
private external fun NativeWindow_clearInterval(handle: Int)

@SymbolName("uranium_NativeWindow_setOnKeyDownListener")
private external fun NativeWindow_setOnKeyDownListener(handler: KtFunction<Unit>)

@SymbolName("uranium_NativeWindow_setOnKeyPressListener")
private external fun NativeWindow_setOnKeyPressListener(handler: KtFunction<Unit>)

@SymbolName("uranium_NativeWindow_setOnKeyUpListener")
private external fun NativeWindow_setOnKeyUpListener(handler: KtFunction<Unit>)

actual object NativeWindow
{
	actual fun now() = NativeWindow_now()

	actual fun setInterval(interval: Int, handler: () -> Unit) = NativeWindow_setInterval(interval) { handler() }

	actual fun clearInterval(handle: Int) = NativeWindow_clearInterval(handle)

	actual fun setOnKeyDownListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyDownListener(keyHandler(listener))

	actual fun setOnKeyPressListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyPressListener(keyHandler(listener))

	actual fun setOnKeyUpListener(listener: ((NativeKeyboardEvent) -> Unit)?) =
			NativeWindow_setOnKeyUpListener(keyHandler(listener))

	private fun keyHandler(listener: ((NativeKeyboardEvent) -> Unit)?): KtFunction<Unit> = { (eventJsValue) ->
		listener?.invoke(NativeKeyboardEvent(eventJsValue.arena, eventJsValue.index))
	}
}
