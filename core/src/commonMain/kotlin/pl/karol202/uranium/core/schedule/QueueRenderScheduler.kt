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
                                                            coroutineContext: CoroutineContext = Dispatchers.Main) =
		createNode(context, QueueRenderScheduler(CoroutineScope(coroutineContext))).apply { scheduleInit() }

class QueueRenderScheduler<N>(coroutineScope: CoroutineScope) : RenderScheduler<N>
{
	companion object
	{
		const val MAX_BUFFER_SIZE = 64
	}

	private val scheduleChannel = Channel<() -> Unit>(MAX_BUFFER_SIZE)

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
		if(!scheduleChannel.offer(render)) throw RuntimeException("Too many scheduled renders (probably an infinite loop)")
	}
}
