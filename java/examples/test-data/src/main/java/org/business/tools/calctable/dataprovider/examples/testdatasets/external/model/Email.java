package org.business.tools.calctable.dataprovider.examples.testdatasets.external.model;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EEmailKind;

public class Email
		extends
		AbstractDomainObject
{

	// ... properties

	private EEmailKind kind;

	private String address;

	// ... constructors

	public Email() {

		super();
	}

	public static Email of(
			final EEmailKind kind,
			final String address
	)
	{

		final Email result = new Email();

		result.kind = kind;
		result.address = address;

		return result;
	}

	// ... getter/setter methods

	public EEmailKind getKind() {

		return kind;
	}

	public void setKind(final EEmailKind kind) {

		this.kind = kind;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}

}
