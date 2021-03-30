package org.business.tools.calctable.dataprovider.examples.testdatasets.external.service;

import java.util.List;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;

public interface ExternalPersonDataService {

	List<Person> findByName(
			final String firstName,
			final String lastName
	);

}
