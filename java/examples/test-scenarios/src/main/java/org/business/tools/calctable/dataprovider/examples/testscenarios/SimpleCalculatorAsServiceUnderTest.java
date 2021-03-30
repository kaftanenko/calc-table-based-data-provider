package org.business.tools.calctable.dataprovider.examples.testscenarios;

public class SimpleCalculatorAsServiceUnderTest {

	// ... business methods

	public int calculate(
			final int arg1,
			final EArithmeticOperation op,
			final int arg2
	)
	{

		switch (op) {
			case ADD:
				return arg1 + arg2;
			case SUBTRACT:
				return arg1 - arg2;
			case DIVIDE:
				return arg1 / arg2;
			case MULTIPLY:
				return arg1 * arg2;
			default:
				throw new RuntimeException(
					String.format(
						"Unsupported operation: %s.",
						op
					)
				);
		}
	}

}
