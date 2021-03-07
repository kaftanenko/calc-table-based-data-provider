package org.business.tools.calctable.dataprovider.reader;

import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Cell;
import org.business.tools.calctable.dataprovider.parser.CalcTablePrimitiveValueParser;
import org.business.tools.calctable.dataprovider.parser.CalcTableStructureNamesResolver;

public class CalcTableDataReaderConfig {

	// ... properties

	private final Predicate<Cell> headerCellPredicate;

	private final CalcTablePrimitiveValueParser primitiveValueParser;

	private final CalcTableStructureNamesResolver structureNamesResolver;

	// ... constructors

	public CalcTableDataReaderConfig(
			final Predicate<Cell> headerCellPredicate,
			final CalcTablePrimitiveValueParser primitiveValueParser,
			final CalcTableStructureNamesResolver structureNamesResolver
	)
	{

		this.headerCellPredicate = headerCellPredicate;
		this.primitiveValueParser = primitiveValueParser;
		this.structureNamesResolver = structureNamesResolver;
	}

	// ... getter methods

	public Predicate<Cell> getHeaderCellPredicate() {

		return headerCellPredicate;
	}

	public CalcTablePrimitiveValueParser getPrimitiveValueParser() {

		return primitiveValueParser;
	}

	public CalcTableStructureNamesResolver getStructureNamesResolver() {

		return structureNamesResolver;
	}

}
