package org.business.tools.calctable.dataprovider.examples.dictionaries.selftest.generator.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.business.tools.calctable.dataprovider.examples.dictionaries.generator.util.FreemarkerTemplateMethodToEnumClassName;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerTemplateMethodToEnumClassName_UnitTest {

	// ... dependencies

	private final static FreemarkerTemplateMethodToEnumClassName serviceUnderTest = new FreemarkerTemplateMethodToEnumClassName();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test_Exec_Succeeds(
			final String value,
			final String expectedResult
	)
			throws Exception
	{

		// ... call service under test
		final TemplateModel result = serviceUnderTest.exec(Collections.singletonList(value));

		// ... verify post-conditions
		assertThat(result.toString()).isEqualTo(expectedResult);
	}

	@Test(
			dataProvider = "dataProvider_InvalidArguments",
			expectedExceptions = TemplateModelException.class,
			expectedExceptionsMessageRegExp = "An only argument of type 'String' is expected!"
	)
	void test_Exec_Fails_On_InvalidArguments(
			final List<Object> inputParameter
	)
			throws Exception
	{

		// ... call service under test
		serviceUnderTest.exec(inputParameter);
	}

	// ... data provider

	@DataProvider
	public Object[][] dataProvider() {

		return new Object[][] {
				{
						/* value */ "some free text",
						/* expectedResult */ "ESomeFreeText"
				},
				{
						/* value */ "some free text (comment must be ignored)",
						/* expectedResult */ "ESomeFreeText"
				},
				{
						/* value */ " = some $ free %& text (non-alpha-numeric signs must be ignored) /; ",
						/* expectedResult */ "ESomeFreeText"
				},
		};
	}

	@DataProvider
	public Object[][] dataProvider_InvalidArguments() {

		return new Object[][] {
				{
						Arrays.asList()
				},
				{
						Arrays.asList(
							"arg 1",
							"arg 2"
						)
				},
		};
	}
}
