package org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested;

import org.business.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class BeanNestedLevel_1
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private String propertyLevel11;

	private BeanNestedLevel_1_2 beanLevel12;

	private BeanNestedLevel_1_3 beanLevel13;

	// ... constructors

	public BeanNestedLevel_1() {

	}

	public BeanNestedLevel_1(
			final String propertyLevel1,
			final BeanNestedLevel_1_2 beanLevel12,
			final BeanNestedLevel_1_3 beanLevel13
	)
	{

		this.propertyLevel11 = propertyLevel1;
		this.beanLevel12 = beanLevel12;
		this.beanLevel13 = beanLevel13;
	}

	// ... getter/setter methods

	public String getPropertyLevel11() {

		return propertyLevel11;
	}

	public void setPropertyLevel11(final String propertyLevel11) {

		this.propertyLevel11 = propertyLevel11;
	}

	public BeanNestedLevel_1_2 getBeanLevel12() {

		return beanLevel12;
	}

	public void setBeanLevel12(final BeanNestedLevel_1_2 beanLevel12) {

		this.beanLevel12 = beanLevel12;
	}

	public BeanNestedLevel_1_3 getBeanLevel13() {

		return beanLevel13;
	}

	public void setBeanLevel13(final BeanNestedLevel_1_3 beanLevel13) {

		this.beanLevel13 = beanLevel13;
	}

}
