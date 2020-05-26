package pl.karol202.uranium.webcanvas.input

import pl.karol202.uranium.webcanvas.values.Vector

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
}

internal expect fun setMouseEventListener(elementId: String, listener: ((InputEvent.Mouse) -> Unit)?)

internal expect fun setKeyboardEventListener(listener: ((InputEvent.Key) -> Unit)?)
