package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.native.SNCProvider
import pl.karol202.uranium.swing.native.constraints
import pl.karol202.uranium.swing.util.*

fun <EP : SNCProvider<EP>> SwingGridBagScope.cell(x: Int,
                                                  y: Int,
                                                  size: Size = size(1, 1),
                                                  weights: GridBagWeights = weights(0.0, 0.0),
                                                  anchor: GridBagAnchor = GridBagAnchor.CENTER,
                                                  fill: GridBagFill = GridBagFill.NONE,
                                                  insets: Insets = insets(),
                                                  internalPadding: Size = padding(0, 0),
                                                  element: SwingRenderScope.() -> SwingElement<EP>) =
		SwingEmptyRenderScope.element().constraints(GridBagConstraints(x, y, size, weights, anchor, fill, insets, internalPadding))
