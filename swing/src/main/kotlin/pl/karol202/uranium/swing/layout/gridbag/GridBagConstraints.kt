package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.util.Insets
import pl.karol202.uranium.swing.util.Size
import java.awt.GridBagConstraints

data class GridBagConstraints(private val x: Int,
                              private val y: Int,
                              private val size: Size,
                              private val weights: GridBagWeights,
                              private val anchor: GridBagAnchor,
                              private val fill: GridBagFill,
                              private val insets: Insets,
                              private val padding: Size) : GridBagConstraints(x, y, size.x, size.y, weights.x, weights.y,
                                                                              anchor.code, fill.code, insets.toAWTInsets(),
                                                                              padding.x, padding.y)
