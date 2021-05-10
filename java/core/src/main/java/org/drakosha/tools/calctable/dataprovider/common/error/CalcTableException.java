package org.drakosha.tools.calctable.dataprovider.common.error;

public class CalcTableException
		extends
		RuntimeException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableException(
			final String message
	)
	{

		super(
			message
		);
	}

	public CalcTableException(
			final Throwable cause
	)
	{

		super(
			cause
		);
	}

	public CalcTableException(
			final String message,
			final Throwable cause
	)
	{

		super(
			message,
			cause
		);
	}

}
