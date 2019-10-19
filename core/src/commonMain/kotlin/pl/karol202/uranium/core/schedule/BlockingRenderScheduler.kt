package pl.karol202.uranium.core.schedule

import kotlinx.coroutines.CoroutineScope

class BlockingRenderScheduler<N>(coroutineScope: CoroutineScope) : BaseRenderScheduler<N>(coroutineScope)
{
	fun submitAndWait(function: () -> Unit) = Request(function).execute()
}
