package pl.karol202.uranium.arkade.canvas.dom.input

import org.w3c.dom.events.KeyboardEvent

actual class NativeKeyboardEvent(private val event: KeyboardEvent)
{
	actual val type get() = event.type
	actual val key get() = event.key
	actual val code get() = event.code
	actual val repeat get() = event.repeat
	actual val altKey get() = event.altKey
	actual val ctrlKey get() = event.ctrlKey
	actual val metaKey get() = event.metaKey
	actual val shiftKey get() = event.shiftKey
}
