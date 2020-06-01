plugins {
	kotlin("multiplatform") version "1.3.72"
	`maven-publish`
}

kotlin {
	js {
		targets {
			browser()
		}
	}
	wasm32()

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(project(":core"))
				implementation(project("dom-api"))
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

		all {
			languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
		}
	}
}

publishing {
	publications {
		create<MavenPublication>("web-canvas") {
			from(components["kotlin"])
		}
	}
}
