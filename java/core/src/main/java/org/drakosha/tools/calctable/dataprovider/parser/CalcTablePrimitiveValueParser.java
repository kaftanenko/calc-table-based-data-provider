package org.drakosha.tools.calctable.dataprovider.parser;

import org.apache.poi.ss.usermodel.Cell;

import java.util.List;
import java.util.Optional;

/**
 * Parser component responsible for parsing of data cell contents into the
 * values of corresponding data type.
 */
public interface CalcTablePrimitiveValueParser {

	// ... business methods

	boolean isApplicableTo(
			Class<?> targetDataType
	);

	<DATA_TYPE> Optional<DATA_TYPE> parseValue(
			Cell cell,
			Class<DATA_TYPE> targetDataType,
			List<RuntimeException> messageContainer
	);
}
