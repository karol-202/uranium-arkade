package pl.karol202.uranium.webcanvas.test

external val konan: dynamic
external val instance: dynamic
external val heap: dynamic

//external fun kotlinObject(arenaIndex: Int, objectIndex: Any): dynamic
external fun toUTF16String(pointer: Int, size: Int): String

fun extern_test(strPtr: Int, strLen: Int)
{
	println("extern test in js ${toUTF16String(strPtr, strLen)}")
	/*//val str = toString(aPtr)
	println("extern test in js")
	//val ko = kotlinObject(0, 0)
	//console.log(ko)
	val libraries = konan.libraries as Array<dynamic>
	val addObject = libraries.mapNotNull { it.Konan_js_addObjectToArena }.single()
	addObject(0, jsObject {
		abcd = "efgh"
	})*/

	instance.exports.intern_test(666)
}

inline fun jsObject(init: dynamic.() -> Unit): dynamic {
	val o = js("{}")
	init(o)
	return o
}

fun main()
{
	konan.libraries.push(jsObject {
		extern_test = ::extern_test
	})
	println("main test 6")
}
