import com.jfrog.bintray.gradle.BintrayExtension.PackageConfig

plugins {
	kotlin("multiplatform")
	id("com.jfrog.bintray")
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

bintray {
	user = project.findProperty("bintray.user") as String?
	key = project.findProperty("bintray.key") as String?
	setPublications(*publishing.publications.names.toTypedArray())
	publish = true

	pkg(delegateClosureOf<PackageConfig> {
		repo = "uranium"
		name = "uranium-arkade-canvas"
		description = "2D game engine for HTML5 canvas using Uranium"
		vcsUrl = "https://github.com/karol-202/uranium-arkade"
		githubRepo = "karol-202/uranium-arkade"
		setLicenses("MIT")
	})
}

publishing {
	publications.withType<MavenPublication>().all {
		pom {
			name.set(bintray.pkg.name)
			description.set(bintray.pkg.desc)
			url.set(bintray.pkg.vcsUrl)
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
				url.set(bintray.pkg.vcsUrl)
			}
		}
	}
}
