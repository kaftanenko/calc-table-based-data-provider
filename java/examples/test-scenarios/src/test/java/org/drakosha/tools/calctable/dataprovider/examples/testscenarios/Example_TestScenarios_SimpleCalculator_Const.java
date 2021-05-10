package org.drakosha.tools.calctable.dataprovider.examples.testscenarios;

import java.util.Locale;

public class Example_TestScenarios_SimpleCalculator_Const {

	// ... constants

	public static final String EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_LANDSCAPE_FORMAT = //
			Example_TestScenarios_SimpleCalculator_Const.class.getResource(
				"/test-scenarios/Example_TestScenarios_SimpleCalculator_in_LandscapeFormat.xlsx"
			).getFile();

	public static final String EXAMPLE_TEST_SCENARIOS__FILE_PATH__IN_PORTRAIT_FORMAT = //
			Example_TestScenarios_SimpleCalculator_Const.class.getResource(
				"/test-scenarios/Example_TestScenarios_SimpleCalculator_in_PortraitFormat.xlsx"
			).getFile();

	public static final String EXAMPLE_TEST_SCENARIOS__SHEET_NAME__TEST_CASES = "Test Cases";

	public static final Locale EXAMPLE_TEST_SCENARIOS__SHEET_DATA_READER__PARAMETER__LOCALE__US = Locale.US;

}
