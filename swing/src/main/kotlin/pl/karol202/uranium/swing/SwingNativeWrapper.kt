package pl.karol202.uranium.swing

import pl.karol202.uranium.swing.util.SwingNative

data class SwingNativeWrapper(val component: SwingNative,
                              val constraints: Any?)

fun SwingNative.constrained(constraints: Any?) = SwingNativeWrapper(this, constraints)

fun SwingNative.notConstrained() = SwingNativeWrapper(this, null)
