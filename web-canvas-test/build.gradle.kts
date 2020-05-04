plugins {
	kotlin("js") version "1.3.72"
}

dependencies {
	api(project(":web-canvas"))

	implementation(kotlin("stdlib-js"))
	implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
}

kotlin.target.browser { }
