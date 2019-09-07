package pl.karol202.uranium.swing

import javax.swing.JFrame

class UraniumFrame(title: String,
                   private val builder: SwingBuilder.() -> Unit) : JFrame(title)
{
	init
	{
		SwingRenderer().renderRoot(createRootElement(), createContext())
	}

	private fun createRootElement() = SwingBuilder().also(builder).elements.single()

	private fun createContext() = SwingContextImpl(this)
}
