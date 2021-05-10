package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingPrimitiveTextTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private String string;

	// ... constructors

	public SupportingPrimitiveTextTypes() {

	}

	private SupportingPrimitiveTextTypes(
			final String string
	)
	{

		this.string = string;
	}

	public static SupportingPrimitiveTextTypes of(
			final String string
	)
	{

		return new SupportingPrimitiveTextTypes(
			string
		);
	}

	// ... getter methods

	public String getString() {

		return string;
	}

	public void setString(
			final String string
	)
	{

		this.string = string;
	}

}
