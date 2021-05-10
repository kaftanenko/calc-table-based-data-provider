package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model;

import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EPhoneKind;

public class Phone
		extends
		AbstractDomainObject
{

	// ... properties

	private EPhoneKind kind;

	private String number;

	// ... constructors

	public Phone() {

		super();
	}

	public static Phone of(
			final EPhoneKind kind,
			final String number
	)
	{

		final Phone result = new Phone();

		result.kind = kind;
		result.number = number;

		return result;
	}

	// ... getter/setter methods

	public EPhoneKind getKind() {

		return kind;
	}

	public void setKind(final EPhoneKind kind) {

		this.kind = kind;
	}

	public String getNumber() {

		return number;
	}

	public void setNumber(final String number) {

		this.number = number;
	}

}
