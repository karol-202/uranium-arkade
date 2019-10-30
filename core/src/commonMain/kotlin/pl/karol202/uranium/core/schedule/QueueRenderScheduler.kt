package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class QueueRenderScheduler<N>(coroutineScope: CoroutineScope) : RenderScheduler<N>
{
	companion object
	{
		const val MAX_BUFFER_SIZE = 64
	}

	private data class Request(private val function: () -> Unit)
	{
		fun execute() = function()
	}

	private val scheduleChannel = Channel<Request>(MAX_BUFFER_SIZE)

	init
	{
		coroutineScope.launch { run() }
	}

	private suspend fun run()
	{
		for(request in scheduleChannel) request.execute()
	}

	override fun submit(function: () -> Unit)
	{
		val request = Request(function)
		scheduleChannel.offer(request) || throw RuntimeException("Too many scheduled renders (probably an infinite loop)")
	}
}
