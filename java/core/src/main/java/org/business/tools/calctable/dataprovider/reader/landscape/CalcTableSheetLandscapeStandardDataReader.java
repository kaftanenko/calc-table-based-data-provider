package org.business.tools.calctable.dataprovider.reader.landscape;

import java.util.Locale;

import org.business.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardPredicate;
import org.business.tools.calctable.dataprovider.parser.common.CalcTablePrimitiveValueStandardParser;
import org.business.tools.calctable.dataprovider.parser.common.CalcTableStructureNamesStandardResolver;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;

public class CalcTableSheetLandscapeStandardDataReader
		extends
		CalcTableSheetLandscapeDataReader
{

	// ... constructors

	public CalcTableSheetLandscapeStandardDataReader(final Locale locale) {

		super(
			new CalcTableDataReaderConfig(
				CalcTableHeaderCellStandardPredicate.INSTANCE__NON_TRANSPARENT_BACKGROUND,
				new CalcTablePrimitiveValueStandardParser(locale),
				CalcTableStructureNamesStandardResolver.INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS
			)
		);
	}

}
