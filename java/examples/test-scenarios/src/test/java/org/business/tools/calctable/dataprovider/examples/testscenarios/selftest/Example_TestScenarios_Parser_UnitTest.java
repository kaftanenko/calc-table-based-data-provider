package org.business.tools.calctable.dataprovider.examples.testscenarios.selftest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_LANDSCAPE_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Record;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableLandscapeStandardDataReader;
import org.business.tools.calctable.dataprovider.reader.portrait.CalcTablePortraitStandardDataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Example_TestScenarios_Parser_UnitTest {

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public void test(
			final AbstractCalcTableDataReader reader,
			final String workbookFilePath,
			final String sheetName,
			final List<Example_TestScenarios_SimpleCalculator_Record> expectedResult
	)
			throws Exception
	{

		// ... prepare test data

		try (final InputStream is = new FileInputStream(
			workbookFilePath
		);
				final Workbook workbook = new XSSFWorkbook(
					is
				))
		{
			final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				sheetName
			);

			final List<RuntimeException> messageContainer = new ArrayList<>();

			// ... call service under test
			final List<Example_TestScenarios_SimpleCalculator_Record> result = reader.readData(
				sheet,
				Example_TestScenarios_SimpleCalculator_Record.class,
				messageContainer
			);

			// ... verify post-conditions
			assertThat(result).containsExactlyElementsOf(expectedResult);
			assertThat(messageContainer).isEmpty();
		}
	}

	// ... test data provider

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				{
						new CalcTableLandscapeStandardDataReader(),
						EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_LANDSCAPE_FORMAT,
						EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES,
						_TestDataFactory.EXPECTED__EXAMPLE_TEST_SCENARIOS__DATA
				},
				{
						new CalcTablePortraitStandardDataReader(),
						EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT,
						EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES,
						_TestDataFactory.EXPECTED__EXAMPLE_TEST_SCENARIOS__DATA
				},
		};
	}

}
