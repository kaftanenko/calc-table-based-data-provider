package org.drakosha.tools.calctable.dataprovider.parser.landscape;

import java.util.List;
import java.util.Locale;

import org.drakosha.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTablePrimitiveValueStandardParser;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTableStructureNamesStandardResolver;

abstract class AbstractCalcTableLandscape_UnitTest {

	// ... constants: test files

	protected final String TEST_DATA_SOURCE__LANDSCAPE__DATA_PARSER__FILE_PATH = this
			.getClass()
			.getResource(
					"/UnitTest_CalcTable_Landscape_DataParser.xlsx"
			)
			.getFile();

	protected final String UNIT_TEST__SAMPLE_XLS_FILE__STRUCTURE_PARSER = this
			.getClass()
			.getResource(
					"/UnitTest_CalcTable_Landscape_StructureParser.xlsx"
			)
			.getFile();

	protected final Locale UNIT_TEST__PARSER_PARAMETER__LOCALE__US = Locale.US;

	// ... constants: configurations

	protected final CalcTableDataParserConfig DATA_PARSER_STANDARD_CONFIG = //
			new CalcTableDataParserConfig(
					new CalcTablePrimitiveValueStandardParser(UNIT_TEST__PARSER_PARAMETER__LOCALE__US),
					CalcTableStructureNamesStandardResolver.INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS
			);

	// ... helper methods

	protected void verifyMessageContainer(
			final List<RuntimeException> messageContainer
	)
	{

		messageContainer.forEach(
				message -> System.out.println(
						message
				)
		);
	}

}
