package pl.karol202.uranium.swing.test

import pl.karol202.uranium.swing.control.button
import pl.karol202.uranium.swing.control.label
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.flowLayout
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot {
		flowLayout(key = 0) {
			label(key = 0, text = "Text")
			button(key = 1, text = "Hello world")
		}
	}.withTitle("Uranium test").withSize(640, 480).show()
}
