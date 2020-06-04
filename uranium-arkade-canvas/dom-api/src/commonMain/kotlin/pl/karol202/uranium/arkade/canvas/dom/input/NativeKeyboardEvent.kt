package pl.karol202.uranium.arkade.canvas.dom.input

expect class NativeKeyboardEvent
{
	val type: String
	val key: String
	val code: String
	val repeat: Boolean
	val altKey: Boolean
	val ctrlKey: Boolean
	val metaKey: Boolean
	val shiftKey: Boolean
}
