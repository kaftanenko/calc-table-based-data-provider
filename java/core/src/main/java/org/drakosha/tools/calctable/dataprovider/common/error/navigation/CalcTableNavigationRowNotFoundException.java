package org.drakosha.tools.calctable.dataprovider.common.error.navigation;

import org.apache.poi.ss.usermodel.Sheet;
import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableNavigationRowNotFoundException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableNavigationRowNotFoundException(
			final Sheet sheet,
			final int rowNum
	)
	{

		super(
			String.format(
				"There is no such row: [sheet: '%s', row: %d] within the workbook: '%s'.",
				sheet.getSheetName(),
				rowNum,
				sheet.getWorkbook()
			)
		);
	}

}
