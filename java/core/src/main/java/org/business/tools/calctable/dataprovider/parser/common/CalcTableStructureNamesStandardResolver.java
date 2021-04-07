package org.business.tools.calctable.dataprovider.parser.common;

import org.apache.commons.text.WordUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableStructureNamesResolver;

/**
 * The standard implementation of the {@link CalcTableStructureNamesResolver}.
 * <p>
 * Solves its tasks as follows:
 * <ul>
 * <li>recognizes cell text as a comment node in case it is prefixed with the
 * "#" sign.
 * <li>converts cell text into the camelCase format ignoring non-alphanumeric
 * signs and text parts in round brackets
 * </ul>
 */
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

	/**
	 * Recognize cell text as a comment node in case it is prefixed with the "#"
	 * sign.
	 *
	 * @param structureNodeText
	 *          original text from the structure description cell.
	 * @return true or false, see method description for further details.
	 */
	@Override
	public boolean isComment(final String structureNodeText) {

		return structureNodeText.matches("\\s*#.*");
	}

	/**
	 * Converts given cell text into the camelCase format. Accepts only
	 * alphanumeric symbols from the original text, all other symbols are
	 * interpreted as the word delimiter signs and will be removed. The method
	 * ignores either text parts within round brackets.
	 *
	 * @param structureNodeText
	 *          original text from the structure description cell.
	 * @return true or false, see method description for further details.
	 */
	@Override
	public String resolvePropertyName(
			final String structureNodeText
	)
	{

		String tmpResult = structureNodeText;
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
