package org.drakosha.tools.calctable.dataprovider.reader.portrait;

import java.util.Locale;

import org.drakosha.tools.calctable.dataprovider.reader.common.CalcTableDataStandardReaderConfig;

/**
 * The standard Sheet Data Reader for Calc table documents in portrait format.
 */
public class CalcTableSheetPortraitStandardDataReader
		extends
		CalcTableSheetPortraitDataReader
{

	// ... constructors

	public CalcTableSheetPortraitStandardDataReader(final Locale locale) {

		super(new CalcTableDataStandardReaderConfig(locale));
	}

}
