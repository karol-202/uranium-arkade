package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.SNCProvider
import pl.karol202.uranium.swing.constraints
import pl.karol202.uranium.swing.util.*
import java.awt.GridBagConstraints
import java.awt.GridBagConstraints.CENTER
import java.awt.GridBagConstraints.NONE

fun <EP : SNCProvider<EP>> SwingGridBagScope.cell(x: Int,
                                                  y: Int,
                                                  size: Size? = null,
                                                  weights: Weights? = null,
                                                  anchor: Anchor? = null,
                                                  fill: Fill? = null,
                                                  insets: Insets? = null,
                                                  internalPadding: Size? = null,
                                                  element: SwingRenderScope.() -> SwingElement<EP>) =
		SwingEmptyRenderScope.element().constraints(
				GridBagConstraints(x, y,
				                   size?.x ?: 1, size?.y ?: 1,
				                   weights?.x ?: 0.0, weights?.y ?: 0.0,
				                   anchor?.code ?: CENTER,
				                   fill?.code ?: NONE,
				                   insets?.toAWTInsets() ?: java.awt.Insets(0, 0, 0, 0),
				                   internalPadding?.x ?: 0, internalPadding?.y ?: 0)
		)
