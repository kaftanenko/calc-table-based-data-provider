package org.business.tools.calctable.dataprovider.parser;

import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;

public interface CalcTablePrimitiveValueParser {

	// ... business methods

	boolean isApplicableTo(
			Class<?> propertyType
	);

	<DATA_TYPE> Optional<DATA_TYPE> parseValue(
			Cell cell,
			Class<DATA_TYPE> propertyType,
			List<RuntimeException> messageContainer
	);

}
