plugins {
	kotlin("multiplatform")
}

val jsIncludesPath = "src/wasm32Main/js"

kotlin {
	js {
		targets {
			browser()
		}
	}
	wasm32 {
		val main by compilations.getting {
			kotlinOptions {
				freeCompilerArgs = fileTree(jsIncludesPath).flatMap {
					listOf("-ib", it.absolutePath)
				}
			}
			compileKotlinTask.inputs.dir(jsIncludesPath)
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(kotlin("stdlib-common"))
			}
		}

		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}

		val wasm32Main by getting {
			resources.srcDirs(jsIncludesPath)
		}
	}
}
