package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.util.Insets
import pl.karol202.uranium.swing.util.Size

interface SwingGridBagScope

fun SwingGridBagScope.weights(x: Double, y: Double) = GridBagWeights(x, y)

fun SwingGridBagScope.insets(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = Insets(left, top, right, bottom)

fun SwingGridBagScope.size(width: Int = 1, height: Int = 0) = Size(width, height)

fun SwingGridBagScope.padding(x: Int = 0, y: Int = 0) = Size(x, y)
