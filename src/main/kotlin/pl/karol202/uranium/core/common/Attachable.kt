package pl.karol202.uranium.core.common

interface Attachable
{
	fun attach(invalidateListener: () -> Unit)
}
