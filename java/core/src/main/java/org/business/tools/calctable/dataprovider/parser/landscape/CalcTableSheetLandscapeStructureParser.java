package org.business.tools.calctable.dataprovider.parser.landscape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.error.CalcTableException;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;

public class CalcTableSheetLandscapeStructureParser {

	// ... business methods

	public List<CalcTableStructureNode> parseStructureArea(
			final Sheet sheet,
			final CalcTableCellsDimension structureAreaDimension
	)
	{

		final Cell currentStructureCell;
		try {
			currentStructureCell = CalcTablePoiNavigationUtils.getCell(
				sheet,
				structureAreaDimension.getRow(),
				structureAreaDimension.getColumn()
			);
		} catch (final CalcTableException ex) {
			return Arrays.asList();
		}

		final String currentStructureCellText = CalcTablePoiDataUtils.getCellValueAsString(currentStructureCell);
		if (StringUtils.isBlank(
			currentStructureCellText
		))
		{
			return Arrays.asList();
		}

		final List<CalcTableStructureNode> resultStructureNodes = new ArrayList<>();

		final CalcTableCellsDimension currentStructureCellInnerDimension = CalcTablePoiNavigationUtils.getCellDimension(
			sheet,
			currentStructureCell
		);

		final List<CalcTableStructureNode> childStructureNodes;

		final int childStructureCellStartRowNum = currentStructureCellInnerDimension.getRow()
				+ currentStructureCellInnerDimension.getRowSpan();
		final int lastStructureAreaRowNum = structureAreaDimension.getRow() + structureAreaDimension.getRowSpan() - 1;

		final boolean thereAreNoMoreChildren = childStructureCellStartRowNum > lastStructureAreaRowNum;
		if (thereAreNoMoreChildren) {

			childStructureNodes = Arrays.asList();
		} else {
			final CalcTableCellsDimension childStructureAreaDimension = CalcTableCellsDimension.of(
				childStructureCellStartRowNum,
				structureAreaDimension.getColumn(),
				structureAreaDimension.getRowSpan() - currentStructureCellInnerDimension.getRowSpan(),
				currentStructureCellInnerDimension.getColumnSpan()
			);
			childStructureNodes = parseStructureArea(
				sheet,
				childStructureAreaDimension
			);
		}

		resultStructureNodes.add(
			CalcTableStructureNode.of(
				currentStructureCellText,
				currentStructureCellInnerDimension,
				childStructureNodes
			)
		);

		final List<CalcTableStructureNode> siblingStructureNodes;

		final int nextSiblingColumnNum = currentStructureCellInnerDimension.getColumn()
				+ currentStructureCellInnerDimension.getColumnSpan();
		final int lastStructureAreaColumnNum = structureAreaDimension.getColumn()
				+ structureAreaDimension.getColumnSpan()
				- 1;

		final boolean thereAreNoMoreSiblings = nextSiblingColumnNum > lastStructureAreaColumnNum;

		if (thereAreNoMoreSiblings) {
			siblingStructureNodes = Arrays.asList();
		} else {
			final CalcTableCellsDimension siblingStructureNodesDimension = CalcTableCellsDimension.of(
				structureAreaDimension.getRow(),
				nextSiblingColumnNum,
				structureAreaDimension.getRowSpan(),
				structureAreaDimension.getColumnSpan() - currentStructureCellInnerDimension.getColumnSpan()
			);
			siblingStructureNodes = parseStructureArea(
				sheet,
				siblingStructureNodesDimension
			);
		}

		resultStructureNodes.addAll(
			siblingStructureNodes
		);

		return resultStructureNodes;
	}

}
