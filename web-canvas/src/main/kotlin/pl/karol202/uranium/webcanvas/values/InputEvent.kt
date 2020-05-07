package pl.karol202.uranium.webcanvas.values

import org.w3c.dom.events.MouseEvent

sealed class InputEvent
{
	data class Mouse(val location: Vector,
	                 val type: Type) : InputEvent()
	{
		enum class Type
		{
			DOWN, MOVE, UP, ENTER, LEAVE
		}

		fun withLocation(location: Vector) = copy(location = location)
	}

	companion object
	{
		private const val TYPE_MOUSE_DOWN = "mousedown"
		private const val TYPE_MOUSE_MOVE = "mousemove"
		private const val TYPE_MOUSE_UP = "mouseup"
		private const val TYPE_MOUSE_ENTER = "mouseenter"
		private const val TYPE_MOUSE_LEAVE = "mouseleave"

		fun from(mouseEvent: MouseEvent) = Mouse(mouseEvent.location, mouseEvent.eventType)

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
	}
}
