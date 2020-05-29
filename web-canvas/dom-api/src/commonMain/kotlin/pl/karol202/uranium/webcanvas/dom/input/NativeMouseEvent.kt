package pl.karol202.uranium.webcanvas.dom.input

expect class NativeMouseEvent
{
	val type: String
	val clientX: Int
	val clientY: Int
	val screenX: Int
	val screenY: Int
	val movementX: Int
	val movementY: Int
	val button: Short
	val buttons: Short
	val altKey: Boolean
	val ctrlKey: Boolean
	val metaKey: Boolean
	val shiftKey: Boolean
}
