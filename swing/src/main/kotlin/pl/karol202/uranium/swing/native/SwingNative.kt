package pl.karol202.uranium.swing.native

import pl.karol202.uranium.core.native.Native
import pl.karol202.uranium.core.native.NativeContainer
import pl.karol202.uranium.swing.util.Swing
import java.awt.Component
import java.awt.Container

fun Native<Swing>.asSwingNative() = this as SwingNative

sealed class SwingNative : Native<Swing>
{
	companion object
	{
		fun from(component: Component, constraints: Any?) = when(component)
		{
			is Container -> ConstrainedContainer(component, constraints)
			else -> ConstrainedComponent(component, constraints)
		}
	}

	private data class ConstrainedComponent(override val component: Component,
	                                        override val constraints: Any?) : SwingNative()

	private data class ConstrainedContainer(override val component: Container,
	                                        override val constraints: Any?) : SwingNative(), NativeContainer<Swing>
	{
		override val children = component.componentsWithConstraints.map { (comp, constr) -> from(comp, constr) }

		override fun attach(native: Native<Swing>, index: Int)
		{
			val swingNative = native.asSwingNative()
			component.add(swingNative.component, swingNative.constraints, index)
		}

		override fun detach(native: Native<Swing>) = component.remove(native.asSwingNative().component)
	}

	abstract val component: Component
	abstract val constraints: Any?
}
