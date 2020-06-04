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
				api("pl.karol202.uranium:uranium-core:0.2")

				implementation(kotlin("stdlib-common"))
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

publishing {
	publications {
		create<MavenPublication>("uranium-arkade-core") {
			from(components["kotlin"])
		}
	}
}
