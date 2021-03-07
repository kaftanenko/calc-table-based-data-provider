package org.business.tools.calctable.dataprovider.reader.landscape;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableLandscapeDataParser;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableLandscapeStructureParser;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableDataReader;

public class CalcTableLandscapeDataReader
		extends
		AbstractCalcTableDataReader
{

	// ... properties

	private final CalcTableLandscapeStructureParser structureParser;

	private final CalcTableLandscapeDataParser dataParser;

	// ... constructors

	public CalcTableLandscapeDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		super(config);

		this.structureParser = new CalcTableLandscapeStructureParser();

		final CalcTableDataParserConfig dataParserConfig = //
				new CalcTableDataParserConfig(
					config.getPrimitiveValueParser(),
					config.getStructureNamesResolver()
				);
		this.dataParser = new CalcTableLandscapeDataParser(dataParserConfig);
	}

	// ... business methods

	@Override
	public <DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataItemType,
			final List<RuntimeException> messageContainer
	)
	{

		final List<CalcTableStructureNode> structureDescription = parseStructureArea(sheet);

		final List<DATA_TYPE> result = dataParser.parseDataArea(
			sheet,
			dataItemType,
			structureDescription,
			messageContainer
		);

		return result;
	}

	private List<CalcTableStructureNode> parseStructureArea(
			final Sheet sheet
	)
	{

		final CalcTableCellsDimension structureAreaDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
			sheet,
			this.config.getHeaderCellPredicate()
		);

		final List<CalcTableStructureNode> firstLevelStructureNodes = structureParser.parseStructureArea(
			sheet,
			structureAreaDimension
		);
		return firstLevelStructureNodes;
	}

}
