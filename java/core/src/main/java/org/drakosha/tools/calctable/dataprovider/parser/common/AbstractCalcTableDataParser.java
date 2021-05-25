package org.drakosha.tools.calctable.dataprovider.parser.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;
import org.drakosha.tools.calctable.dataprovider.common.error.bean.CalcTableBeanInstantiationException;
import org.drakosha.tools.calctable.dataprovider.common.error.bean.CalcTableBeanPropertyMissingException;
import org.drakosha.tools.calctable.dataprovider.common.error.bean.CalcTableBeanPropertyUnsettableException;
import org.drakosha.tools.calctable.dataprovider.common.error.bean.CalcTableCollectionItemTypeUnidentifiedException;
import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTableBeanUtils;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.drakosha.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;

public abstract class AbstractCalcTableDataParser {

	// ... properties

	protected final CalcTableDataParserConfig parserConfig;

	// ... constructors

	public AbstractCalcTableDataParser(
			final CalcTableDataParserConfig parserConfig
	)
	{

		this.parserConfig = parserConfig;
	}

	// ... helper methods

	protected <DATA_TYPE> Optional<String> getBeanPropertyName(
			final DATA_TYPE dataBean,
			final CalcTableStructureNode structureNode,
			final List<RuntimeException> messageContainer
	)
	{

		if (isCommentNode(structureNode)) {
			return Optional.empty();
		}

		final String structureNodeText = structureNode.getText();

		final String propertyName = this.parserConfig.getStructureNamesResolver().resolvePropertyName(
			structureNodeText
		);

		if (hasBeanProperty(
			dataBean,
			propertyName
		))
		{
			return Optional.of(
				propertyName
			);
		}

		final RuntimeException warningException = new CalcTableBeanPropertyMissingException(
			dataBean.getClass(),
			propertyName,
			structureNodeText
		);
		messageContainer.add(
			warningException
		);

		return Optional.empty();
	}

	protected <DATA_TYPE> Class<?> getBeanPropertyType(
			final DATA_TYPE dataBean,
			final String propertyName
	)
	{

		return CalcTableBeanUtils.getPropertyType(
			dataBean,
			propertyName
		);
	}

	protected <DATA_TYPE> Optional<Class<?>> getBeanPropertyItemType(
			final DATA_TYPE dataBean,
			final String propertyName,
			final List<RuntimeException> messageContainer
	)
	{

		try {
			return Optional.of(
				CalcTableBeanUtils.getPropertyItemType(
					dataBean,
					propertyName
				)
			);
		} catch (final Exception ex) {

			final RuntimeException warningException = new CalcTableCollectionItemTypeUnidentifiedException(
				dataBean.getClass(),
				propertyName,
				ex
			);
			messageContainer.add(
				warningException
			);
		}

		return Optional.empty();
	}

	protected <DATA_TYPE> Object getBeanPropertyValue(
			final DATA_TYPE dataBean,
			final String propertyName
	)
	{

		return CalcTableBeanUtils.getPropertyValue(
			dataBean,
			propertyName
		);
	}

	protected <DATA_TYPE> boolean hasBeanProperty(
			final DATA_TYPE dataBean,
			final String propertyName
	)
	{

		return CalcTableBeanUtils.hasProperty(
			dataBean,
			propertyName
		);
	}

	protected boolean isCellEmpty(
			final Sheet sheet,
			final int rowNum,
			final int columnNum
	)
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
			return !cellValueAsStringOptional.isPresent()
					|| StringUtils.isBlank(cellValueAsStringOptional.get());
		} catch (final CalcTableException ex) {
			return true;
		}
	}

	protected boolean isCellNoneEmpty(
			final Sheet sheet,
			final int rowNum,
			final int columnNum
	)
	{

		return !isCellEmpty(
			sheet,
			rowNum,
			columnNum
		);
	}

	protected <DATA_TYPE> Optional<DATA_TYPE> instantiateBean(
			final Class<DATA_TYPE> dataBeanType,
			final List<RuntimeException> messageContainer
	)
	{

		try {
			return Optional.of(
				CalcTableBeanUtils.instantiate(
					dataBeanType
				)
			);
		} catch (final Exception ex) {

			final RuntimeException warningException = new CalcTableBeanInstantiationException(
				dataBeanType,
				ex
			);
			messageContainer.add(
				warningException
			);
		}

		return Optional.empty();
	}

	protected boolean isCollectionType(
			final Class<?> propertyType
	)
	{

		return CalcTableBeanUtils.isCollectionType(
			propertyType
		);
	}

	protected boolean isCommentNode(
			final CalcTableStructureNode structureNode
	)
	{

		return parserConfig.getStructureNamesResolver().isComment(structureNode.getText());
	}

	protected boolean isPrimitiveType(
			final Class<?> propertyType
	)
	{

		return this.parserConfig.getPrimitiveValueParser().isApplicableTo(
			propertyType
		);
	}

	protected <DATA_TYPE> void setBeanPropertyValue(
			final DATA_TYPE dataBean,
			final String propertyName,
			final Object propertyValue,
			final List<RuntimeException> messageContainer
	)
	{

		try {
			CalcTableBeanUtils.setPropertyValue(
				dataBean,
				propertyName,
				propertyValue
			);
		} catch (final Exception ex) {

			final RuntimeException warningException = new CalcTableBeanPropertyUnsettableException(
				dataBean.getClass(),
				propertyName,
				propertyValue,
				ex
			);
			messageContainer.add(
				warningException
			);
		}
	}

	protected Object[] toArray(
			final List<?> collection
	)
	{

		final Object[] result = new Object[collection.size()];

		IntStream.range(
			0,
			collection.size()
		).forEach(
			i -> result[i] = collection.get(
				i
			)
		);

		return result;
	}

	protected <DATA_ITEM_TYPE> Collection<DATA_ITEM_TYPE> toCollectionType(
			final Collection<DATA_ITEM_TYPE> tempResultCollection,
			final Class<?> collectionPropertyType
	)
	{

		if (CalcTableBeanUtils.isTypeCompatibleWith(
			collectionPropertyType,
			List.class
		))
		{

			return new ArrayList<>(
				tempResultCollection
			);
		} else if (CalcTableBeanUtils.isTypeCompatibleWith(
			collectionPropertyType,
			Set.class
		))
		{

			return new HashSet<>(
				tempResultCollection
			);
		} else {
			throw CalcTableErrorHelper.handleUnsupportedValueType(
				collectionPropertyType
			);
		}
	}

}
