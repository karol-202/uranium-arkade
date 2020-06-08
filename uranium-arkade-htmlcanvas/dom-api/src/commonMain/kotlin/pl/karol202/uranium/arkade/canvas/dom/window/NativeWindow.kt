package pl.karol202.uranium.arkade.canvas.dom.window

import pl.karol202.uranium.arkade.canvas.dom.input.NativeKeyboardEvent

expect object NativeWindow
{
	fun now(): Double

	fun setInterval(interval: Int, handler: () -> Unit): Int

	fun clearInterval(handle: Int)

	fun setOnKeyDownListener(listener: ((NativeKeyboardEvent) -> Unit)?)

	fun setOnKeyPressListener(listener: ((NativeKeyboardEvent) -> Unit)?)

	fun setOnKeyUpListener(listener: ((NativeKeyboardEvent) -> Unit)?)
}
