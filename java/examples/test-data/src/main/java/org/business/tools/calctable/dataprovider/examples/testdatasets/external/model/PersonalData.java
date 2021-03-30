package org.business.tools.calctable.dataprovider.examples.testdatasets.external.model;

import java.time.LocalDate;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.type.EGender;

public class PersonalData
		extends
		AbstractDomainObject
{

	// ... properties

	private String sSN;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;

	private EGender gender;

	// ... constructors

	public PersonalData() {

		super();
	}

	public static PersonalData of(
			final String ssn,
			final String firstName,
			final String lastName,
			final LocalDate birthDate,
			final EGender gender
	)
	{

		final PersonalData result = new PersonalData();

		result.sSN = ssn;
		result.firstName = firstName;
		result.lastName = lastName;
		result.birthDate = birthDate;
		result.gender = gender;

		return result;
	}

	// ... getter/setter methods

	public String getsSN() {

		return sSN;
	}

	public void setsSN(final String ssn) {

		this.sSN = ssn;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(final String firstName) {

		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(final String lastName) {

		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {

		return birthDate;
	}

	public void setBirthDate(final LocalDate birthDate) {

		this.birthDate = birthDate;
	}

	public EGender getGender() {

		return gender;
	}

	public void setGender(final EGender gender) {

		this.gender = gender;
	}

}
