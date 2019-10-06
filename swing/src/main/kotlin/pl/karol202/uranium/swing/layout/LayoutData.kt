package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.swing.util.SwingContainer
import java.awt.LayoutManager

interface LayoutData<L : LayoutManager>
{
	fun createLayout(container: SwingContainer): L

	// May return updated layout passed via argument or a new one
	fun updateLayout(container: SwingContainer, layout: LayoutManager): L
}
