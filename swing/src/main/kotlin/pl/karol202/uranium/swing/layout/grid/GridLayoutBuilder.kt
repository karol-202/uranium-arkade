package pl.karol202.uranium.swing.layout.grid

import pl.karol202.uranium.core.render.RenderDsl
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingRenderBuilder

@RenderDsl
abstract class GridLayoutBuilder
{
	protected var sequences = emptyList<SwingRenderBuilder>()
		private set

	protected val sequencesAmount get() = sequences.size
	protected val sequenceSize get() = sequences.map { it.size }.distinct().singleOrNull()
			?: throw IllegalStateException("Inconsistent sequence size")

	abstract val rows: Int
	abstract val columns: Int
	abstract val elements: List<SwingElement<*>>

	protected fun sequence(block: SwingRenderBuilder.() -> Unit)
	{
		sequences += SwingRenderBuilder().apply(block)
	}
}

@RenderDsl
class GridLayoutRowsBuilder : GridLayoutBuilder()
{
	override val rows get() = sequencesAmount
	override val columns get() = sequenceSize
	override val elements get() = sequences.flatMap { it.elements }

	fun row(block: SwingRenderBuilder.() -> Unit) = sequence(block)
}

@RenderDsl
class GridLayoutColumnsBuilder : GridLayoutBuilder()
{
	override val rows get() = sequenceSize
	override val columns get() = sequencesAmount
	override val elements get() = (0 until rows).flatMap { rowIndex -> sequences.map { it.elements[rowIndex] } }

	fun column(block: SwingRenderBuilder.() -> Unit) = sequence(block)
}
