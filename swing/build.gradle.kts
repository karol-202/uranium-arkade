import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.61"
	`maven-publish`
}

dependencies {
	api(project(":core"))

	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.3.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

publishing {
	publications {
		create<MavenPublication>("swing") {
			from(components["java"])
		}
	}
}
