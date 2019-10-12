package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.SNCProvider
import pl.karol202.uranium.swing.constraints
import pl.karol202.uranium.swing.util.*

fun <EP : SNCProvider<EP>> SwingGridBagScope.cell(x: Int,
                                                  y: Int,
                                                  size: Size = Size(1, 1),
                                                  weights: Weights = Weights(0.0, 0.0),
                                                  anchor: Anchor = Anchor.CENTER,
                                                  fill: Fill = Fill.NONE,
                                                  insets: Insets = Insets(),
                                                  internalPadding: Size = Size(0, 0),
                                                  element: SwingRenderScope.() -> SwingElement<EP>) =
		SwingEmptyRenderScope.element().constraints(Constraints(x, y, size, weights, anchor, fill, insets, internalPadding))
