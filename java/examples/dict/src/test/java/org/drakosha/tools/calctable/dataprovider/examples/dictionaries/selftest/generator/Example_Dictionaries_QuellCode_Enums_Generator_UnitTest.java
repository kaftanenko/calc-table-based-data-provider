package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;

import org.drakosha.tools.calctable.dataprovider.common.util.ResourceUtils;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator.Example_Dictionaries_QuellCode_Enums_Generator;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest.Example_Dictionaries_TestDataFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Example_Dictionaries_QuellCode_Enums_Generator_UnitTest {

	// ... constants

	private static final String EXPECTED_TEXT__RESSOURCE_PATH__E_SOME_DICTIONARY_JAVA = //
			"/expected-results/expected_ESomeDictionary.java";

	private static final String EXPECTED_TEXT__RESSOURCE_PATH__E_SOME_OTHER_DICTIONARY_JAVA = //
			"/expected-results/expected_ESomeOtherDictionary.java";

	// ... dependencies

	private final Example_Dictionaries_QuellCode_Enums_Generator serviceUnderTest = //
			new Example_Dictionaries_QuellCode_Enums_Generator();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test_ParseDictionaryFile_Succeeds(
			final Dictionary dictionaryData,
			final String expectedGeneratedContent
	)
			throws Exception
	{

		// ... prepare test data
		final String enumPackageName = "org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generated.type";
		final StringWriter serviceOutputWriter = new StringWriter();

		// ... call service under test
		serviceUnderTest.generate(
			dictionaryData,
			enumPackageName,
			serviceOutputWriter
		);

		// ... verify post conditions
		assertThat(serviceOutputWriter.toString()).isEqualTo(expectedGeneratedContent);
	}

	@DataProvider
	public Object[][] dataProvider() {

		return new Object[][] {
				{
						Example_Dictionaries_TestDataFactory.EXPECTED__TEST_DATA__SOME_DICTIONARY,
						ResourceUtils.getResourceText(EXPECTED_TEXT__RESSOURCE_PATH__E_SOME_DICTIONARY_JAVA)
				},
				{
						Example_Dictionaries_TestDataFactory.EXPECTED__TEST_DATA__SOME_OTHER_DICTIONARY,
						ResourceUtils.getResourceText(EXPECTED_TEXT__RESSOURCE_PATH__E_SOME_OTHER_DICTIONARY_JAVA)
				},
		};
	}
}
