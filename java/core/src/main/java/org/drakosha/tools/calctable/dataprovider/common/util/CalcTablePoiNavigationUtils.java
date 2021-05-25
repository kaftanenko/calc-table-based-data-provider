package org.drakosha.tools.calctable.dataprovider.common.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.drakosha.tools.calctable.dataprovider.common.error.navigation.CalcTableNavigationCellNotFoundException;
import org.drakosha.tools.calctable.dataprovider.common.error.navigation.CalcTableNavigationRowNotFoundException;
import org.drakosha.tools.calctable.dataprovider.common.error.navigation.CalcTableNavigationSheetNotFoundException;
import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;

public class CalcTablePoiNavigationUtils {

	// ... business methods

	public static Sheet getSheet(
			final Workbook workbook,
			final String sheetName
	)
	{

		final Sheet sheet = workbook.getSheet(
				sheetName
		);

		if (sheet != null) {

			return sheet;
		} else {
			throw new CalcTableNavigationSheetNotFoundException(
					workbook,
					sheetName
			);
		}
	}

	public static Row getRow(
			final Sheet sheet,
			final int rowNum
	)
	{

		final Row row = sheet.getRow(
				rowNum
		);

		if (row != null) {
			return row;
		} else {
			throw new CalcTableNavigationRowNotFoundException(
					sheet,
					rowNum
			);
		}
	}

	public static Cell getCell(
			final Row row,
			final int columnNum
	)
	{

		final Cell cell = row.getCell(
				columnNum
		);

		if (cell != null) {
			return cell;
		} else {
			throw new CalcTableNavigationCellNotFoundException(
					row.getSheet(),
					row.getRowNum(),
					columnNum
			);
		}
	}

	public static Cell getCell(
			final Sheet sheet,
			final int rowNum,
			final int columnNum
	)
	{

		final Row row = getRow(
				sheet,
				rowNum
		);
		return getCell(
				row,
				columnNum
		);
	}

	public static CalcTableCellsDimension getCellDimension(
			final Sheet sheet,
			final Cell cell
	)
	{

		final int cellRowNum = cell.getRowIndex();
		final int cellColumnNum = cell.getColumnIndex();

		final CellRangeAddress cellRangeAddress = sheet
				.getMergedRegions()
				.stream()
				.filter(
						mr ->
						{
							return mr.containsRow(
									cellRowNum
							)
									&& mr.containsColumn(
									cellColumnNum
							);
						}
				)
				.findFirst()
				.orElse(
						new CellRangeAddress(
								cellRowNum,
								cellRowNum,
								cellColumnNum,
								cellColumnNum
						)
				);

		final CalcTableCellsDimension innerDimension = CalcTableCellsDimension.of(
				cellRangeAddress.getFirstRow(),
				cellRangeAddress.getFirstColumn(),
				1 + cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow(),
				1 + cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn()
		);
		return innerDimension;
	}

}
