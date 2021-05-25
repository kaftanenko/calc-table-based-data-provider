package org.drakosha.tools.calctable.dataprovider.examples.testdatasets;

import java.util.Locale;

public interface Example_TestDataSets_Const {

	// ... constants

	String EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_LANDSCAPE_FORMAT = Example_TestDataSets_Const.class
			.getResource(
					"/test-data-sources/Example_TestDataSets_PersonData_in_LandscapeFormat.xlsx"
			)
			.getFile();

	String EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_PORTRAIT_FORMAT = Example_TestDataSets_Const.class
			.getResource(
					"/test-data-sources/Example_TestDataSets_PersonData_in_PortraitFormat.xlsx"
			)
			.getFile();

	String EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA = "Person Data";

	Locale EXAMPLE_TEST_DATA_SETS__SHEET_DATA_READER__PARAMETER__LOCALE__US = Locale.US;

}
