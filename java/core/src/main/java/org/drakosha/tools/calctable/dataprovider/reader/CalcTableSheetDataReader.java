package org.drakosha.tools.calctable.dataprovider.reader;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface CalcTableSheetDataReader {

	// ... business methods

	/**
	 * Reads data record sets of the given type from the given Calc table sheet.
	 * <p>
	 * It must be tried to process the whole sheet through to the finish despite
	 * potential parsing errors. Exceptions arising during the reading process
	 * must be accumulated within the given message container.
	 *
	 * @param <DATA_TYPE>
	 * @param sheet            the Calc table sheet to read from
	 * @param dataRecordType   the type of expected data records
	 * @param messageContainer container to accumulate exceptions had arisen during the reading
	 *                         process
	 * @return resulting data record sets
	 */
	<DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataRecordType,
			final List<RuntimeException> messageContainer
	);

}
