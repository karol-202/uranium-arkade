package pl.karol202.uranium.core.common

interface UProps : KeyProvider

data class BaseProps(override val key: Any = AutoKey) : UProps

abstract class CompositeProps<S : CompositeProps<S, B>, B : UProps>(open val parentProps: B) : UProps by parentProps
{
	abstract fun withParentProps(parentProps: B): S
}

interface PropsProvider<P : UProps> : KeyProvider
{
	val props: P

	override val key get() = props.key
}
