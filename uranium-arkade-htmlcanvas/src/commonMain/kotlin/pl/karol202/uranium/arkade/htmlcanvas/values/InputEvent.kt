package pl.karol202.uranium.arkade.htmlcanvas.values

import pl.karol202.uranium.arkade.htmlcanvas.dom.input.NativeKeyboardEvent
import pl.karol202.uranium.arkade.htmlcanvas.dom.input.NativeMouseEvent

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

		companion object
		{
			private const val TYPE_MOUSE_DOWN = "mousedown"
			private const val TYPE_MOUSE_MOVE = "mousemove"
			private const val TYPE_MOUSE_UP = "mouseup"
			private const val TYPE_MOUSE_ENTER = "mouseenter"
			private const val TYPE_MOUSE_LEAVE = "mouseleave"

			internal fun from(event: NativeMouseEvent) = event.run {
				Mouse(eventType, location, buttonTyped, buttonsTyped, altKey, ctrlKey, metaKey, shiftKey)
			}

			private val NativeMouseEvent.location get() = Vector(clientX.toDouble(), clientY.toDouble())

			private val NativeMouseEvent.eventType
				get() = when(type)
				{
					TYPE_MOUSE_DOWN -> Type.DOWN
					TYPE_MOUSE_MOVE -> Type.MOVE
					TYPE_MOUSE_UP -> Type.UP
					TYPE_MOUSE_ENTER -> Type.ENTER
					TYPE_MOUSE_LEAVE -> Type.LEAVE
					else -> throw IllegalArgumentException("Unknown event type: $type")
				}

			private val NativeMouseEvent.buttonTyped
				get() = when(button.toInt())
				{
					0 -> Button.PRIMARY
					1 -> Button.AUXILIARY
					2 -> Button.SECONDARY
					3 -> Button.BACKWARD
					4 -> Button.FORWARD
					else -> throw IllegalArgumentException("Unknown button: $button")
				}

			private val NativeMouseEvent.buttonsTyped
				get() = mapOf(Button.PRIMARY to buttons.getBit(0),
				              Button.SECONDARY to buttons.getBit(1),
				              Button.AUXILIARY to buttons.getBit(2),
				              Button.BACKWARD to buttons.getBit(3),
				              Button.FORWARD to buttons.getBit(4))

			private fun Short.getBit(index: Int) = (this.toInt() and (1 shl index)) != 0
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

		companion object
		{
			private const val TYPE_KEY_DOWN = "keydown"
			private const val TYPE_KEY_PRESS = "keypress"
			private const val TYPE_KEY_UP = "keyup"

			internal fun from(event: NativeKeyboardEvent) = event.run {
				Key(eventType, key, code, repeat, altKey, ctrlKey, metaKey, shiftKey)
			}

			private val NativeKeyboardEvent.eventType
				get() = when(type)
				{
					TYPE_KEY_DOWN -> Type.DOWN
					TYPE_KEY_PRESS -> Type.PRESS
					TYPE_KEY_UP -> Type.UP
					else -> throw IllegalArgumentException("Unknown event type: $type")
				}
		}
	}
}
