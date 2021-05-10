package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.beansnested;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class BeanNestedLevel_1_2
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private String propertyLevel121;

	private String propertyLevel122;

	// ... constructors

	public BeanNestedLevel_1_2() {

	}

	public BeanNestedLevel_1_2(
			final String propertyLevel121,
			final String propertyLevel122
	)
	{

		this.propertyLevel121 = propertyLevel121;
		this.propertyLevel122 = propertyLevel122;
	}

	// ... getter/setter methods

	public String getPropertyLevel121() {

		return propertyLevel121;
	}

	public void setPropertyLevel121(final String propertyLevel121) {

		this.propertyLevel121 = propertyLevel121;
	}

	public String getPropertyLevel122() {

		return propertyLevel122;
	}

	public void setPropertyLevel122(final String propertyLevel122) {

		this.propertyLevel122 = propertyLevel122;
	}

}
