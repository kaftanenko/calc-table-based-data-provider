package org.business.tools.calctable.dataprovider.reader.common;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;

public abstract class AbstractCalcTableDataReader {

	// ... properties

	protected final CalcTableDataReaderConfig config;

	// ... constructors

	public AbstractCalcTableDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		this.config = config;
	}

	// ... business methods

	public abstract <DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataItemType,
			final List<RuntimeException> messageContainer
	);

}
