package org.business.tools.calctable.dataprovider.examples.testdatasets.external.model;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EPostalAddressKind;

public class PostalAddress
		extends
		AbstractDomainObject
{

	// ... properties

	private EPostalAddressKind kind;

	private String street2;

	private String street1;

	private String city;

	private String state;

	private int zip;

	private String country;

	// ... constructors

	public PostalAddress() {

		super();
	}

	public static PostalAddress of(
			final EPostalAddressKind kind,
			final String street2,
			final String street1,
			final String city,
			final String state,
			final int zip,
			final String country
	)
	{

		final PostalAddress result = new PostalAddress();

		result.kind = kind;
		result.street2 = street2;
		result.street1 = street1;
		result.city = city;
		result.state = state;
		result.zip = zip;
		result.country = country;

		return result;
	}

	// ... getter/setter methods

	public EPostalAddressKind getKind() {

		return kind;
	}

	public void setKind(final EPostalAddressKind kind) {

		this.kind = kind;
	}

	public String getStreet2() {

		return street2;
	}

	public void setStreet2(final String street2) {

		this.street2 = street2;
	}

	public String getStreet1() {

		return street1;
	}

	public void setStreet1(final String streetOrPOBox) {

		this.street1 = streetOrPOBox;
	}

	public String getCity() {

		return city;
	}

	public void setCity(final String city) {

		this.city = city;
	}

	public String getState() {

		return state;
	}

	public void setState(final String state) {

		this.state = state;
	}

	public int getZip() {

		return zip;
	}

	public void setZip(final int zip) {

		this.zip = zip;
	}

	public String getCountry() {

		return country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

}
