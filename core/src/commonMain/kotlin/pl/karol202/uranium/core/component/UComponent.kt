package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.PropsProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.native.Native
import pl.karol202.uranium.core.render.RenderScope

interface UComponent<N, P : UProps> : PropsProvider<P>
{
	val native: Native<N>? get() = null

	fun create(context: ComponentContext)

	fun destroy()

	fun render(): List<UElement<N, *>>

	fun onUpdate(previousProps: P?) { }

	fun modifyPropsInternal(props: P)
}

inline fun <N, reified P : UProps> RenderScope<N>.component(noinline constructor: (P) -> UComponent<N, P>, props: P) =
		UElement(constructor, props, P::class)
