package org.drakosha.tools.calctable.dataprovider.common.error.navigation;

import org.apache.poi.ss.usermodel.Sheet;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableNavigationCellNotFoundException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableNavigationCellNotFoundException(
			final Sheet sheet,
			final int rowNum,
			final int columnNum
	)
	{

		super(
				String.format(
						"There is no such cell: [sheet: '%s', row: %d, column: %d] within the workbook: '%s'.",
						sheet.getSheetName(),
						rowNum,
						columnNum,
						sheet.getWorkbook()
				)
		);
	}

}
