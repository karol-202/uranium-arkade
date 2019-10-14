package pl.karol202.uranium.core.tree

interface RenderScheduler<N>
{
	fun submit(render: () -> Unit)
}
