plugins {
	kotlin("js") version "1.3.72"
}

dependencies {
	api(project(":core"))

	implementation(kotlin("stdlib-js"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5")
}

kotlin.target.browser { }
