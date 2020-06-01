plugins {
	kotlin("multiplatform") version "1.3.72"
}

kotlin {
	js {
		targets {
			browser()
		}
	}
	wasm32 {
		binaries {
			executable {
				entryPoint = "pl.karol202.uranium.webcanvas.test.main"
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":web-canvas"))
			}
		}
		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}

		all {
			languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
		}
	}
}

tasks {
	val copyWasm32BinariesToDevServer by creating(Copy::class) {
		dependsOn("wasm32Binaries")
		from("build/bin/wasm32/debugExecutable")
		into("build/processedResources/js/main")
	}

	val jsProcessResources by getting {
		dependsOn(copyWasm32BinariesToDevServer)
	}
}
