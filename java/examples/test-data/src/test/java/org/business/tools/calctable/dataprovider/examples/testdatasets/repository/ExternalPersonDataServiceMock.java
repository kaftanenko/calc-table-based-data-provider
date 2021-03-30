package org.business.tools.calctable.dataprovider.examples.testdatasets.repository;

import static org.business.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_LANDSCAPE_FORMAT;
import static org.business.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA;

import java.util.List;
import java.util.stream.Collectors;

import org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.Person;
import org.business.tools.calctable.dataprovider.examples.testdatasets.external.service.ExternalPersonDataService;

public class ExternalPersonDataServiceMock
		extends
		AbstractCalcTableDrivenDataRepository<Person>
		implements
		ExternalPersonDataService
{

	// ... business methods

	@Override
	public List<Person> findByName(
			final String firstName,
			final String lastName
	)
	{

		return getDataItems().stream().filter(
			e -> lastName.equals(e.getPersonalData().getLastName()) && firstName.equals(e.getPersonalData().getFirstName())
		).collect(
			Collectors.toList()
		);
	}

	// ... configuration methods

	@Override
	protected String getDataSourceFilePath() {

		return EXAMPLE_TEST_DATA_SETS__FILE_PATH__IN_LANDSCAPE_FORMAT;
	}

	@Override
	protected String getDataSourceSheetName() {

		return EXAMPLE_TEST_DATA_SETS__SHEET_NAME__PERSON_DATA;
	}

	@Override
	protected Class<Person> getDataItemType() {

		return Person.class;
	}

}
