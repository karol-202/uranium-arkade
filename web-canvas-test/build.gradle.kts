import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
	kotlin("js") version "1.3.72"
}

dependencies {
	api(project(":web-canvas"))

	implementation(kotlin("stdlib-js"))
	implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
}

kotlin.target.browser { }

tasks.withType(Kotlin2JsCompile::class) {
	kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.time.ExperimentalTime")
}
