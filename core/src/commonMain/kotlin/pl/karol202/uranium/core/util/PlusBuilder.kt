package pl.karol202.uranium.core.util

interface PlusBuilder<T>
{
	operator fun T.unaryPlus()

	operator fun List<T>.unaryPlus()
}

abstract class PlusBuilderImpl<T> : PlusBuilder<T>
{
	var elements = emptyList<T>()
		private set

	override operator fun T.unaryPlus()
	{
		this@PlusBuilderImpl.elements += this
	}

	override fun List<T>.unaryPlus()
	{
		this@PlusBuilderImpl.elements += this
	}
}
