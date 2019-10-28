package pl.karol202.uranium.swing.layout

import java.awt.Container
import java.awt.LayoutManager

interface LayoutData<L : LayoutManager>
{
	fun createLayout(container: Container): L

	// May return updated layout passed via argument or a new one
	fun updateLayout(container: Container, layout: LayoutManager): L
}
