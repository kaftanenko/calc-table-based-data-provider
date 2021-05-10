package org.drakosha.tools.calctable.dataprovider.common.error.bean;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableBeanPropertyMissingException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableBeanPropertyMissingException(
			final Class<?> beanClass,
			final String propertyName,
			final String originalStructureCellText
	)
	{

		super(
			String.format(
				"There is no such bean property: [bean: '%s', property name: %s]. The original structure cell text: '%s'.",
				beanClass,
				propertyName,
				originalStructureCellText
			)
		);
	}

}
