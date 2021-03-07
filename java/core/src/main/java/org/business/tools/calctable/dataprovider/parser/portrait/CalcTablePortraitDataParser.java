package org.business.tools.calctable.dataprovider.parser.portrait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.business.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;
import org.business.tools.calctable.dataprovider.parser.common.AbstractCalcTableDataParser;

public class CalcTablePortraitDataParser
		extends
		AbstractCalcTableDataParser
{

	// ... properties

	final CalcTableDataParserConfig parserConfig;

	// ... constructors

	public CalcTablePortraitDataParser(
			final CalcTableDataParserConfig parserConfig
	)
	{

		super(parserConfig);
		this.parserConfig = parserConfig;
	}

	// ... business methods

	public <DATA_TYPE> List<DATA_TYPE> parseDataArea(
			final Sheet sheet,
			final Class<DATA_TYPE> dataItemType,
			final CalcTableCellsDimension dataAreaDimension,
			final List<CalcTableStructureNode> structureDescription,
			final List<RuntimeException> messageContainer
	)
	{

		final int startColumnNum = dataAreaDimension.getColumn();
		final int finishColumnNum = dataAreaDimension.getColumn() + dataAreaDimension.getColumnSpan() - 1;

		return IntStream.rangeClosed(
			startColumnNum,
			finishColumnNum
		).mapToObj(
			columnNum -> parseRootDataBean(
				sheet,
				structureDescription,
				dataItemType,
				columnNum,
				messageContainer
			)
		).collect(Collectors.toList());
	}

	private <DATA_TYPE> DATA_TYPE parseRootDataBean(
			final Sheet sheet,
			final List<CalcTableStructureNode> structureDescription,
			final Class<DATA_TYPE> dataBeanType,
			final int columnNum,
			final List<RuntimeException> messageContainer
	)
	{

		final Optional<DATA_TYPE> dataBeanOptional = instantiateBean(
			dataBeanType,
			messageContainer
		);

		dataBeanOptional.ifPresent(dataBean -> {

			structureDescription.forEach(structureNode -> {
				parseStructureNode(
					sheet,
					structureNode,
					dataBean,
					columnNum,
					messageContainer
				);
			});
		});
		// TODO evtl. warning for no value

		return dataBeanOptional.orElse(null);
	}

	private <DATA_TYPE> void parseStructureNode(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE parentDataBean,
			final int columnNum,
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
				columnNum,
				messageContainer
			);
		} else if (isCollectionType(propertyType)) {
			parseCollectionProperty(
				sheet,
				structureNode,
				parentDataBean,
				columnNum,
				messageContainer
			);
		} else {
			parseDataBeanProperty(
				sheet,
				structureNode,
				parentDataBean,
				columnNum,
				messageContainer
			);
		}
	}

	private <DATA_TYPE> void parsePrimitiveProperty(
			final Sheet sheet,
			final CalcTableStructureNode structureNode,
			final DATA_TYPE parentDataBean,
			final int columnNum,
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
			columnNum,
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
			final int columnNum,
			final List<RuntimeException> messageContainer
	)
	{

		final int rowNum = structureNode.getInnerDimension().getRow();
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
			final int columnNum,
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
				columnNum,
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
			final int columnNum,
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

		final Class<?> propertyItemType;
		if (propertyItemTypeOptional.isPresent()) {
			propertyItemType = propertyItemTypeOptional.get();
		} else {
			return;
		}

		final Class<?> propertyType = getBeanPropertyType(
			parentDataBean,
			propertyName
		);

		final Object originalPropertyValue = getBeanPropertyValue(
			parentDataBean,
			propertyName
		);

		final List newPropertyItemValues = new ArrayList<>();

		final Optional<?> propertyItemValueOptional;
		if (isPrimitiveType(propertyItemType)) {
			propertyItemValueOptional = parsePrimitivePropertyOfType(
				sheet,
				structureNode,
				propertyItemType,
				columnNum,
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
						columnNum,
						messageContainer
					)
				);
			});
			// TODO evtl. warning if no value

			propertyItemValueOptional = propertyDataBeanOptional;
		}

		propertyItemValueOptional.ifPresent(propertyItemValue -> {
			newPropertyItemValues.add(propertyItemValue);
		});

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

}
