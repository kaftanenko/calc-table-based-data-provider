package org.business.tools.calctable.dataprovider.reader.portrait;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardSampler;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableSheetLandscapeStructureParser;
import org.business.tools.calctable.dataprovider.parser.portrait.CalcTableSheetPortraitDataParser;
import org.business.tools.calctable.dataprovider.parser.portrait.CalcTableSheetPortraitStructureParser;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableSheetDataReader;

/**
 * Sheet Data Reader for Calc table documents in portrait format.
 */
public class CalcTableSheetPortraitDataReader
		extends
		AbstractCalcTableSheetDataReader
{

	// ... properties

	private final CalcTableSheetPortraitStructureParser structureParser;

	private final CalcTableSheetPortraitDataParser dataParser;

	// ... constructors

	public CalcTableSheetPortraitDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		super(config);

		this.structureParser = new CalcTableSheetPortraitStructureParser();

		final CalcTableDataParserConfig dataParserConfig = //
				new CalcTableDataParserConfig(
					config.getPrimitiveValueParser(),
					config.getStructureNamesResolver()
				);
		this.dataParser = new CalcTableSheetPortraitDataParser(dataParserConfig);
	}

	// ... business methods

	@Override
	public <DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataRecordType,
			final List<RuntimeException> messageContainer
	)
	{

		final CalcTableCellsDimension headerDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
			sheet,
			CalcTableHeaderCellStandardSampler.INSTANCE__NON_TRANSPARENT_AND_NON_WHITE_BACKGROUND
		);
		final List<
				CalcTableStructureNode> headerDescription = new CalcTableSheetLandscapeStructureParser().parseStructureArea(
					sheet,
					headerDimension
				);
		final CalcTableCellsDimension headerFirstCellDimension = headerDescription.get(0).getInnerDimension();

		final int firstRowNum = headerDimension.getRow() + headerDimension.getRowSpan();
		final int firstStructureColumnNum = headerFirstCellDimension.getColumn();
		final int firstDataColumnNum = headerFirstCellDimension.getColumn() + headerFirstCellDimension.getColumnSpan();

		final CalcTableCellsDimension dataAreaDimension = CalcTableCellsDimension.of(
			firstRowNum,
			firstDataColumnNum,
			sheet.getLastRowNum() - firstRowNum + 1,
			headerDimension.getColumnSpan() - firstDataColumnNum + firstStructureColumnNum
		);

		final List<CalcTableStructureNode> structureDescription = parseStructureArea(sheet);

		final List<DATA_TYPE> result = dataParser.parseDataArea(
			sheet,
			dataRecordType,
			dataAreaDimension,
			structureDescription,
			messageContainer
		);

		return result;
	}

	public List<CalcTableStructureNode> parseStructureArea(
			final Sheet sheet
	)
	{

		final CalcTableCellsDimension headerDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
			sheet,
			config.getHeaderCellSampler()
		);
		final List<
				CalcTableStructureNode> headerDescription = new CalcTableSheetLandscapeStructureParser().parseStructureArea(
					sheet,
					headerDimension
				);
		final CalcTableCellsDimension headerFirstCellDimension = headerDescription.get(0).getInnerDimension();

		final int firstRowNum = headerDimension.getRow() + headerDimension.getRowSpan();
		final int firstStructureColumnNum = headerFirstCellDimension.getColumn();
		final int firstDataColumnNum = headerFirstCellDimension.getColumn() + headerFirstCellDimension.getColumnSpan();

		final CalcTableCellsDimension structureAreaDimension = CalcTableCellsDimension.of(
			firstRowNum,
			firstStructureColumnNum,
			sheet.getLastRowNum() - firstRowNum + 1,
			firstDataColumnNum - firstStructureColumnNum
		);

		final List<CalcTableStructureNode> firstLevelStructureNodes = structureParser.parseStructureArea(
			sheet,
			structureAreaDimension
		);
		return firstLevelStructureNodes;
	}

}
