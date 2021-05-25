package org.drakosha.tools.calctable.dataprovider.reader.common;

import java.util.Locale;

import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardSampler;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTablePrimitiveValueStandardParser;
import org.drakosha.tools.calctable.dataprovider.parser.common.CalcTableStructureNamesStandardResolver;
import org.drakosha.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;

/**
 * The standard implementation of the {@link CalcTableDataReaderConfig}.
 */
public class CalcTableDataStandardReaderConfig
		extends
		CalcTableDataReaderConfig
{

	// ... constructors

	public CalcTableDataStandardReaderConfig(final Locale locale) {

		super(
				CalcTableHeaderCellStandardSampler.INSTANCE__NON_TRANSPARENT_AND_NON_WHITE_BACKGROUND,
				new CalcTablePrimitiveValueStandardParser(locale),
				CalcTableStructureNamesStandardResolver.INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS
		);
	}

}
