package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CoroutineScope

class SuspendRenderScheduler<N>(coroutineScope: CoroutineScope) : AbstractRenderScheduler<N>(coroutineScope)
{
	override suspend fun submitAndWait(function: () -> Unit) = submitAndReturnJob(function).join()
}
