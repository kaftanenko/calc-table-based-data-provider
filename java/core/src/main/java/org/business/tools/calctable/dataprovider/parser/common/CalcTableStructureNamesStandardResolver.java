package org.business.tools.calctable.dataprovider.parser.common;

import org.apache.commons.text.WordUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableStructureNamesResolver;

public class CalcTableStructureNamesStandardResolver
		implements
		CalcTableStructureNamesResolver
{

	// ... constants

	public static final CalcTableStructureNamesResolver INSTANCE__TO_CAMEL_CASE__IGNORING_PARENTHESES__AND__HASH_SIGN_PREFIXED_COMMENTS = new CalcTableStructureNamesStandardResolver();

	// ... constructors

	protected CalcTableStructureNamesStandardResolver() {

		super();
	}

	// ... business methods

	@Override
	public boolean isComment(final String structureName) {

		return structureName.matches("\\s*#.*");
	}

	@Override
	public String resolvePropertyName(
			final String structureName
	)
	{

		String tmpResult = structureName;
		tmpResult = tmpResult.replaceAll(
			"\\(.*\\)",
			" "
		);
		tmpResult = tmpResult.replaceAll(
			"[^A-Za-z0-9]+",
			" "
		);
		tmpResult = WordUtils.capitalize(
			tmpResult
		);
		tmpResult = tmpResult.replaceAll(
			" +",
			""
		);
		tmpResult = WordUtils.uncapitalize(
			tmpResult
		);
		return tmpResult;
	}

}
