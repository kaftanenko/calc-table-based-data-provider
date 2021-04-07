package org.business.tools.calctable.dataprovider.parser;

/**
 * Component responsible for conversion of original texts within the structure
 * description cells into the names of corresponding target data object
 * attributes and to recognize the comment cells respectively.
 */
public interface CalcTableStructureNamesResolver {

	boolean isComment(
			String structureNodeText
	);

	String resolvePropertyName(
			String structureNodeText
	);
}
