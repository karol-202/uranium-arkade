plugins {
    kotlin("multiplatform") version "1.3.72" apply false
    kotlin("js") version "1.3.72" apply false
}

allprojects {
    group = "pl.karol202.uranium"
    version = "0.2"

    repositories {
        jcenter()
        mavenLocal()
    }
}
