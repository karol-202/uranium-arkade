package pl.karol202.uranium.arkade.htmlcanvas.component.ui

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.containers.group
import pl.karol202.uranium.arkade.htmlcanvas.component.containers.translate
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.stack(key: Any = AutoKey,
                            elementsAmount: Int,
                            elementOffset: Vector,
                            elementContent: ArkadeRenderBuilder.(Int) -> Unit) =
		stack(key = key,
		      elements = List(elementsAmount) { Unit },
		      elementOffset = elementOffset) { _, index ->
			elementContent(index)
		}

fun <E> ArkadeRenderScope.stack(key: Any = AutoKey,
                                elements: List<E>,
                                elementOffset: Vector,
                                elementContent: ArkadeRenderBuilder.(E, Int) -> Unit) =
		group(key = key) {
			elements.forEachIndexed { index, element ->
				+ translate(key = index,
				            vector = elementOffset * index.toDouble()) {
					+ elementContent.asRenderer(element, index).render()
				}
			}
		}

private fun <E> (ArkadeRenderBuilder.(E, Int) -> Unit).asRenderer(element: E, index: Int): ArkadeRenderBuilder.() -> Unit =
		{ this@asRenderer(element, index) }
