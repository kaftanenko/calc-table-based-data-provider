package org.business.tools.calctable.dataprovider.parser.landscape;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardSampler;
import org.business.tools.calctable.dataprovider.parser.landscape.testdata.CalcTableLandscapeStructureParser_TestDataFactory_MergedFormat;
import org.business.tools.calctable.dataprovider.parser.landscape.testdata.CalcTableLandscapeStructureParser_TestDataFactory_NaturalFormat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

class CalcTableLandscapeStructureParser_UnitTest
		extends
		AbstractCalcTableLandscape_UnitTest
{

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test_ParseStructureArea(
			final String sheetName,
			final CalcTableStructureNode expectedStructureNode
	)
			throws Exception
	{

		// ... prepare test data
		try (final InputStream is = new FileInputStream(
			this.UNIT_TEST__SAMPLE_XLS_FILE__STRUCTURE_PARSER
		);
				final Workbook workbook = new XSSFWorkbook(
					is
				))
		{
			final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				sheetName
			);

			// ... call service under test
			final CalcTableCellsDimension structureAreaDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
				sheet,
				CalcTableHeaderCellStandardSampler.INSTANCE__NON_TRANSPARENT_AND_NON_WHITE_BACKGROUND
			);

			final List<
					CalcTableStructureNode> resultStructureNodes = new CalcTableSheetLandscapeStructureParser().parseStructureArea(
						sheet,
						structureAreaDimension
					);

			// ... verify post-conditions
			assertThat(
				resultStructureNodes
			).hasSize(
				1
			);
			assertThat(
				resultStructureNodes.get(
					0
				)
			).isEqualTo(
				expectedStructureNode
			);
		}
	}

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
				{
						"UT - Structure Parser (merged)",
						CalcTableLandscapeStructureParser_TestDataFactory_MergedFormat.EXPECTED_STRUCTURE_NODES
				},
				new Object[]
				{
						"UT - Structure Parser (natural)",
						CalcTableLandscapeStructureParser_TestDataFactory_NaturalFormat.EXPECTED_STRUCTURE_NODES
				},
		};
	}

}
