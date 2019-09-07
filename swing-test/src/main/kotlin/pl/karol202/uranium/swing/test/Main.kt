package pl.karol202.uranium.swing.test

import pl.karol202.uranium.swing.SwingFrame
import pl.karol202.uranium.swing.control.button
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
	SwingFrame.withRoot {
		button(0, "Hello world")
	}.withTitle("Uranium test").withSize(640, 480).show()
}
