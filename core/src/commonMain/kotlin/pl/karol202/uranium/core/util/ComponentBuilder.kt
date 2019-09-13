package pl.karol202.uranium.core.util

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.component.component

class ComponentBuilder<N, P : UProps>(private val renderBuilder: RenderBuilder<N>,
                                      private val constructor: (P) -> UComponent<N, P>,
                                      private val props: P)
{
	fun withProps(propsBuilder: P.() -> P) = ComponentBuilder(renderBuilder, constructor, props.propsBuilder())

	fun build() = renderBuilder.component(constructor, props)
}

fun <N, P : UProps> RenderBuilder<N>.buildComponent(constructor: (P) -> UComponent<N, P>,
                                                    props: P) = ComponentBuilder(this, constructor, props)
