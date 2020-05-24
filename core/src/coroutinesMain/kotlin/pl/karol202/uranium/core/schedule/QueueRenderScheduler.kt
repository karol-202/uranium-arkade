package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import pl.karol202.uranium.core.util.StackTrace

internal actual fun queueRenderScheduler(queueSize: Int): RenderScheduler =
		QueueRenderScheduler(CoroutineScope(Dispatchers.Main), queueSize)

internal class QueueRenderScheduler(coroutineScope: CoroutineScope,
                                    queueSize: Int) : RenderScheduler
{
	private data class Request(private val function: () -> Unit,
	                           val stackTrace: StackTrace) // For debugging purposes
	{
		fun execute() = function()
	}

	private val scheduleChannel = Channel<Request>(queueSize)

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
		val request = Request(function, StackTrace.current)
		scheduleChannel.offer(request) || throw RuntimeException("Too many scheduled renders (probably an infinite loop)")
	}
}
