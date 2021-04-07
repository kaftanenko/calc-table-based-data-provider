package org.business.tools.calctable.dataprovider.parser;

public class CalcTableDataParserConfig {

	// ... properties

	private final CalcTablePrimitiveValueParser primitiveValueParser;

	private final CalcTableStructureNamesResolver structureNamesResolver;

	// ... constructors

	public CalcTableDataParserConfig(
			final CalcTablePrimitiveValueParser primitiveValueParser,
			final CalcTableStructureNamesResolver structureNamesResolver
	)
	{

		this.primitiveValueParser = primitiveValueParser;
		this.structureNamesResolver = structureNamesResolver;
	}

	// ... getter methods

	public CalcTablePrimitiveValueParser getPrimitiveValueParser() {

		return primitiveValueParser;
	}

	public CalcTableStructureNamesResolver getStructureNamesResolver() {

		return structureNamesResolver;
	}
}
