package org.business.tools.calctable.dataprovider.examples.testdatasets;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;
import org.business.tools.calctable.dataprovider.examples.testdatasets.external.service.ExternalPersonDataService;
import org.business.tools.calctable.dataprovider.examples.testdatasets.repository.ExternalPersonDataServiceMock;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MockitoTestNGListener.class)
public class Example_TestDataSets_PersonDataService_UnitTest {

	// ... dependencies

	@Spy
	private final ExternalPersonDataService externalPersonDataService = new ExternalPersonDataServiceMock();

	@InjectMocks
	private final PersonDataService serviceUnderTest = new PersonDataService();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	public void test_FindByNameAndBirthDate_Succeeds(
			final String firstName,
			final String lastName,
			final LocalDate birthDate,
			final int expectedHitsCount
	)
	{

		// call service under test
		final List<Person> result = serviceUnderTest.findByNameAndBirthDay(
			firstName,
			lastName,
			birthDate
		);

		// ... verify post conditions
		assertThat(result).isNotNull();
		assertThat(result).hasSize(expectedHitsCount);

		result.forEach(e -> {
			assertThat(e.getPersonalData().getFirstName()).isEqualTo(firstName);
			assertThat(e.getPersonalData().getLastName()).isEqualTo(lastName);
			assertThat(e.getPersonalData().getBirthDate()).isEqualTo(birthDate);
		});
	}

	@Test(
			dataProvider = "dataProvider_InvalidParameters",
			expectedExceptions = IllegalArgumentException.class,
			expectedExceptionsMessageRegExp = "Empty parameter values are not supported yet."
	)
	public void test_FindByNameAndBirthDate_Fails_On_InvalidParameters(
			final String firstName,
			final String lastName,
			final LocalDate birthDate
	)
	{

		// call service under test
		serviceUnderTest.findByNameAndBirthDay(
			firstName,
			lastName,
			birthDate
		);
	}

	// ... data provider methods

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				{
						"Some",
						"Unknown Person",
						LocalDate.of(
							1970,
							1,
							1
						),
						0 // expectedHitsCount
				},
				{
						"David R.",
						"Brown",
						LocalDate.of(
							1971,
							1,
							13
						),
						1 // expectedHitsCount
				},
				{
						"Jennifer J.",
						"Taveras",
						LocalDate.of(
							1974,
							2,
							9
						),
						1 // expectedHitsCount
				}
		};
	}

	@DataProvider
	private Object[][] dataProvider_InvalidParameters() {

		return new Object[][] {
				{
						null,
						"Unknown Person",
						LocalDate.of(
							1970,
							1,
							1
						)
				},
				{
						" ",
						"Unknown Person",
						LocalDate.of(
							1970,
							1,
							1
						)
				},
				{
						"Some",
						null,
						LocalDate.of(
							1970,
							1,
							1
						)
				},
				{
						"Some",
						" ",
						LocalDate.of(
							1970,
							1,
							1
						)
				},
				{
						"Some",
						"Unknown Person",
						null
				},
		};
	}

}
