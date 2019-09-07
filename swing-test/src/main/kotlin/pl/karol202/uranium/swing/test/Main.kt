package pl.karol202.uranium.swing.test

import pl.karol202.uranium.swing.SwingFrame
import pl.karol202.uranium.swing.control.button
import pl.karol202.uranium.swing.control.label
import pl.karol202.uranium.swing.layout.flowLayout
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot {
		flowLayout(0) {
			label(0, "Text")
			button(1, "Hello world")
		}
	}.withTitle("Uranium test").withSize(640, 480).show()
}
