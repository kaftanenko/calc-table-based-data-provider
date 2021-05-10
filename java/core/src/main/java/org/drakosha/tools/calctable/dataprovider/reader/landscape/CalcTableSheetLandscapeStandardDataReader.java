package org.drakosha.tools.calctable.dataprovider.reader.landscape;

import java.util.Locale;

import org.drakosha.tools.calctable.dataprovider.reader.common.CalcTableDataStandardReaderConfig;

/**
 * The standard Sheet Data Reader for Calc table documents in landscape format.
 */
public class CalcTableSheetLandscapeStandardDataReader
		extends
		CalcTableSheetLandscapeDataReader
{

	// ... constructors

	public CalcTableSheetLandscapeStandardDataReader(final Locale locale) {

		super(new CalcTableDataStandardReaderConfig(locale));
	}

}
