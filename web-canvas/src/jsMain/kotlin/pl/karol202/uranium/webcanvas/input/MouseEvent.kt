package pl.karol202.uranium.webcanvas.input

import org.w3c.dom.events.MouseEvent
import pl.karol202.uranium.webcanvas.values.Vector

private const val TYPE_MOUSE_DOWN = "mousedown"
private const val TYPE_MOUSE_MOVE = "mousemove"
private const val TYPE_MOUSE_UP = "mouseup"
private const val TYPE_MOUSE_ENTER = "mouseenter"
private const val TYPE_MOUSE_LEAVE = "mouseleave"

fun MouseEvent.toInputEvent() =
		InputEvent.Mouse(eventType, location, buttonTyped, buttonsTyped, altKey, ctrlKey, metaKey, shiftKey)

private val MouseEvent.location get() = Vector(x, y)

private val MouseEvent.eventType get() = when(type)
{
	TYPE_MOUSE_DOWN -> InputEvent.Mouse.Type.DOWN
	TYPE_MOUSE_MOVE -> InputEvent.Mouse.Type.MOVE
	TYPE_MOUSE_UP -> InputEvent.Mouse.Type.UP
	TYPE_MOUSE_ENTER -> InputEvent.Mouse.Type.ENTER
	TYPE_MOUSE_LEAVE -> InputEvent.Mouse.Type.LEAVE
	else -> throw IllegalArgumentException("Unknown event type: $type")
}

private val MouseEvent.buttonTyped get() = when(button.toInt())
{
	0 -> InputEvent.Mouse.Button.PRIMARY
	1 -> InputEvent.Mouse.Button.AUXILIARY
	2 -> InputEvent.Mouse.Button.SECONDARY
	3 -> InputEvent.Mouse.Button.BACKWARD
	4 -> InputEvent.Mouse.Button.FORWARD
	else -> throw IllegalArgumentException("Unknown button: $button")
}

private val MouseEvent.buttonsTyped get() = mapOf(InputEvent.Mouse.Button.PRIMARY to buttons.getBit(0),
                                                  InputEvent.Mouse.Button.SECONDARY to buttons.getBit(1),
                                                  InputEvent.Mouse.Button.AUXILIARY to buttons.getBit(2),
                                                  InputEvent.Mouse.Button.BACKWARD to buttons.getBit(3),
                                                  InputEvent.Mouse.Button.FORWARD  to buttons.getBit(4))

private fun Short.getBit(index: Int) = (this.toInt() and (1 shl index)) != 0
