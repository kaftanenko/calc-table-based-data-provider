package org.drakosha.tools.calctable.dataprovider.examples.testscenarios;

import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.AbstractDomainObject;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.ExpectedErrorMessage;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.ExpectedOutput;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.InputParameters;

public class Example_TestScenarios_SimpleCalculator_Record
		extends
		AbstractDomainObject
{

	// ... properties

	private InputParameters inputParameters;

	private ExpectedOutput expectedOutput;

	private ExpectedErrorMessage expectedErrorMessage;

	// ... constructors

	public Example_TestScenarios_SimpleCalculator_Record() {

		super();
	}

	public Example_TestScenarios_SimpleCalculator_Record(
			final InputParameters inputParameters,
			final ExpectedOutput expectedOutput,
			final ExpectedErrorMessage expectedErrorMessage
	)
	{

		super();
		this.inputParameters = inputParameters;
		this.expectedOutput = expectedOutput;
		this.expectedErrorMessage = expectedErrorMessage;
	}

	// ... getter/setter methods

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public ExpectedOutput getExpectedOutput() {

		return expectedOutput;
	}

	public void setExpectedOutput(final ExpectedOutput expectedOutput) {

		this.expectedOutput = expectedOutput;
	}

	public ExpectedErrorMessage getExpectedErrorMessage() {

		return expectedErrorMessage;
	}

	public void setExpectedErrorMessage(final ExpectedErrorMessage expectedErrorMessage) {

		this.expectedErrorMessage = expectedErrorMessage;
	}

}
