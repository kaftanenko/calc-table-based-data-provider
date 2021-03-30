package org.business.tools.calctable.dataprovider.examples.testscenarios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Const.EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.reader.portrait.CalcTablePortraitStandardDataReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Example_TestScenarios_SimpleCalculator_UnitTest {

	// ... dependencies

	private final SimpleCalculatorAsServiceUnderTest serviceUnderTest = new SimpleCalculatorAsServiceUnderTest();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public void test(
			final Example_TestScenarios_SimpleCalculator_Record testScenarioRecord
	)
	{

		// ... prepare test data

		// ... call service under test
		Integer actualResult;
		String actualErrorMessageText;
		try {
			actualResult = serviceUnderTest.calculate(
				testScenarioRecord.getInputParameters().getArgument1(),
				testScenarioRecord.getInputParameters().getOperation(),
				testScenarioRecord.getInputParameters().getArgument2()
			);
			actualErrorMessageText = null;
		} catch (final Exception ex) {
			actualResult = null;
			actualErrorMessageText = ex.getMessage();
		}

		// ... verify post-conditions
		assertThat(actualResult).isEqualTo(testScenarioRecord.getExpectedOutput().getResult());
		assertThat(actualErrorMessageText).isEqualTo(testScenarioRecord.getExpectedErrorMessage().getText());
	}

	// ... test data provider

	@DataProvider
	Object[][] dataProvider()
			throws Exception
	{

		final List<RuntimeException> messageContainer = new ArrayList<>();

		// ...

		try (final InputStream is = new FileInputStream(
			EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT
		);
				final Workbook workbook = new XSSFWorkbook(
					is
				))
		{
			final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES
			);

			final CalcTablePortraitStandardDataReader reader = new CalcTablePortraitStandardDataReader();

			return reader.readData(
				sheet,
				Example_TestScenarios_SimpleCalculator_Record.class,
				messageContainer
			).stream().map(e -> new Object[]
			{
					e
			}).collect(Collectors.toList()).toArray(new Object[0][0]);
		}
	}

}
