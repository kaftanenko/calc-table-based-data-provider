package org.drakosha.tools.calctable.dataprovider.parser.portrait;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;

import org.drakosha.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTablePrimitiveValueStandardParser;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTableStructureNamesStandardResolver;

abstract class AbstractCalcTablePortrait_UnitTest {

	// ... constants: test files

	protected final String TEST_DATA_SOURCE__PORTRAIT__DATA_PARSER__FILE_PATH = this
			.getClass()
			.getResource(
					"/UnitTest_CalcTable_Portrait_DataParser.xlsx"
			)
			.getFile();

	protected final String UNIT_TEST__SAMPLE_XLS_FILE__STRUCTURE_PARSER = this
			.getClass()
			.getResource(
					"/UnitTest_CalcTable_Portrait_StructureParser.xlsx"
			)
			.getFile();

	protected final Locale UNIT_TEST__PARSER_PARAMETER__LOCALE__US = Locale.US;

	// ... constants: test data

	final int FIRST_ROW_NUM = 2;

	final int FIRST_STRUCTURE_COLUMN_NUM = 1;

	final int FIRST_DATA_COLUMN_NUM = 20;

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
		assertThat(messageContainer)
				.isEmpty();
	}

}
