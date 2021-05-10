package org.drakosha.tools.calctable.dataprovider.common.type;

import java.util.List;

public class CalcTableStructureNode
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private final String text;

	private final CalcTableCellsDimension innerDimension;

	private final List<CalcTableStructureNode> childStructureNodes;

	// ... constructors

	private CalcTableStructureNode(
			final String text,
			final CalcTableCellsDimension innerDimension,
			final List<CalcTableStructureNode> childStructureNodes
	)
	{

		this.text = text;
		this.innerDimension = innerDimension;
		this.childStructureNodes = childStructureNodes;
	}

	public static CalcTableStructureNode of(
			final String text,
			final CalcTableCellsDimension innerDimension,
			final List<CalcTableStructureNode> childStructureNodes
	)
	{

		return new CalcTableStructureNode(
			text,
			innerDimension,
			childStructureNodes
		);
	}

	// ... setter/getter methods

	public String getText() {

		return this.text;
	}

	public CalcTableCellsDimension getInnerDimension() {

		return this.innerDimension;
	}

	public List<CalcTableStructureNode> getChildStructureNodes() {

		return this.childStructureNodes;
	}

}
