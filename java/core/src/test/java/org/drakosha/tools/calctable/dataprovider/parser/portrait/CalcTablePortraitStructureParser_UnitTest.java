package org.drakosha.tools.calctable.dataprovider.parser.portrait;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.drakosha.tools.calctable.dataprovider.parser.portrait.testdata.CalcTablePortraitStructureParser_TestDataFactory;

class CalcTablePortraitStructureParser_UnitTest
		extends
		AbstractCalcTablePortrait_UnitTest
{

	// ... dependencies

	private final CalcTableSheetPortraitStructureParser serviceUnderTest = new CalcTableSheetPortraitStructureParser();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test_ParseStructureArea(
			final String sheetName,
			final CalcTableStructureNode[] expectedStructureNode
	)
			throws Exception
	{

		// ... prepare test data
		try (
				final InputStream is = new FileInputStream(
						UNIT_TEST__SAMPLE_XLS_FILE__STRUCTURE_PARSER
				);
				final Workbook workbook = new XSSFWorkbook(
						is
				)
		) {
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

			// ... call service under test
			final List<
					CalcTableStructureNode> resultStructureNodes = serviceUnderTest.parseStructureArea(
					sheet,
					structureAreaDimension
			);

			// ... verify post-conditions
			// verifyMessageContainer(
			// messageContainer
			// );

			assertThat(
					resultStructureNodes
			)
					.containsExactly(
							expectedStructureNode
					);
		}
	}

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
						{
								"UT - Structure Parser",
								new CalcTableStructureNode[]
										{
												CalcTablePortraitStructureParser_TestDataFactory.EXPECTED_STRUCTURE_NODES
										}
						},
		};
	}

}
