import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
	kotlin("js")
}

dependencies {
	api(project(":uranium-arkade-canvas"))

	implementation(kotlin("stdlib-js"))
}

kotlin.target.browser { }

tasks.withType(Kotlin2JsCompile::class) {
	kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.time.ExperimentalTime")
}
