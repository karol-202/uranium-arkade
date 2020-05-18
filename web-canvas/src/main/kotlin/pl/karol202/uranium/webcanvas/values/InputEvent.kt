package pl.karol202.uranium.webcanvas.values

import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent

sealed class InputEvent
{
	data class Mouse(val type: Type,
	                 val location: Vector,
	                 val button: Button,
	                 val buttons: Map<Button, Boolean>,
	                 val altKey: Boolean,
	                 val ctrlKey: Boolean,
	                 val metaKey: Boolean,
	                 val shiftKey: Boolean) : InputEvent()
	{
		enum class Type
		{
			DOWN, MOVE, UP, ENTER, LEAVE
		}

		enum class Button
		{
			PRIMARY, AUXILIARY, SECONDARY, BACKWARD, FORWARD
		}

		fun withLocation(location: Vector) = copy(location = location)
	}

	data class Key(val type: Type,
	               val key: String,
	               val code: String,
	               val repeating: Boolean,
	               val altKey: Boolean,
	               val ctrlKey: Boolean,
	               val metaKey: Boolean,
	               val shiftKey: Boolean) : InputEvent()
	{
		enum class Type
		{
			DOWN, PRESS, UP
		}
	}

	companion object
	{
		private const val TYPE_MOUSE_DOWN = "mousedown"
		private const val TYPE_MOUSE_MOVE = "mousemove"
		private const val TYPE_MOUSE_UP = "mouseup"
		private const val TYPE_MOUSE_ENTER = "mouseenter"
		private const val TYPE_MOUSE_LEAVE = "mouseleave"

		private const val TYPE_KEY_DOWN = "keydown"
		private const val TYPE_KEY_PRESS = "keypress"
		private const val TYPE_KEY_UP = "keyup"

		fun from(event: MouseEvent) = Mouse(event.eventType, event.location, event.buttonTyped, event.buttonsTyped,
		                                    event.altKey, event.ctrlKey, event.metaKey, event.shiftKey)

		private val MouseEvent.location get() = Vector(x, y)

		private val MouseEvent.eventType get() = when(type)
		{
			TYPE_MOUSE_DOWN -> Mouse.Type.DOWN
			TYPE_MOUSE_MOVE -> Mouse.Type.MOVE
			TYPE_MOUSE_UP -> Mouse.Type.UP
			TYPE_MOUSE_ENTER -> Mouse.Type.ENTER
			TYPE_MOUSE_LEAVE -> Mouse.Type.LEAVE
			else -> throw IllegalArgumentException("Unknown event type: $type")
		}

		private val MouseEvent.buttonTyped get() = when(button.toInt())
		{
			0 -> Mouse.Button.PRIMARY
			1 -> Mouse.Button.AUXILIARY
			2 -> Mouse.Button.SECONDARY
			3 -> Mouse.Button.BACKWARD
			4 -> Mouse.Button.FORWARD
			else -> throw IllegalArgumentException("Unknown button: $button")
		}

		private val MouseEvent.buttonsTyped get() = mapOf(Mouse.Button.PRIMARY to buttons.getBit(0),
		                                                  Mouse.Button.SECONDARY to buttons.getBit(1),
		                                                  Mouse.Button.AUXILIARY to buttons.getBit(2),
		                                                  Mouse.Button.BACKWARD to buttons.getBit(3),
		                                                  Mouse.Button.FORWARD  to buttons.getBit(4))

		private fun Short.getBit(index: Int) = (this.toInt() and (1 shl index)) != 0

		fun from(event: KeyboardEvent) = Key(event.eventType, event.key, event.code, event.repeat,
		                                     event.altKey, event.ctrlKey, event.metaKey, event.shiftKey)

		private val KeyboardEvent.eventType get() = when(type)
		{
			TYPE_KEY_DOWN -> Key.Type.DOWN
			TYPE_KEY_PRESS -> Key.Type.PRESS
			TYPE_KEY_UP -> Key.Type.UP
			else -> throw IllegalArgumentException("Unknown event type: $type")
		}
	}
}
