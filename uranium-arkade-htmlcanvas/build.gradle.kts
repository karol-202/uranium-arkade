plugins {
	kotlin("multiplatform")
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
				api("pl.karol202.uranium:uranium-core:0.2.1")

				implementation(kotlin("stdlib-common"))
				implementation(project("dom-api"))
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

object Publish
{
	const val repo = "uranium"
	const val name = "uranium-arkade-htmlcanvas"
	const val description = "2D game engine for HTML5 canvas using Uranium"
	const val vcsUrl = "https://github.com/karol-202/uranium-arkade"
}

publishing {
	repositories {
		val user = System.getenv("BINTRAY_USER")
		val key = System.getenv("BINTRAY_KEY")

		maven("https://api.bintray.com/maven/$user/${Publish.repo}/${Publish.name}/;publish=1") {
			name = "Bintray"
			credentials {
				username = user
				password = key
			}
		}
	}

	publications.withType<MavenPublication>().all {
		pom {
			name.set(Publish.name)
			description.set(Publish.description)
			url.set(Publish.vcsUrl)
			licenses {
				license {
					name.set("MIT")
					url.set("https://opensource.org/licenses/MIT")
				}
			}
			developers {
				developer {
					id.set("karol202")
					name.set("Karol Jurski")
					email.set("karoljurski1@gmail.com")
				}
			}
			scm {
				url.set(Publish.vcsUrl)
			}
		}
	}
}
