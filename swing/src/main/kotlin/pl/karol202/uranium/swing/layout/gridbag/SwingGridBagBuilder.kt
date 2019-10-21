package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.swing.util.SwingRenderBuilderBase

open class SwingGridBagBuilder : SwingRenderBuilderBase(), SwingGridBagScope

fun (SwingGridBagBuilder.() -> Unit).render() = SwingGridBagBuilder().also(this).elements
