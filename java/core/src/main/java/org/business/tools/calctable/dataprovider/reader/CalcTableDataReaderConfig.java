package org.business.tools.calctable.dataprovider.reader;

import org.business.tools.calctable.dataprovider.parser.CalcTableHeaderCellSampler;
import org.business.tools.calctable.dataprovider.parser.CalcTablePrimitiveValueParser;
import org.business.tools.calctable.dataprovider.parser.CalcTableStructureNamesResolver;

/**
 * Configuration class, primarily for Sheet Data Readers.
 */
public class CalcTableDataReaderConfig {

	// ... properties

	/**
	 * Function allowing to recognize header area cells within a Calc table sheet.
	 */
	private final CalcTableHeaderCellSampler headerCellSampler;

	/**
	 * Parser for primitive values contained in the cells within the Calc table
	 * sheet's data (values) area.
	 */
	private final CalcTablePrimitiveValueParser primitiveValueParser;

	/**
	 * Component allowing to convert texts within the cells of the data structure
	 * description area into the target data record attribute names and to
	 * recognize the comment cells respectively.
	 */
	private final CalcTableStructureNamesResolver structureNamesResolver;

	// ... constructors

	public CalcTableDataReaderConfig(
			final CalcTableHeaderCellSampler headerCellSampler,
			final CalcTablePrimitiveValueParser primitiveValueParser,
			final CalcTableStructureNamesResolver structureNamesResolver
	)
	{

		this.headerCellSampler = headerCellSampler;
		this.primitiveValueParser = primitiveValueParser;
		this.structureNamesResolver = structureNamesResolver;
	}

	// ... getter methods

	public CalcTableHeaderCellSampler getHeaderCellSampler() {

		return headerCellSampler;
	}

	public CalcTablePrimitiveValueParser getPrimitiveValueParser() {

		return primitiveValueParser;
	}

	public CalcTableStructureNamesResolver getStructureNamesResolver() {

		return structureNamesResolver;
	}

}
