package pl.karol202.uranium.webcanvas.input

import kotlinx.wasm.jsinterop.KtFunction
import kotlinx.wasm.jsinterop.Pointer
import kotlinx.wasm.jsinterop.stringLengthBytes
import kotlinx.wasm.jsinterop.stringPointer
import pl.karol202.uranium.webcanvas.asArray
import pl.karol202.uranium.webcanvas.toList

/*private object KeyboardData
{
	const val KEY_SIZE = 16
	const val CODE_SIZE = 16

	val keyPtr = nativeHeap.alloc(KEY_SIZE, 1)
	val codePtr = nativeHeap.alloc(CODE_SIZE, 1)
}*/

@SymbolName("uranium_setElementMouseEventListener")
private external fun setElementMouseEventListener(elementIdPtr: Pointer, elementIdLength: Int, listener: KtFunction<Unit>)

@SymbolName("uranium_setWindowKeyboardEventListener")
private external fun setWindowKeyboardEventListener(listener: KtFunction<Unit>)

internal actual fun setMouseEventListener(elementId: String, listener: ((InputEvent.Mouse) -> Unit)?) =
		setElementMouseEventListener(stringPointer(elementId), stringLengthBytes(elementId)) { (rawEvent) ->
			val type = rawEvent.getInt("type").let { InputEvent.Mouse.Type.values()[it] }
			val locationX = rawEvent.getInt("locationX")
			val locationY = rawEvent.getInt("locationY")
			val button = rawEvent.getInt("button").let { InputEvent.Mouse.Button.values()[it] }
			val buttons = rawEvent.getProperty("buttons").asArray.toList().let { presentButtons ->
				InputEvent.Mouse.Button.values().toList().associateWith { presentButtons[it.ordinal] }
			}
			val altKey = rawEvent.getInt("altKey") != 0
			val ctrlKey = rawEvent.getInt("ctrlKey") != 0
			val metaKey = rawEvent.getInt("metaKey") != 0
			val shiftKey = rawEvent.getInt("shiftKey") != 0
		}

internal actual fun setKeyboardEventListener(listener: ((InputEvent.Key) -> Unit)?) =
		setWindowKeyboardEventListener { (rawEvent) ->
			val type = rawEvent.getInt("type").let { InputEvent.Key.Type.values()[it] }
			val key = "TODO"
			val code = "TODO"
			val repeating = rawEvent.getInt("repeating") != 0
			val altKey = rawEvent.getInt("altKey") != 0
			val ctrlKey = rawEvent.getInt("ctrlKey") != 0
			val metaKey = rawEvent.getInt("metaKey") != 0
			val shiftKey = rawEvent.getInt("shiftKey") != 0
		}
