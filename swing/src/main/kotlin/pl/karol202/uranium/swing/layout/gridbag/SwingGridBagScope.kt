package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.util.Insets

interface SwingGridBagScope

fun SwingGridBagScope.insets(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) = Insets(left, top, right, bottom)
