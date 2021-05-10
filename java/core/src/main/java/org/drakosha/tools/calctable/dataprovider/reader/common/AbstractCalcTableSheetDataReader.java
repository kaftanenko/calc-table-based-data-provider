package org.drakosha.tools.calctable.dataprovider.reader.common;

import org.drakosha.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;
import org.drakosha.tools.calctable.dataprovider.reader.CalcTableSheetDataReader;

public abstract class AbstractCalcTableSheetDataReader
		implements
		CalcTableSheetDataReader
{

	// ... properties

	protected final CalcTableDataReaderConfig config;

	// ... constructors

	public AbstractCalcTableSheetDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		this.config = config;
	}

}
