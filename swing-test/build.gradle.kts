import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.50"
	application
}

application {
	mainClassName = "pl.karol202.uranium.swing.test.MainKt"
	applicationDefaultJvmArgs = listOf("-Dsun.awt.disablegrab=true")
}

dependencies {
	implementation(project(":swing"))

	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
