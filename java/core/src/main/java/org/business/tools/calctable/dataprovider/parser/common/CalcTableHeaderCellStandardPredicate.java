package org.business.tools.calctable.dataprovider.parser.common;

import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xssf.usermodel.XSSFColor;

import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;

public class CalcTableHeaderCellStandardPredicate {

	// ... constants

	private static final Color[] DEFAULT_BG_COLORS = new Color[] {
			new XSSFColor(java.awt.Color.WHITE)
	};

	public static final Predicate<Cell> INSTANCE__NON_TRANSPARENT_BACKGROUND = (
			final Cell cell
	) ->
	{
		return !(CalcTablePoiDataUtils.hasNoBackgroundColor(cell)
				|| CalcTablePoiDataUtils.hasBackgroundColor(
					cell,
					DEFAULT_BG_COLORS
				));
	};

}
