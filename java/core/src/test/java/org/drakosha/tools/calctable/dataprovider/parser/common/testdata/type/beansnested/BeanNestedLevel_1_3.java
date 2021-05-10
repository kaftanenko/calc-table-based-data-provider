package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.beansnested;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class BeanNestedLevel_1_3
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private String propertyLevel131;

	private BeanNestedLevel_1_3_2 beanLevel132;

	// ... constructors

	public BeanNestedLevel_1_3() {

	}

	public BeanNestedLevel_1_3(
			final String propertyLevel131,
			final BeanNestedLevel_1_3_2 beanLevel132
	)
	{

		this.propertyLevel131 = propertyLevel131;
		this.beanLevel132 = beanLevel132;
	}

	// ... getter/setter methods

	public String getPropertyLevel131() {

		return propertyLevel131;
	}

	public void setPropertyLevel131(final String propertyLevel131) {

		this.propertyLevel131 = propertyLevel131;
	}

	public BeanNestedLevel_1_3_2 getBeanLevel132() {

		return beanLevel132;
	}

	public void setBeanLevel132(final BeanNestedLevel_1_3_2 beanLevel132) {

		this.beanLevel132 = beanLevel132;
	}

}
