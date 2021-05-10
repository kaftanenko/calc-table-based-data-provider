package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingPrimitiveBooleanTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private boolean primitive;

	private Boolean wrapper;

	// ... constructors

	public SupportingPrimitiveBooleanTypes() {

	}

	private SupportingPrimitiveBooleanTypes(
			final boolean primitive,
			final Boolean wrapper
	)
	{

		this.primitive = primitive;
		this.wrapper = wrapper;
	}

	public static SupportingPrimitiveBooleanTypes of(
			final boolean primitive,
			final Boolean wrapper
	)
	{

		return new SupportingPrimitiveBooleanTypes(
			primitive,
			wrapper
		);
	}

	// ... getter methods

	public boolean isPrimitive() {

		return primitive;
	}

	public void setPrimitive(
			final boolean primitive
	)
	{

		this.primitive = primitive;
	}

	public Boolean getWrapper() {

		return wrapper;
	}

	public void setWrapper(
			final Boolean wrapper
	)
	{

		this.wrapper = wrapper;
	}

}
