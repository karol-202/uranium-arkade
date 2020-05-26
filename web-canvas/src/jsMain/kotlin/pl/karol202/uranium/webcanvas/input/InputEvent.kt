package pl.karol202.uranium.webcanvas.input

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import kotlin.browser.document
import kotlin.browser.window

internal actual fun setMouseEventListener(elementId: String, listener: ((InputEvent.Mouse) -> Unit)?)
{
	fun onEvent(mouseEvent: MouseEvent) = listener?.invoke(mouseEvent.toInputEvent())

	val element = document.getElementById(elementId) as HTMLElement
	element.onmousedown = ::onEvent
	element.onmousemove = ::onEvent
	element.onmouseup = ::onEvent
	element.onmouseenter = ::onEvent
	element.onmouseleave = ::onEvent
}

internal actual fun setKeyboardEventListener(listener: ((InputEvent.Key) -> Unit)?)
{
	fun onEvent(keyboardEvent: KeyboardEvent) = listener?.invoke(keyboardEvent.toInputEvent())

	window.onkeydown = ::onEvent
	window.onkeypress = ::onEvent
	window.onkeyup = ::onEvent
}
