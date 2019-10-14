package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.tree.RenderScheduler
import pl.karol202.uranium.core.tree.createNode
import kotlin.coroutines.CoroutineContext

fun <N, P : UProps> UElement<N, P>.renderWithQueueScheduler(context: UContext<N>,
                                                coroutineContext: CoroutineContext = Dispatchers.Default) =
		createNode(context, QueueRenderScheduler(CoroutineScope(coroutineContext))).apply { scheduleInit() }

class QueueRenderScheduler<N>(coroutineScope: CoroutineScope) : RenderScheduler<N>
{
	private val scheduleChannel = Channel<() -> Unit>(Channel.UNLIMITED)

	init
	{
		coroutineScope.launch { run() }
	}

	private suspend fun run()
	{
		for(render in scheduleChannel) render()
	}

	override fun submit(render: () -> Unit)
	{
		scheduleChannel.offer(render)
	}
}
