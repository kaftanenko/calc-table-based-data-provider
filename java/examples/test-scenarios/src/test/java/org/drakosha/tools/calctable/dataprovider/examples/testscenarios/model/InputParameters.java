package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model;

import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.EArithmeticOperation;

public class InputParameters
		extends
		AbstractDomainObject
{

	// ... properties

	private int argument1;

	private EArithmeticOperation operation;

	private int argument2;

	// ... constructors

	public InputParameters() {

		super();
	}

	public InputParameters(
			final int argument1,
			final EArithmeticOperation operation,
			final int argument2
	)
	{

		super();
		this.argument1 = argument1;
		this.operation = operation;
		this.argument2 = argument2;
	}

	// ... getter/setter methods

	public int getArgument1() {

		return argument1;
	}

	public void setArgument1(final int argument1) {

		this.argument1 = argument1;
	}

	public EArithmeticOperation getOperation() {

		return operation;
	}

	public void setOperation(final EArithmeticOperation operation) {

		this.operation = operation;
	}

	public int getArgument2() {

		return argument2;
	}

	public void setArgument2(final int argument2) {

		this.argument2 = argument2;
	}

}
