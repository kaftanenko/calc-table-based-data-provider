package org.business.tools.calctable.dataprovider.reader.portrait;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.common.CalcTableHeaderCellStandardPredicate;
import org.business.tools.calctable.dataprovider.parser.landscape.CalcTableLandscapeStructureParser;
import org.business.tools.calctable.dataprovider.parser.portrait.CalcTablePortraitDataParser;
import org.business.tools.calctable.dataprovider.parser.portrait.CalcTablePortraitStructureParser;
import org.business.tools.calctable.dataprovider.reader.CalcTableDataReaderConfig;
import org.business.tools.calctable.dataprovider.reader.common.AbstractCalcTableDataReader;

public class CalcTablePortraitDataReader
		extends
		AbstractCalcTableDataReader
{

	// ... properties

	private final CalcTablePortraitStructureParser structureParser;

	private final CalcTablePortraitDataParser dataParser;

	// ... constructors

	public CalcTablePortraitDataReader(
			final CalcTableDataReaderConfig config
	)
	{

		super(config);

		this.structureParser = new CalcTablePortraitStructureParser();

		final CalcTableDataParserConfig dataParserConfig = //
				new CalcTableDataParserConfig(
					config.getPrimitiveValueParser(),
					config.getStructureNamesResolver()
				);
		this.dataParser = new CalcTablePortraitDataParser(dataParserConfig);
	}

	// ... business methods

	@Override
	public <DATA_TYPE> List<DATA_TYPE> readData(
			final Sheet sheet,
			final Class<DATA_TYPE> dataItemType,
			final List<RuntimeException> messageContainer
	)
	{

		final CalcTableCellsDimension headerDimension = CalcTablePoiDataUtils.determineCellsAreaDimension(
			sheet,
			CalcTableHeaderCellStandardPredicate.INSTANCE__NON_TRANSPARENT_BACKGROUND
		);
		final List<CalcTableStructureNode> headerDescription = new CalcTableLandscapeStructureParser().parseStructureArea(
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
			dataItemType,
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
			config.getHeaderCellPredicate()
		);
		final List<CalcTableStructureNode> headerDescription = new CalcTableLandscapeStructureParser().parseStructureArea(
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
