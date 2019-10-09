package pl.karol202.uranium.swing.layout.gridbag

import java.awt.GridBagConstraints

enum class Fill(val code: Int)
{
	HORIZONTAL(GridBagConstraints.HORIZONTAL),
	VERTICAL(GridBagConstraints.VERTICAL),
	BOTH(GridBagConstraints.BOTH)
}
