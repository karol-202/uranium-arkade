import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
	kotlin("js") version "1.3.72"
}

dependencies {
	api(project(":uranium-arkade-canvas"))

	implementation(kotlin("stdlib-js"))
}

kotlin.target.browser { }

tasks.withType(Kotlin2JsCompile::class) {
	kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.time.ExperimentalTime")
}
