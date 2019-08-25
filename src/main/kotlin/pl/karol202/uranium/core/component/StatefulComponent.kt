package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState

abstract class StatefulComponent<P : UProps, S : UState>(props: P,
                                                         state: S) : AbstractComponent<P>(props)
{
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	override fun render() = Builder().also { it.render() }.elements

	protected abstract fun Builder.render()

	private fun invalidate() = invalidateListener?.invoke()
}
