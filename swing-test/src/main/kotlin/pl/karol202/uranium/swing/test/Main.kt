package pl.karol202.uranium.swing.test

import pl.karol202.uranium.swing.UraniumSwingFrame
import pl.karol202.uranium.swing.controls.button
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
	UraniumSwingFrame.withRoot {
		button(0, "Hello world")
	}.withTitle("Uranium test").withSize(640, 480).show()
}
