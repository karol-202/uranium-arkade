package pl.karol202.uranium.webcanvas.values

import org.w3c.dom.events.MouseEvent

sealed class InputEvent
{
	abstract class Mouse : InputEvent()
	{
		abstract val location: Vector

		data class Down(override val location: Vector) : Mouse()
		{
			override fun withLocation(location: Vector) = copy(location = location)
		}

		data class Move(override val location: Vector) : Mouse()
		{
			override fun withLocation(location: Vector) = copy(location = location)
		}

		data class Up(override val location: Vector) : Mouse()
		{
			override fun withLocation(location: Vector) = copy(location = location)
		}

		abstract fun withLocation(location: Vector): Mouse

		fun withLocation(builder: (Vector) -> Vector) = withLocation(builder(location))
	}

	companion object
	{
		private const val TYPE_MOUSE_DOWN = "mousedown"
		private const val TYPE_MOUSE_MOVE = "mousemove"
		private const val TYPE_MOUSE_UP = "mouseup"

		fun from(mouseEvent: MouseEvent) = when(mouseEvent.type)
		{
			TYPE_MOUSE_DOWN -> Mouse.Down(mouseEvent.location)
			TYPE_MOUSE_MOVE -> Mouse.Move(mouseEvent.location)
			TYPE_MOUSE_UP -> Mouse.Up(mouseEvent.location)
			else -> throw IllegalArgumentException("Unknown event type: ${mouseEvent.type}")
		}

		private val MouseEvent.location get() = Vector(x, y)
	}
}
