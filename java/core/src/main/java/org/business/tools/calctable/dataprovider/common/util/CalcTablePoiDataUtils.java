package org.business.tools.calctable.dataprovider.common.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.error.CalcTableException;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;

public class CalcTablePoiDataUtils {

	// ... business methods

	public static CalcTableCellsDimension determineCellsAreaDimension(
			final Sheet sheet,
			final Predicate<Cell> cellsAreaPredicate
	)
	{

		int startStructureCellRowNum = -1;
		int startStructureCellColumnNum = -1;

		final Iterator<Row> rit = sheet.rowIterator();
		while (rit.hasNext()) {
			final Row row = rit.next();
			final Iterator<Cell> cit = row.cellIterator();
			while (cit.hasNext()) {
				final Cell cell = cit.next();
				if (cellsAreaPredicate.test(cell)) {
					startStructureCellRowNum = cell.getRowIndex();
					startStructureCellColumnNum = cell.getColumnIndex();
					break;
				}
			}
			if (startStructureCellRowNum >= 0) {
				break;
			}
		}

		if (startStructureCellRowNum < 0) {
			throw handleCellsAreaNotFoundException(
				sheet
			);
		}

		int lastStructureCellRowNum = startStructureCellRowNum;
		try {
			while (cellsAreaPredicate.test(
				CalcTablePoiNavigationUtils.getCell(
					sheet,
					lastStructureCellRowNum + 1,
					startStructureCellColumnNum
				)
			))
			{
				lastStructureCellRowNum++;
			}
		} catch (final CalcTableException ex) {
			// there are no more cells within the given column, nothing to do.
		}

		int lastStructureCellColumnNum = startStructureCellColumnNum;
		try {
			while (cellsAreaPredicate.test(
				CalcTablePoiNavigationUtils.getCell(
					sheet,
					startStructureCellRowNum,
					lastStructureCellColumnNum + 1
				)
			))
			{
				lastStructureCellColumnNum++;
			}
		} catch (final CalcTableException ex) {
			// there are no more cells within the given row, nothing to do.
		}

		final CalcTableCellsDimension structureAreaDimension = CalcTableCellsDimension.of(
			startStructureCellRowNum,
			startStructureCellColumnNum,
			1 + lastStructureCellRowNum - startStructureCellRowNum,
			1 + lastStructureCellColumnNum - startStructureCellColumnNum
		);
		return structureAreaDimension;
	}

	public static boolean hasBackgroundColor(
			final Cell cell,
			final Color... bgColors
	)
	{

		final Color cellBackgroundColor = cell.getCellStyle().getFillBackgroundColorColor();
		return Arrays.stream(bgColors).filter(bgColor -> bgColor.equals(cellBackgroundColor)).findAny().isPresent();
	}

	public static boolean hasNoBackgroundColor(final Cell cell) {

		final Color cellBackgroundColor = cell.getCellStyle().getFillBackgroundColorColor();
		return cellBackgroundColor == null;
	}

	public static String getCellValueAsString(
			final Cell cell
	)
	{

		final CellType cellTypeEnum = cell.getCellTypeEnum();
		switch (cellTypeEnum) {
			case BLANK:
				return null;
			case BOOLEAN:
				return Boolean.toString(
					cell.getBooleanCellValue()
				);
			case NUMERIC:
				return Double.toString(
					cell.getNumericCellValue()
				);
			case STRING:
				return cell.getStringCellValue().trim();
			default:
				throw CalcTableErrorHelper.handleUnsupportedValue(
					cellTypeEnum
				);
		}
	}

	// ... helper methods

	private static RuntimeException handleCellsAreaNotFoundException(
			final Sheet sheet
	)
	{

		throw new CalcTableException(
			String.format(
				"No structure area found within the [sheet: '%s']!",
				sheet.getSheetName()
			)
		);
	}

}
