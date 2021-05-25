package org.drakosha.tools.calctable.dataprovider.parser.landscape;

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
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardSampler;

abstract class AbstractCalcTableLandscapeDataParser_UnitTest
		extends
		AbstractCalcTableLandscape_UnitTest
{

	// ... dependencies

	private final CalcTableSheetLandscapeDataParser serviceUnderTest = new CalcTableSheetLandscapeDataParser(
		DATA_PARSER_STANDARD_CONFIG
	);

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public <DATA_RECORD_TYPE> void test_ParseDataArea(
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

			final CalcTableCellsDimension structureAreaDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
				sheet,
				CalcTableHeaderCellStandardSampler.INSTANCE__NON_TRANSPARENT_AND_NON_WHITE_BACKGROUND
			);

			final List<
					CalcTableStructureNode> structureDescription = new CalcTableSheetLandscapeStructureParser().parseStructureArea(
						sheet,
						structureAreaDimension
					);

			final Class<DATA_RECORD_TYPE> dataRecordType = (Class<
					DATA_RECORD_TYPE>) expectedDataRecords.getClass().getComponentType();

			final List<RuntimeException> messageContainer = new ArrayList<>();

			// ... call service under test
			final List<DATA_RECORD_TYPE> resultDataRecords = this.serviceUnderTest.parseDataArea(
				sheet,
				dataRecordType,
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
