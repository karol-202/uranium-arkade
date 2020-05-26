package pl.karol202.uranium.webcanvas.test

external val konan: dynamic
external val instance: dynamic
external val heap: dynamic

fun extern_test(a: Boolean, lambdaIndex: Int, lambdaResultArena: Int)
{
	println("extern $a")

	val libraries = konan.libraries as Array<dynamic>
	val wrapLambda = libraries.mapNotNull { it.Konan_js_wrapLambda }.single()
	val lambda = wrapLambda(lambdaResultArena, lambdaIndex)

	val obj = jsObject {
		bool = true
		test = 42
	}
	lambda(1, "xyz", 3.8, obj)
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
