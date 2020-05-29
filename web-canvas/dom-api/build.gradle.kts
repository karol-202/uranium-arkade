plugins {
	kotlin("multiplatform")
}

kotlin {
	js()
	wasm32()

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
			dependencies {
			}
		}
	}
}
