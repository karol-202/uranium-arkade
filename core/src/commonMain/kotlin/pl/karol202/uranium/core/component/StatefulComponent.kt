package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState
import kotlin.jvm.JvmName

abstract class StatefulComponent<N, P : UProps, S : UState>(props: P,
                                                            state: S) : AbstractComponent<N, P>(props)
{
	override val context get() = parentContext

	@set:JvmName("setStateProperty")
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	override fun render() = UBuilder<N>().also { it.render() }.elements

	protected abstract fun UBuilder<N>.render()

	private fun invalidate() = parentContext?.invalidate()

	protected fun setState(state: S)
	{
		this.state = state
	}
}
