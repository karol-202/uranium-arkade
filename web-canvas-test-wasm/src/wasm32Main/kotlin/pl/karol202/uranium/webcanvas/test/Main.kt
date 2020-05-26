package pl.karol202.uranium.webcanvas.test

import kotlinx.cinterop.objcPtr
import kotlinx.wasm.jsinterop.*
import kotlin.native.internal.ExportForCppRuntime

@SymbolName("extern_test")
external fun externTest(a: Boolean, lambdaIndex: Int, lambdaResultArena: Int)

@Retain
@ExportForCppRuntime("intern_test")
fun internTest(a: Boolean)
{
	println("intern $a")
}

fun helloWorld()
{
	println("Hell'o world! Before")

	externTest(false, wrapFunction {
		println(it[0])
		println(it[1])
		println(it[2])
		println(it[3].getInt("bool"))
	}, ArenaManager.currentArena)

	//val value = JsValue(ArenaManager.currentArena, 0)
	//println(value.getProperty())

	println("Hell'o world! After")
}
