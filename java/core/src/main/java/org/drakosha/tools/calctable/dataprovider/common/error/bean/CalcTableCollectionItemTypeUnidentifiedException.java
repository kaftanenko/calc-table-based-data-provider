package org.drakosha.tools.calctable.dataprovider.common.error.bean;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableCollectionItemTypeUnidentifiedException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableCollectionItemTypeUnidentifiedException(
			final Class<?> beanClass,
			final String propertyName,
			final Exception ex
	)
	{

		super(
				String.format(
						"No type of a collection item could be identified: [bean: '%s', property name: %s].",
						beanClass,
						propertyName
				),
				ex
		);
	}

}
