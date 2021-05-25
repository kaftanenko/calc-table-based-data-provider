package org.drakosha.tools.calctable.dataprovider.common.error.navigation;

import org.apache.poi.ss.usermodel.Workbook;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableNavigationSheetNotFoundException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableNavigationSheetNotFoundException(
			final Workbook workbook,
			final String sheetName
	)
	{

		super(
			String.format(
				"There is no such sheet: [sheet: '%s'] within the workbook: '%s'.",
				sheetName,
				workbook
			)
		);
	}

}
