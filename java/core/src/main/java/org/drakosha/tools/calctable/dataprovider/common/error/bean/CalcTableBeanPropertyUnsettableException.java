package org.drakosha.tools.calctable.dataprovider.common.error.bean;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableBeanPropertyUnsettableException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableBeanPropertyUnsettableException(
			final Class<?> beanClass,
			final String propertyName,
			final Object propertyValue,
			final Exception ex
	)
	{

		super(
			String.format(
				"The bean property [bean: '%s', property name: '%s'] couldn't be set to the value '%s'.",
				beanClass,
				propertyName,
				propertyValue
			),
			ex
		);
	}

}
