package org.drakosha.tools.calctable.dataprovider.examples.testdatasets;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;
import org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.service.ExternalPersonDataService;

public class PersonDataService {

	// ... dependencies

	private ExternalPersonDataService externalPersonDataService;

	// ... business methods

	public List<Person> findByNameAndBirthDay(
			final String firstName,
			final String lastName,
			final LocalDate birthDate
	)
	{

		try {
			assertThat(firstName)
					.isNotBlank();
			assertThat(lastName)
					.isNotBlank();
			assertThat(birthDate)
					.isNotNull();
		} catch (final Throwable ex) {
			throw new IllegalArgumentException("Empty parameter values are not supported yet.");
		}

		return externalPersonDataService
				.findByName(
						firstName,
						lastName
				)
				.stream()
				.filter(e -> birthDate.equals(e
						.getPersonalData()
						.getBirthDate()))
				.collect(Collectors.toList());
	}

}
