package org.business.tools.calctable.dataprovider.parser;

public interface CalcTableStructureNamesResolver {

	boolean isComment(
			String structureName
	);

	String resolvePropertyName(
			String structureName
	);

}
