package org.business.tools.calctable.dataprovider.common.util;

import org.business.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableErrorHelper {

	// ... business methods

	public static RuntimeException handleFatalException(
			final String message
	)
	{

		throw new CalcTableException(
			message
		);
	}

	public static RuntimeException handleFatalException(
			final Throwable cause
	)
	{

		throw new CalcTableException(
			cause
		);
	}

	public static RuntimeException handleFatalException(
			final String message,
			final Throwable cause
	)
	{

		throw new CalcTableException(
			message,
			cause
		);
	}

	public static RuntimeException handleNotImplementedYetException() {

		throw new CalcTableException(
			"... not implemented yet."
		);
	}

	public static RuntimeException handleUnsupportedValue(
			final Object value
	)
	{

		throw new CalcTableException(
			String.format(
				"The value is unsupported yet: '%s'.",
				value
			)
		);
	}

	public static RuntimeException handleUnsupportedValueType(
			final Class<?> type
	)
	{

		throw new CalcTableException(
			String.format(
				"The type is unsupported yet: '%s'.",
				type
			)
		);
	}

}
