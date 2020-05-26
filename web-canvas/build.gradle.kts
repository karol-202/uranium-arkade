import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile

plugins {
	kotlin("multiplatform") version "1.3.72"
	`maven-publish`
}

val jsInteropPackage = "kotlinx.interop.wasm.dom"
val jsInteropKlibFile = buildDir.resolve("klib").resolve("$jsInteropPackage-jsInterop.klib")

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

tasks {
	val jsInterop by registering(Exec::class) {
		workingDir = projectDir

		val isWindows = System.getProperty("os.name").startsWith("Windows")
		val ext = if(isWindows) ".bat" else ""
		val distributionPath = project.properties["kotlin.native.home"] as String?

		if(distributionPath != null)
		{
			val jsInteropCommand = file(distributionPath).resolve("bin").resolve("jsinterop$ext")

			inputs.property("jsInteropCommand", jsInteropCommand)
			inputs.property("jsInteropPackage", jsInteropPackage)
			outputs.file(jsInteropKlibFile)

			commandLine(jsInteropCommand,
			            "-pkg", jsInteropPackage,
			            "-o", jsInteropKlibFile,
			            "-target", "wasm32")
		}
		else
		{
			doFirst {
				throw GradleException("""
	                    |Kotlin/Native distribution path must be specified to build the JavaScript interop.
	                    |Use 'kotlin.native.home' project property to specify it.
	                """.trimMargin()
				)
			}
		}
	}

	/*withType<AbstractKotlinNativeCompile<*>>().all {
		dependsOn(jsInterop)
	}*/
}

publishing {
	publications {
		create<MavenPublication>("web-canvas") {
			from(components["kotlin"])
		}
	}
}
