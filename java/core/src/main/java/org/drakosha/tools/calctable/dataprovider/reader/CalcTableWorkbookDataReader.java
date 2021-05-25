package org.drakosha.tools.calctable.dataprovider.reader;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;

/**
 * Reader for a single workbook's sheet from a Calc table workbook document.
 */
public class CalcTableWorkbookDataReader {

	// ... business methods

	/**
	 * Reads data record sets of the given type from the given sheet within the
	 * given Calc table workbook document with the given Sheet Data Reader.
	 *
	 * @param <DATA_RECORD_TYPE>
	 *          the type of expected data records
	 * @param workbookInputStream
	 *          the workbook input stream to read from
	 * @param sheetName
	 *          the sheet name to read from
	 * @param dataRecordType
	 *          the type of expected data records
	 * @param sheetDataReader
	 *          the Sheet Data Reader to read with
	 * @return resulting data record sets
	 * @throws Exception
	 *           it is sad, must be actually never happen.
	 */
	public <DATA_RECORD_TYPE> List<DATA_RECORD_TYPE> readData(
			final InputStream workbookInputStream,
			final String sheetName,
			final Class<DATA_RECORD_TYPE> dataRecordType,
			final CalcTableSheetDataReader sheetDataReader
	)
			throws Exception
	{

		try (
				final Workbook workbook = new XSSFWorkbook(
					workbookInputStream
				))
		{
			final List<RuntimeException> errorMessageContainer = new ArrayList<>();

			final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				sheetName
			);

			final List<DATA_RECORD_TYPE> resultDataRecords = sheetDataReader.readData(
				sheet,
				dataRecordType,
				errorMessageContainer
			);

			assertThat(errorMessageContainer).isEmpty();

			return resultDataRecords;
		}
	}

}
