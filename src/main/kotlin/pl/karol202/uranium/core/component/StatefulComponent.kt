package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState

abstract class StatefulComponent<N, P : UProps, S : UState>(props: P,
                                                            state: S) : AbstractComponent<N, P>(props)
{
	override val context = parentContext

	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	override fun render() = UBuilder<N>().also { it.render() }.elements

	protected abstract fun UBuilder<N>.render()

	private fun invalidate() = parentContext?.invalidate()
}
