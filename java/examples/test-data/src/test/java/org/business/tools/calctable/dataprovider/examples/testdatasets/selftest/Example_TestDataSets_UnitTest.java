package org.business.tools.calctable.dataprovider.examples.testdatasets.selftest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.business.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_LANDSCAPE_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_PORTRAIT_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA;
import static org.business.tools.calctable.dataprovider.examples.testdatasets.selftest._TestDataFactory.EXPECTED__TEST_DATA_SETS__PERSON_DATA;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableLandscapeStandardDataReader;
import org.business.tools.calctable.dataprovider.reader.portrait.CalcTablePortraitStandardDataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Example_TestDataSets_UnitTest {

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public void test(
			final AbstractCalcTableDataReader reader,
			final String workbookFilePath,
			final String sheetName,
			final List<Person> expectedResult
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
			final List<Person> result = reader.readData(
				sheet,
				Person.class,
				messageContainer
			);

			// ... verify post-conditions
			System.out.println("# Actual result:");
			System.out.println(result);

			System.out.println("# Expected result:");
			System.out.println(expectedResult);
			assertThat(result).containsExactlyElementsOf(expectedResult);

			System.out.println("# Actual error messages:");
			System.out.println(messageContainer);
			assertThat(messageContainer).isEmpty();
		}
	}

	// ... test data provider

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {

				{
						new CalcTableLandscapeStandardDataReader(),
						EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_LANDSCAPE_FORMAT,
						EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA,
						EXPECTED__TEST_DATA_SETS__PERSON_DATA
				},
				{
						new CalcTablePortraitStandardDataReader(),
						EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_PORTRAIT_FORMAT,
						EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA,
						EXPECTED__TEST_DATA_SETS__PERSON_DATA
				},
		};
	}

}
