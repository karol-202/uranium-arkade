package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.context.InvalidateableContext

abstract class StatefulComponent<C : InvalidateableContext<*>, P : UProps, S : UState>(props: P,
                                                                                       state: S) : AbstractComponent<C, P>(props)
{
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	override fun render() = Builder().also { it.render() }.elements

	protected abstract fun Builder.render()

	private fun invalidate() = context?.invalidate()
}
