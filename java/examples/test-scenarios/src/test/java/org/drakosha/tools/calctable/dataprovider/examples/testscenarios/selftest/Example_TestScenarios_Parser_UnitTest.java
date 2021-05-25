package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.selftest;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_LANDSCAPE_FORMAT;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__SHEET_DATA_READER__PARAMETER__LOCALE__US;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES;

import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Record;
import org.drakosha.tools.calctable.dataprovider.reader.CalcTableSheetDataReader;
import org.drakosha.tools.calctable.dataprovider.reader.CalcTableWorkbookDataReader;
import org.drakosha.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeStandardDataReader;
import org.drakosha.tools.calctable.dataprovider.reader.portrait.CalcTableSheetPortraitStandardDataReader;

public class Example_TestScenarios_Parser_UnitTest {

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public void test(
			final CalcTableSheetDataReader sheetDataReader,
			final String workbookFilePath,
			final String sheetName,
			final List<Example_TestScenarios_SimpleCalculator_Record> expectedResult
	)
			throws Exception
	{

		try (final InputStream workbookInputStream = new FileInputStream(
			workbookFilePath
		))
		{

			// ... call service under test
			final CalcTableWorkbookDataReader workbookDataReader = new CalcTableWorkbookDataReader();

			final List<Example_TestScenarios_SimpleCalculator_Record> result = workbookDataReader.readData(
				workbookInputStream,
				sheetName,
				Example_TestScenarios_SimpleCalculator_Record.class,
				sheetDataReader
			);

			// ... verify post-conditions
			assertThat(result).containsExactlyElementsOf(expectedResult);
		}
	}

	// ... test data provider

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				{
						new CalcTableSheetLandscapeStandardDataReader(
							EXAMPLE_TEST_SCENARIOS__SHEET_DATA_READER__PARAMETER__LOCALE__US
						),
						EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_LANDSCAPE_FORMAT,
						EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES,
						_TestDataFactory.EXPECTED__EXAMPLE_TEST_SCENARIOS__DATA
				},
				{
						new CalcTableSheetPortraitStandardDataReader(
							EXAMPLE_TEST_SCENARIOS__SHEET_DATA_READER__PARAMETER__LOCALE__US
						),
						EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT,
						EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES,
						_TestDataFactory.EXPECTED__EXAMPLE_TEST_SCENARIOS__DATA
				},
		};
	}

}
