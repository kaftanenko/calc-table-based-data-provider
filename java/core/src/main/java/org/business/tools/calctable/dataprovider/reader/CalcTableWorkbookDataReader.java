package org.business.tools.calctable.dataprovider.reader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;

public class CalcTableWorkbookDataReader {

	// ... business methods

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
