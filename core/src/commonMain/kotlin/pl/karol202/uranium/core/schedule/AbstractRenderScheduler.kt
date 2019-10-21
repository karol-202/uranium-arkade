package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class AbstractRenderScheduler<N>(coroutineScope: CoroutineScope) : RenderScheduler<N>
{
	companion object
	{
		const val MAX_BUFFER_SIZE = 64
	}

	protected data class Request(private val function: () -> Unit)
	{
		private val deferred = CompletableDeferred<Unit>()

		fun execute()
		{
			function()
			deferred.complete(Unit)
		}

		fun asJob(): Job = deferred
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
		submitAndReturnJob(function)
	}

	protected fun submitAndReturnJob(function: () -> Unit) = Request(function).let { request ->
		scheduleChannel.offer(request) || throw RuntimeException("Too many scheduled renders (probably an infinite loop)")
		request.asJob()
	}
}
