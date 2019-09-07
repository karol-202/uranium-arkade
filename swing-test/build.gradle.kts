import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.50"
	application
}

application.mainClassName = "pl.karol202.uranium.swing.test.MainKt"

dependencies {
	implementation(project(":swing"))

	implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
