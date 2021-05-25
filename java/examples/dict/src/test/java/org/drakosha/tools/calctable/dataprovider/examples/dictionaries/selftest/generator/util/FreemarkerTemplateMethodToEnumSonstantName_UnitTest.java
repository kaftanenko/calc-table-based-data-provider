package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.selftest.generator.util;

import static org.assertj.core.api.Assertions.assertThat;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator.util.FreemarkerTemplateMethodToEnumConstantName;

public class FreemarkerTemplateMethodToEnumSonstantName_UnitTest {

	// ... dependencies

	private final static FreemarkerTemplateMethodToEnumConstantName serviceUnderTest = new FreemarkerTemplateMethodToEnumConstantName();

	// ... test methods

	@Test(dataProvider = "dataProvider")
	void test(
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
						/* expectedResult */ "SOME_FREE_TEXT"
				},
				{
						/* value */ "some free text (comment must be ignored)",
						/* expectedResult */ "SOME_FREE_TEXT"
				},
				{
						/* value */ " = some $ free %& text (non-alpha-numeric signs must be ignored) /; ",
						/* expectedResult */ "SOME_FREE_TEXT"
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
