package org.business.tools.calctable.dataprovider.parser.landscape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.error.CalcTableException;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.common.AbstractCalcTableDataParser;

public class CalcTableLandscapeDataParser
		extends
		AbstractCalcTableDataParser
{

	// ... constructors

	public CalcTableLandscapeDataParser(
			final CalcTableDataParserConfig parserConfig
	)
	{

		super(parserConfig);
	}

	// ... business methods

	public <DATA_TYPE> List<DATA_TYPE> parseDataArea(
			final Sheet sheet,
			final Class<DATA_TYPE> dataItemType,
			final List<CalcTableStructureNode> structureDescription,
			final List<RuntimeException> messageContainer
	)
	{

		final CalcTableCellsDimension structureAreaDimension = determineStructureAreaDimension(
			structureDescription
		);

		final int startRowNum = structureAreaDimension.getRow() + structureAreaDimension.getRowSpan();
		final int finishRowNum = sheet.getLastRowNum();

		final int startColumnNum = structureDescription.get(0).getInnerDimension().getColumn();
		final CalcTableStructureNode rootStructureDescription = CalcTableStructureNode.of(
			"dataRecords",
			CalcTableCellsDimension.of(
				0,
				startColumnNum,
				1,
				1
			),
			structureDescription
		);

		return parseCollectionPropertyOfItemType(
			sheet,
			rootStructureDescription,
			dataItemType,
			startRowNum,
			finishRowNum,
			messageContainer
		);
	}

	private <DATA_TYPE> void parseStructureNode(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE parentDataBean,
			final int startRowNum,
			final int finishRowNum,
			final List<RuntimeException> messageContainer
	)
	{

		final Optional<String> propertyNameOptional = getBeanPropertyName(
			parentDataBean,
			structureNode,
			messageContainer
		);

		final String propertyName;
		if (propertyNameOptional.isPresent()) {
			propertyName = propertyNameOptional.get();
		} else {
			return;
		}

		final Class<?> propertyType = getBeanPropertyType(
			parentDataBean,
			propertyName
		);

		if (isPrimitiveType(propertyType)) {
			parsePrimitiveProperty(
				sheet,
				structureNode,
				parentDataBean,
				startRowNum,
				messageContainer
			);
		} else if (isCollectionType(propertyType)) {
			parseCollectionProperty(
				sheet,
				structureNode,
				parentDataBean,
				startRowNum,
				finishRowNum,
				messageContainer
			);
		} else {
			parseDataBeanProperty(
				sheet,
				structureNode,
				parentDataBean,
				startRowNum,
				finishRowNum,
				messageContainer
			);
		}
	}

	private <DATA_TYPE> void parsePrimitiveProperty(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE parentDataBean,
			final int rowNum,
			final List<RuntimeException> messageContainer
	)
	{

		final String propertyName = getBeanPropertyName(
			parentDataBean,
			structureNode,
			messageContainer
		).get();

		final Class<?> propertyType = getBeanPropertyType(
			parentDataBean,
			propertyName
		);

		final Optional<?> propertyValueOptional = parsePrimitivePropertyOfType(
			sheet,
			structureNode,
			propertyType,
			rowNum,
			messageContainer
		);

		propertyValueOptional.ifPresent(
			propertyValue -> setBeanPropertyValue(
				parentDataBean,
				propertyName,
				propertyValue,
				messageContainer
			)
		);
		// TODO: evtl. warning if no value
	}

	private Optional<?> parsePrimitivePropertyOfType(
			final Sheet sheet,
			final CalcTableStructureNode structureNode, // TODO: eliminate or merge
																									// with cell coords (romNum)
			final Class<?> propertyType,
			final int rowNum,
			final List<RuntimeException> messageContainer
	)
	{

		final int columnNum = structureNode.getInnerDimension().getColumn();
		final Cell cell = CalcTablePoiNavigationUtils.getCell(
			sheet,
			rowNum,
			columnNum
		);

		final Optional<?> propertyValueOptional = this.parserConfig.getPrimitiveValueParser().parseValue(
			cell,
			propertyType,
			messageContainer
		);
		return propertyValueOptional;
	}

	private <DATA_TYPE> void parseDataBeanProperty(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE dataBean,
			final int startRowNum,
			final int finishRowNum,
			final List<RuntimeException> messageContainer
	)
	{

		if (isFictivStructureNode(structureNode)) {
			return; // skip structure node handling
		}

		final Optional<String> propertyNameOptional = getBeanPropertyName(
			dataBean,
			structureNode,
			messageContainer
		);

		final String propertyName;
		if (propertyNameOptional.isPresent()) {
			propertyName = propertyNameOptional.get();
		} else {
			return;
		}

		final Class<?> propertyType = getBeanPropertyType(
			dataBean,
			propertyName
		);

		final Object originalPropertyValue = getBeanPropertyValue(
			dataBean,
			propertyName
		);
		final Optional<?> propertyValueOptional;
		if (originalPropertyValue == null) {
			propertyValueOptional = instantiateBean(
				propertyType,
				messageContainer
			);
		} else {
			propertyValueOptional = Optional.of(originalPropertyValue);
		}

		propertyValueOptional.ifPresent(propertyValue -> {

			structureNode.getChildStructureNodes().forEach(childStructureNode ->

			parseStructureNode(
				sheet,
				childStructureNode,
				propertyValue,
				startRowNum,
				finishRowNum,
				messageContainer
			)
			);

			setBeanPropertyValue(
				dataBean,
				propertyName,
				propertyValue,
				messageContainer
			);
		});
		// TODO evtl. warning for no value
	}

	private <DATA_TYPE> void parseCollectionProperty(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE parentDataBean,
			final int startRowNum,
			final int finishRowNum,
			final List<RuntimeException> messageContainer
	)
	{

		final String propertyName = getBeanPropertyName(
			parentDataBean,
			structureNode,
			messageContainer
		).get();

		final Optional<Class<?>> propertyItemTypeOptional = getBeanPropertyItemType(
			parentDataBean,
			propertyName,
			messageContainer
		);

		final Class<?> propertyType = getBeanPropertyType(
			parentDataBean,
			propertyName
		);

		final Object originalPropertyValue = getBeanPropertyValue(
			parentDataBean,
			propertyName
		);

		final Class<?> propertyItemType;
		if (propertyItemTypeOptional.isPresent()) {
			propertyItemType = propertyItemTypeOptional.get();
		} else {
			return;
		}

		final List newPropertyItemValues = parseCollectionPropertyOfItemType(
			sheet,
			structureNode,
			propertyItemType,
			startRowNum,
			finishRowNum,
			messageContainer
		);

		if (newPropertyItemValues.size() > 0) {

			if (propertyType.isArray()) {
				final Object[] propertyValueAsArray = toArray(newPropertyItemValues);
			} else {
				if (originalPropertyValue == null) {

					final Collection propertyValueAsProperCollType = toCollectionType(
						newPropertyItemValues,
						propertyType
					);
					setBeanPropertyValue(
						parentDataBean,
						propertyName,
						propertyValueAsProperCollType,
						messageContainer
					);
				} else {
					((Collection) originalPropertyValue).addAll(
						newPropertyItemValues
					);
				}
			}
		}
	}

	private <DATA_TYPE> List<DATA_TYPE> parseCollectionPropertyOfItemType(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final Class<DATA_TYPE> propertyItemType,
			final int startRowNum,
			final int finishRowNum,
			final List<RuntimeException> messageContainer
	)
	{

		final List<DATA_TYPE> result = new ArrayList<>();

		final int startColumnNum = structureNode.getInnerDimension().getColumn();
		final List<Integer> collectionItemsStartRowNums = getRowsWithNonEmptyCells(
			sheet,
			startRowNum,
			finishRowNum,
			startColumnNum
		);

		final int lastItemsRowNumIndex = collectionItemsStartRowNums.size() - 1;
		for (int i = 0; i <= lastItemsRowNumIndex; i++) {

			final int startItemRowNum = collectionItemsStartRowNums.get(i);
			final int finishItemRowNum = i == lastItemsRowNumIndex
					? finishRowNum
					: collectionItemsStartRowNums.get(i + 1) - 1;

			final Optional<?> propertyItemValueOptional;
			if (isPrimitiveType(propertyItemType)) {
				propertyItemValueOptional = parsePrimitivePropertyOfType(
					sheet,
					structureNode,
					propertyItemType,
					startItemRowNum,
					messageContainer
				);
			} else if (isCollectionType(propertyItemType)) {
				// TODO evtl. just a warning message
				throw CalcTableErrorHelper.handleFatalException("Collections of collections are not supported.");
			} else {

				final Optional<?> propertyDataBeanOptional = instantiateBean(
					propertyItemType,
					messageContainer
				);

				propertyDataBeanOptional.ifPresent(propertyDataBean -> {

					structureNode.getChildStructureNodes().forEach(
						childStructureNode -> parseStructureNode(
							sheet,
							childStructureNode,
							propertyDataBean,
							startItemRowNum,
							finishItemRowNum,
							messageContainer
						)
					);
				});
				// TODO evtl. warning if no value

				propertyItemValueOptional = propertyDataBeanOptional;
			}

			propertyItemValueOptional.ifPresent(propertyItemValue -> {
				result.add((DATA_TYPE) propertyItemValue);
			});
		}

		return result;
	}

	// ... helper methods

	private static CalcTableCellsDimension determineStructureAreaDimension(
			final List<CalcTableStructureNode> structureDescription
	)
	{

		final int lastRowNum = getLastRowNum(structureDescription);
		final int lastColumnNum = getLastColumnNum(structureDescription);

		final CalcTableStructureNode firstCalcTableStructureNode = structureDescription.get(0);
		final int firstRowNum = firstCalcTableStructureNode.getInnerDimension().getRow();
		final int firstColumnNum = firstCalcTableStructureNode.getInnerDimension().getColumn();

		return CalcTableCellsDimension.of(
			firstRowNum,
			firstColumnNum,
			lastRowNum - firstRowNum + 1,
			lastColumnNum - firstColumnNum + 1
		);
	}

	private static int getLastRowNum(final List<CalcTableStructureNode> structureNodes) {

		int lastRowNum = 0;

		for (final CalcTableStructureNode structureNode : structureNodes) {

			lastRowNum = Math.max(
				lastRowNum,
				getLastRowNum(structureNode)
			);
		}

		return lastRowNum;
	}

	private static int getLastRowNum(final CalcTableStructureNode structureNode) {

		if (structureNode.getChildStructureNodes().size() == 0) {

			return structureNode.getInnerDimension().getRow() + structureNode.getInnerDimension().getRowSpan() - 1;
		} else {
			return getLastRowNum(structureNode.getChildStructureNodes());
		}
	}

	private static int getLastColumnNum(final List<CalcTableStructureNode> structureNodes) {

		int lastColumnNum = 0;

		for (final CalcTableStructureNode structureNode : structureNodes) {

			lastColumnNum = Math.max(
				lastColumnNum,
				getLastColumnNum(structureNode)
			);
		}

		return lastColumnNum;
	}

	private static int getLastColumnNum(final CalcTableStructureNode structureNode) {

		if (structureNode.getChildStructureNodes().size() == 0) {

			return structureNode.getInnerDimension().getColumn() + structureNode.getInnerDimension().getColumnSpan() - 1;
		} else {
			return getLastColumnNum(structureNode.getChildStructureNodes());
		}
	}

	private List<Integer> getRowsWithNonEmptyCells(
			final Sheet sheet,
			final int startRowNum,
			final int finishRowNum,
			final int columnNum
	)
	{

		return IntStream.rangeClosed(
			startRowNum,
			finishRowNum
		).mapToObj(
			rowNum ->
			{
				try {
					final Cell cell = CalcTablePoiNavigationUtils.getCell(
						sheet,
						rowNum,
						columnNum
					);
					final Optional<String> cellValueAsStringOptional = this.parserConfig.getPrimitiveValueParser().parseValue(
						cell,
						String.class,
						new ArrayList<>()
					);
					if (cellValueAsStringOptional.isPresent()) {

						final String cellValueAsString = cellValueAsStringOptional.get();
						if (StringUtils.isNoneBlank(cellValueAsString)) {
							return Integer.valueOf(cell.getRowIndex());
						} else {
							return null;
						}
					} else {
						return null;
					}
				} catch (final CalcTableException ex) {
					return null;
				}
			}
		).filter(Objects::nonNull).collect(Collectors.toList());
	}

}
