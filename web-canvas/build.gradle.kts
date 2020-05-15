import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
	kotlin("js") version "1.3.72"
	`maven-publish`
}

dependencies {
	api(project(":core"))

	implementation(kotlin("stdlib-js"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.5")
}

kotlin.target.browser { }

tasks.withType(Kotlin2JsCompile::class) {
	kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.time.ExperimentalTime")
}

publishing {
	publications {
		create<MavenPublication>("web-canvas") {
			from(components["kotlin"])
		}
	}
}
