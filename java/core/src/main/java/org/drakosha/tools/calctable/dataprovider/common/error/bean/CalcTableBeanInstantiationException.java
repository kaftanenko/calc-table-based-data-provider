package org.drakosha.tools.calctable.dataprovider.common.error.bean;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;

public class CalcTableBeanInstantiationException
		extends
		CalcTableException
{

	// ... constants

	private static final long serialVersionUID = 1L;

	// ... constructors

	public CalcTableBeanInstantiationException(
			final Class<?> beanClass,
			final Exception ex
	)
	{

		super(
			String.format(
				"The bean couldn't be instantiated: [bean: '%s'].",
				beanClass
			),
			ex
		);
	}

}
