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
				api(project(":uranium-arkade-core"))

				implementation(kotlin("stdlib-common"))
				implementation(project("dom-api"))
			}
		}

		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}
	}
}

publishing {
	publications {
		create<MavenPublication>("uranium-arkade-canvas") {
			from(components["kotlin"])
		}
	}
}
