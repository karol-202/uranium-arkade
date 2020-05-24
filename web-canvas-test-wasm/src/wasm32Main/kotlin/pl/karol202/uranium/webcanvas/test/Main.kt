package pl.karol202.uranium.webcanvas.test

import kotlinx.wasm.jsinterop.*
import kotlin.native.internal.ExportForCppRuntime

@SymbolName("extern_test")
external fun externTest(strPtr: Pointer, strLen: Int)

@Retain
@ExportForCppRuntime("intern_test")
fun internTest(a: Int)
{
	println("intern $a")
}

fun helloWorld()
{
	println("Hell'o world! Before")

	val str = "abcdef"
	val size = stringLengthBytes(str)
	println("strlen $size")
	externTest(stringPointer(str), size)

	//val value = JsValue(ArenaManager.currentArena, 0)
	//println(value.getProperty())

	println("Hell'o world! After")
}
