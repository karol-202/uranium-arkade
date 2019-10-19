package pl.karol202.uranium.core.schedule

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.tree.createNode

interface RenderScheduler<N>
{
	fun submit(function: () -> Unit)

	suspend fun submitAndWait(function: () -> Unit)
}

fun <N, P : UProps> RenderScheduler<N>.renderToNode(element: UElement<N, P>, context: UContext<N>) =
		element.createNode(context, this).also { it.scheduleInit() }

suspend fun <N, P : UProps> RenderScheduler<N>.renderToNodeAndWait(element: UElement<N, P>, context: UContext<N>) =
		element.createNode(context, this).also { it.scheduleInitAndWait() }
