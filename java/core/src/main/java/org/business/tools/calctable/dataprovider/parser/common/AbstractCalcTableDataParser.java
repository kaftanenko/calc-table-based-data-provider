package org.business.tools.calctable.dataprovider.parser.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.business.tools.calctable.dataprovider.common.error.bean.CalcTableBeanInstantiationException;
import org.business.tools.calctable.dataprovider.common.error.bean.CalcTableBeanPropertyMissingException;
import org.business.tools.calctable.dataprovider.common.error.bean.CalcTableBeanPropertyUnsettableException;
import org.business.tools.calctable.dataprovider.common.error.bean.CalcTableCollectionItemTypeUnidentifiedException;
import org.business.tools.calctable.dataprovider.common.type.CalcTableStructureNode;
import org.business.tools.calctable.dataprovider.common.util.CalcTableBeanUtils;
import org.business.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.business.tools.calctable.dataprovider.parser.CalcTableDataParserConfig;

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

		final String structureNodeText = structureNode.getText();

		if (this.parserConfig.getStructureNamesResolver().isComment(structureNodeText)) {
			return Optional.empty();
		}

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

	protected boolean isFictivStructureNode(
			final CalcTableStructureNode structureNode
	)
	{

		return structureNode.getText().startsWith("#");
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
