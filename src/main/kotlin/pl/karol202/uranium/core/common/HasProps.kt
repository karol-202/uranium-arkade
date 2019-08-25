package pl.karol202.uranium.core.common

interface HasProps<P : UProps> : HasKey
{
	val props: P

	override val key get() = props.key
}
