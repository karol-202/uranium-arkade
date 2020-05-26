package pl.karol202.uranium.webcanvas.input

import org.w3c.dom.events.KeyboardEvent

private const val TYPE_KEY_DOWN = "keydown"
private const val TYPE_KEY_PRESS = "keypress"
private const val TYPE_KEY_UP = "keyup"

fun KeyboardEvent.toInputEvent() = InputEvent.Key(eventType, key, code, repeat, altKey, ctrlKey, metaKey, shiftKey)

private val KeyboardEvent.eventType get() = when(type)
{
	TYPE_KEY_DOWN -> InputEvent.Key.Type.DOWN
	TYPE_KEY_PRESS -> InputEvent.Key.Type.PRESS
	TYPE_KEY_UP -> InputEvent.Key.Type.UP
	else -> throw IllegalArgumentException("Unknown event type: $type")
}
