package pl.karol202.uranium.core.util

interface PlusBuilder<T>
{
	operator fun T.unaryPlus()

	operator fun List<T>.unaryPlus()
}

abstract class PlusBuilderImpl<T> : PlusBuilder<T>
{
	private val _elements = arrayListOf<T>()
	val elements = _elements as List<T>

	override operator fun T.unaryPlus()
	{
		_elements += this
	}

	override fun List<T>.unaryPlus()
	{
		_elements += this
	}
}
