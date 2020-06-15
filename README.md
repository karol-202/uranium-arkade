# uranium-arkade

![Deployment](
https://github.com/karol-202/uranium-arkade/workflows/Deployment/badge.svg
)
[ ![Download](https://api.bintray.com/packages/karol202/uranium/uranium-arkade-htmlcanvas/images/download.svg) ](
https://bintray.com/karol202/uranium/uranium-arkade-htmlcanvas/_latestVersion
)
[ ![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg) ](
https://opensource.org/licenses/MIT
)

uranium-arkade is 2D game engine for Kotlin based on [uranium](https://github.com/karol-202/uranium).
It's unique because of its React-like, component-based architecture with unidirectional data flow.
Currently it supports HTML5 canvas, but support for more targets is planned.

## Contents
- [Project structure](#project-structure)
- [How to install?](#how-to-install)
- [Contributing](#contributing)

## Project structure

uranium-arkade has been designed as a multi-platform game engine, though at the moment,
the only available variant is the one for HTML5 canvas, `uranium-arkade-htmlcanvas`.

`uranium-arkade-htmlcanvas` itself is a Kotlin multiplatform library, because it supports
running as JavaScript or as WebAssembly (experimental). A few Maven artifacts are available:
- `uranium-arkade-htmlcanvas-js` (recommended) - Kotlin/JS artifact, stable
- `uranium-arkade-htmlcanvas-wasm32` - Kotlin/Native artifact for WebAssembly, experimental
- `uranium-arkade-htmlcanvas` - common artifact for use in multiplatform projects,
where both JS and WebAssembly is used (though I can't come across any useful use cases for it)

## How to install?

All the `uranium-arkade` artifacts are located in [jCenter](https://bintray.com/karol202/uranium/uranium-arkade-canvas),
so you can add it as a dependency to your Gradle or Maven project.
The below snippets demonstrate how to use the Kotlin/JS artifact of `uranium-arkade-htmlcanvas`.

Gradle (Kotlin DSL):
```kotlin
dependencies {
    implementation("pl.karol202.uranium.arkade:uranium-arkade-htmlcanvas-js:0.2.1")
}
```

Gradle (Groovy):
```groovy
dependencies {
    implementation "pl.karol202.uranium.arkade:uranium-arkade-htmlcanvas-js:0.2.1"
}
```

Make sure that you have jCenter in your `repositories` section:
```groovy
repositories {
    jcenter()
}
```

## How to use?

**Work in progress**

## Contributing

Contributions are highly welcome.

If you find a bug or would like have some feature implemented, file an issue.
You can also create a pull request if you have working solution for some issue.
