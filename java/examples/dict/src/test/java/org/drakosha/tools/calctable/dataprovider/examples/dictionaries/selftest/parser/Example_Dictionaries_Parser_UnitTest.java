package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import java.util.List;

import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.parser.Example_Dictionaries_CalcTable_Parser;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest.Example_Dictionaries_TestDataFactory;

class Example_Dictionaries_Parser_UnitTest {

	// ... dependencies

	private final Example_Dictionaries_CalcTable_Parser serviceUnderTest = new Example_Dictionaries_CalcTable_Parser();

	// ... test methods

	@Test
	void test_ParseDictionaryFile_Succeeds()
			throws Exception
	{

		// ... prepare test data
		final String filePath = Example_Dictionaries_TestDataFactory.TEST_DATA_SOURCE__FILE_PATH__IN_LANDSCAPE_FORMAT;
		final List<Dictionary> expectedResult = Example_Dictionaries_TestDataFactory.EXPECTED__TEST_DATA;

		// ... call service under test
		final List<Dictionary> result = serviceUnderTest.parseDictionaryFile(filePath);

		// ... verify post conditions
		assertThat(result).containsExactlyElementsOf(expectedResult);
	}
}
