package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model;

public class ExpectedErrorMessage
		extends
		AbstractDomainObject
{

	// ... properties

	private String text;

	// ... constructors

	public ExpectedErrorMessage() {

		super();
	}

	public ExpectedErrorMessage(final String text) {

		super();
		this.text = text;
	}

	// ... getter/setter methods

	public String getText() {

		return text;
	}

	public void setText(final String text) {

		this.text = text;
	}

}
