package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model;

public class Person
		extends
		AbstractDomainObject
{

	// ... properties

	private PersonalData personalData;

	private ContactData contactData;

	// ... constructors

	public Person() {

		super();
	}

	public static Person of(
			final PersonalData personalData,
			final ContactData contactData
	)
	{

		final Person result = new Person();

		result.personalData = personalData;
		result.contactData = contactData;

		return result;
	}

	// ... getter/setter methods

	public PersonalData getPersonalData() {

		return personalData;
	}

	public void setPersonalData(final PersonalData personalData) {

		this.personalData = personalData;
	}

	public ContactData getContactData() {

		return contactData;
	}

	public void setContactData(final ContactData contactData) {

		this.contactData = contactData;
	}

}
