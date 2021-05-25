package org.drakosha.tools.calctable.dataprovider.parser.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xssf.usermodel.XSSFColor;

import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.drakosha.tools.calctable.dataprovider.parser.CalcTableHeaderCellSampler;

/**
 * The standard implementation of the {@link CalcTableHeaderCellSampler}.
 * <p>
 * Recognizes the header cells on their consistent non-transparent and non-white
 * background color.
 */
public class CalcTableHeaderCellStandardSampler
		implements
		CalcTableHeaderCellSampler
{

	// ... constants

	private static final Color[] DEFAULT_BG_COLORS = new Color[] {
			new XSSFColor(java.awt.Color.WHITE)
	};

	public static final CalcTableHeaderCellSampler INSTANCE__NON_TRANSPARENT_AND_NON_WHITE_BACKGROUND = //
			new CalcTableHeaderCellStandardSampler();

	// ... constructors

	protected CalcTableHeaderCellStandardSampler() {

		super();
	}

	// ... business methods

	@Override
	public boolean test(final Cell cell) {

		return !(CalcTablePoiDataUtils.hasNoBackgroundColor(cell)
				|| CalcTablePoiDataUtils.hasBackgroundColor(
				cell,
				DEFAULT_BG_COLORS
		));
	}
}
