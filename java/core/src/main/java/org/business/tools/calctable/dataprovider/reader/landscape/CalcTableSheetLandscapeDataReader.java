package org.business.tools.calctable.dataprovider.reader.landscape;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableSheetLandscapeDataParser;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableSheetLandscapeStructureParser;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableSheetDataReader;

/**
 * Sheet Data Reader for Calc table documents in landscape format.
 */
public class CalcTableSheetLandscapeDataReader
		extends
		AbstractCalcTableSheetDataReader
{

	// ... properties

	private final CalcTableSheetLandscapeStructureParser structureParser;

	private final CalcTableSheetLandscapeDataParser dataParser;

	// ... constructors

	public CalcTableSheetLandscapeDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		super(config);

		this.structureParser = new CalcTableSheetLandscapeStructureParser();

		final CalcTableDataParserConfig dataParserConfig = //
				new CalcTableDataParserConfig(
					config.getPrimitiveValueParser(),
					config.getStructureNamesResolver()
				);
		this.dataParser = new CalcTableSheetLandscapeDataParser(dataParserConfig);
	}

	// ... business methods

	@Override
	public <DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataRecordType,
			final List<RuntimeException> messageContainer
	)
	{

		final List<CalcTableStructureNode> structureDescription = parseStructureArea(sheet);

		final List<DATA_TYPE> result = dataParser.parseDataArea(
			sheet,
			dataRecordType,
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
			this.config.getHeaderCellSampler()
		);

		final List<CalcTableStructureNode> firstLevelStructureNodes = structureParser.parseStructureArea(
			sheet,
			structureAreaDimension
		);
		return firstLevelStructureNodes;
	}

}
