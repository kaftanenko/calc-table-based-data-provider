package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.beansnested;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class BeanNestedType
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private BeanNestedLevel_1 beanLevel1;

	// ... constructors

	public BeanNestedType() {

	}

	public BeanNestedType(final BeanNestedLevel_1 beanLevel1) {

		this.beanLevel1 = beanLevel1;
	}

	// ... getter/setter methods

	public BeanNestedLevel_1 getBeanLevel1() {

		return beanLevel1;
	}

	public void setBeanLevel1(final BeanNestedLevel_1 beanLevel1) {

		this.beanLevel1 = beanLevel1;
	}

}
