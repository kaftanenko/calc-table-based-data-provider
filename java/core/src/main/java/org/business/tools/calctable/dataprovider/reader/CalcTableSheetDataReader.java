package org.business.tools.calctable.dataprovider.reader;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface CalcTableSheetDataReader {

	// ... business methods

	<DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataRecordType,
			final List<RuntimeException> messageContainer
	);

}
