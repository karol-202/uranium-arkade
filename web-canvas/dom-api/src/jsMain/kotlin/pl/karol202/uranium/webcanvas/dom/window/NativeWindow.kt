package pl.karol202.uranium.webcanvas.dom.window

import pl.karol202.uranium.webcanvas.dom.input.NativeKeyboardEvent
import kotlin.browser.window

actual object NativeWindow
{
	actual fun now() = window.performance.now()

	actual fun setInterval(interval: Int, handler: () -> Unit) = window.setInterval(handler, interval)

	actual fun clearInterval(handle: Int) = window.clearInterval(handle)

	actual fun setOnKeyDownListener(listener: ((NativeKeyboardEvent) -> Unit)?)
	{
		window.onkeydown = { listener?.invoke(NativeKeyboardEvent(it)) }
	}

	actual fun setOnKeyPressListener(listener: ((NativeKeyboardEvent) -> Unit)?)
	{
		window.onkeypress = { listener?.invoke(NativeKeyboardEvent(it)) }
	}

	actual fun setOnKeyUpListener(listener: ((NativeKeyboardEvent) -> Unit)?)
	{
		window.onkeyup = { listener?.invoke(NativeKeyboardEvent(it)) }
	}
}
