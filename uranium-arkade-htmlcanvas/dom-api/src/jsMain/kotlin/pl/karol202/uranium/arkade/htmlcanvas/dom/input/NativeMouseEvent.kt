package pl.karol202.uranium.arkade.htmlcanvas.dom.input

import org.w3c.dom.events.MouseEvent

actual class NativeMouseEvent(private val event: MouseEvent)
{
	actual val type get() = event.type
	actual val clientX get() = event.clientX
	actual val clientY get() = event.clientY
	actual val screenX get() = event.screenX
	actual val screenY get() = event.screenY
	actual val movementX get() = event.asDynamic().movementX
	actual val movementY get() = event.asDynamic().movementY
	actual val button get() = event.button
	actual val buttons get() = event.buttons
	actual val altKey get() = event.altKey
	actual val ctrlKey get() = event.ctrlKey
	actual val metaKey get() = event.metaKey
	actual val shiftKey get() = event.shiftKey
}
