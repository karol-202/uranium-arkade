plugins {
	kotlin("multiplatform")
	`maven-publish`
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
				api("pl.karol202.uranium:uranium-core:0.2.2")

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
