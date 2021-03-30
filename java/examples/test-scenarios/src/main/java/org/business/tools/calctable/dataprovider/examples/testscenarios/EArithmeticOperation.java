package org.business.tools.calctable.dataprovider.examples.testscenarios;

import java.util.Arrays;

import org.business.tools.calctable.dataprovider.examples.testscenarios.util.EnumUtils;

public enum EArithmeticOperation {

	// ... constants

	ADD("+"),
	SUBTRACT("-"),
	MULTIPLY("*"),
	DIVIDE("/"),
	;

	// ... properties

	private String sign;

	// ... constructors

	EArithmeticOperation(final String sign) {

		this.sign = sign;

		EnumUtils.overrideValueOfBehaviour(
			this,
			sign
		);
	}

	public static EArithmeticOperation findBySign(final String sign) {

		return Arrays.asList(EArithmeticOperation.values()).stream().filter(
			e -> e.sign.equals(sign)
		).findFirst().orElseThrow(
			() -> new RuntimeException(
				String.format(
					"Operation '%s' is not supported yet.",
					sign
				)
			)
		);
	}

}
