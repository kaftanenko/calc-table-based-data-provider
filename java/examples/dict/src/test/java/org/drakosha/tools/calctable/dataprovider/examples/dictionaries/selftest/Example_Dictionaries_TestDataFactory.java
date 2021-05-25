package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest;

import java.util.List;

import static org.drakosha.tools.calctable.dataprovider.common.util.CalcTableCollectionUtils.listOf;

import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.model.DictionaryEntry;

public interface Example_Dictionaries_TestDataFactory {

	// … constants

	String TEST_DATA_SOURCE__FILE_PATH__IN_LANDSCAPE_FORMAT = //
			Example_Dictionaries_Const.class.getResource(
				"/test-data-sources/Example_Dictionaries_TestDataSource_in_LandscapeFormat.xlsx"
			).getFile();

	Dictionary EXPECTED__TEST_DATA__SOME_DICTIONARY = //

			new Dictionary(
				"Some Dictionary",
				"\"Some Dictionary\" Description",
				listOf(
					new DictionaryEntry(
						1,
						"value_1",
						"Display Text 1",
						"\"value_1\" Description"
					),
					new DictionaryEntry(
						2,
						"value_2",
						"Display Text 2",
						"\"value_2\" Description"
					)
				)
			);

	Dictionary EXPECTED__TEST_DATA__SOME_OTHER_DICTIONARY = //

			new Dictionary(
				"Some other Dictionary",
				"\"Some other Dictionary\" Description",
				listOf(
					new DictionaryEntry(
						1,
						"other_value_1",
						"Other Display Text 1",
						"\"other_value_1\" Description"
					),
					new DictionaryEntry(
						2,
						"other_value_2",
						"Other Display Text 2",
						"\"other_value_2\" Description"
					),
					new DictionaryEntry(
						3,
						"other_value_3",
						"Other Display Text 3",
						"\"other_value_3\" Description"
					)
				)
			);

	List<Dictionary> EXPECTED__TEST_DATA = listOf(
		EXPECTED__TEST_DATA__SOME_DICTIONARY,
		EXPECTED__TEST_DATA__SOME_OTHER_DICTIONARY
	);
}
