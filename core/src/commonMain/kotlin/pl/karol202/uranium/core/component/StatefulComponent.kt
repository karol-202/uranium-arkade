package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState
import kotlin.jvm.JvmName

abstract class StatefulComponent<N, P : UProps, S : UState>(props: P,
                                                            state: S) : AbstractComponent<N, P>(props)
{
	@set:JvmName("setStateProperty")
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	protected fun setState(state: S)
	{
		this.state = state
	}
}
