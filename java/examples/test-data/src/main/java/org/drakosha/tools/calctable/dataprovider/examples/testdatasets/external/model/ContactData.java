package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model;

import java.util.List;

public class ContactData
		extends
		AbstractDomainObject
{

	// ... properties

	private List<PostalAddress> postalAddresses;

	private List<Phone> phones;

	private List<Email> emailAddresses;

	// ... constructors

	public ContactData() {

		super();
	}

	public static ContactData of(
			final List<PostalAddress> postalAddresses,
			final List<Phone> phones,
			final List<Email> emails
	)
	{

		final ContactData result = new ContactData();

		result.postalAddresses = postalAddresses;
		result.phones = phones;
		result.emailAddresses = emails;

		return result;
	}

	// ... getter/setter methods

	public List<PostalAddress> getPostalAddresses() {

		return postalAddresses;
	}

	public void setPostalAddresses(final List<PostalAddress> postalAddresses) {

		this.postalAddresses = postalAddresses;
	}

	public List<Phone> getPhones() {

		return phones;
	}

	public void setPhones(final List<Phone> phones) {

		this.phones = phones;
	}

	public List<Email> getEmailAddresses() {

		return emailAddresses;
	}

	public void setEmailAddresses(final List<Email> emails) {

		this.emailAddresses = emails;
	}

}
