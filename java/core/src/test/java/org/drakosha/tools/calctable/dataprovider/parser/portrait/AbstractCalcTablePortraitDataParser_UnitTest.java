package org.drakosha.tools.calctable.dataprovider.parser.portrait;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;

abstract class AbstractCalcTablePortraitDataParser_UnitTest
		extends
		AbstractCalcTablePortrait_UnitTest
{

	// ... dependencies

	private final CalcTableSheetPortraitDataParser serviceUnderTest = new CalcTableSheetPortraitDataParser(
		DATA_PARSER_STANDARD_CONFIG
	);

	// ... test methods

	@Test(dataProvider = "dataProvider")
	<DATA_RECORD_TYPE> void test_ParseDataArea(
			final String filePath,
			final String sheetName,
			final DATA_RECORD_TYPE[] expectedDataRecords
	)
			throws Exception
	{

		// ... prepare test data
		try (final InputStream is = new FileInputStream(
			filePath
		);
				final Workbook workbook = new XSSFWorkbook(
					is
				))
		{
			final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				sheetName
			);

			final CalcTableCellsDimension structureAreaDimension = CalcTableCellsDimension.of(
				FIRST_ROW_NUM,
				FIRST_STRUCTURE_COLUMN_NUM,
				sheet.getLastRowNum() - FIRST_ROW_NUM + 1,
				FIRST_DATA_COLUMN_NUM - FIRST_STRUCTURE_COLUMN_NUM
			);

			final List<
					CalcTableStructureNode> structureDescription = new CalcTableSheetPortraitStructureParser().parseStructureArea(
						sheet,
						structureAreaDimension
					);

			final Class<DATA_RECORD_TYPE> dataRecordType = (Class<
					DATA_RECORD_TYPE>) expectedDataRecords.getClass().getComponentType();

			final List<RuntimeException> messageContainer = new ArrayList<>();

			// ... call service under test
			final CalcTableCellsDimension dataAreaDimension = CalcTableCellsDimension.of(
				FIRST_ROW_NUM,
				FIRST_DATA_COLUMN_NUM,
				sheet.getLastRowNum() - FIRST_ROW_NUM + 1,
				expectedDataRecords.length
			);

			final List<DATA_RECORD_TYPE> resultDataRecords = this.serviceUnderTest.parseDataArea(
				sheet,
				dataRecordType,
				dataAreaDimension,
				structureDescription,
				messageContainer
			);

			// ... verify post-conditions
			verifyMessageContainer(
				messageContainer
			);

			assertThat(
				resultDataRecords
			).containsExactly(
				expectedDataRecords
			);
		}
	}

}
