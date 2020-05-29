package pl.karol202.uranium.webcanvas.component.ui

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.containers.group
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.values.Vector

fun WCRenderScope.stack(key: Any = AutoKey,
                        elementsAmount: Int,
                        elementOffset: Vector,
                        elementContent: WCRenderBuilder.(Int) -> Unit) =
		stack(key = key,
		      elements = List(elementsAmount) { Unit },
		      elementOffset = elementOffset) { _, index ->
			elementContent(index)
		}

fun <E> WCRenderScope.stack(key: Any = AutoKey,
                            elements: List<E>,
                            elementOffset: Vector,
                            elementContent: WCRenderBuilder.(E, Int) -> Unit) =
		group(key = key) {
			elements.forEachIndexed { index, element ->
				+ translate(key = index,
				            vector = elementOffset * index.toDouble()) {
					+ elementContent.asRenderer(element, index).render()
				}
			}
		}

private fun <E> (WCRenderBuilder.(E, Int) -> Unit).asRenderer(element: E, index: Int): WCRenderBuilder.() -> Unit =
		{ this@asRenderer(element, index) }
