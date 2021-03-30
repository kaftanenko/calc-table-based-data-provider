package org.business.tools.calctable.dataprovider.examples.testscenarios.model;

public class ExpectedOutput
		extends
		AbstractDomainObject
{

	// ... properties

	private Integer result;

	// ... constructors

	public ExpectedOutput() {

		super();
	}

	public ExpectedOutput(final Integer result) {

		super();
		this.result = result;
	}

	// ... getter/setter methods

	public Integer getResult() {

		return result;
	}

	public void setResult(final Integer result) {

		this.result = result;
	}

}
