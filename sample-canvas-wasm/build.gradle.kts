plugins {
	kotlin("multiplatform")
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
				entryPoint = "pl.karol202.uranium.arkade.htmlcanvas.sample.wasm.main"
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":uranium-arkade-htmlcanvas"))
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
