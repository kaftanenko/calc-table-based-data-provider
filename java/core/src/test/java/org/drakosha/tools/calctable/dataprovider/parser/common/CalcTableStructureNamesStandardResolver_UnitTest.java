package org.drakosha.tools.calctable.dataprovider.parser.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.drakosha.tools.calctable.dataprovider.parser.CalcTableStructureNamesResolver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

class CalcTableStructureNamesStandardResolver_UnitTest {

	// ... dependencies

	private final CalcTableStructureNamesResolver serviceUnderTest = //
			CalcTableStructureNamesStandardResolver.INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS;

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test_ParseStructureArea(
			final String structureName,
			final String expectedPropertyName
	)
			throws Exception
	{

		// ... call service under test
		final String resultPropertyName = this.serviceUnderTest.resolvePropertyName(
			structureName
		);

		// ... verify post-conditions
		assertThat(
			resultPropertyName
		).isEqualTo(
			expectedPropertyName
		);
	}

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
				{
						"some domain name",
						"someDomainName"
				},
				{
						"some_domain_name",
						"someDomainName"
				},
				{
						"some__domain___name",
						"someDomainName"
				},
				{
						"Some Domain Name (part to ignore)",
						"someDomainName"
				},
				{
						"§%$ Some Domain Name with special §%$ characters",
						"someDomainNameWithSpecialCharacters"
				},
		};
	}

}
