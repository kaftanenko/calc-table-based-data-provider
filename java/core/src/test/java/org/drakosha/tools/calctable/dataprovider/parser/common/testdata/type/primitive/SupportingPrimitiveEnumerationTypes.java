package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import javax.lang.model.element.ElementKind;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingPrimitiveEnumerationTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private ElementKind original;

	private ECustomEnumeration withCustomValueOf;

	// ... constructors

	public SupportingPrimitiveEnumerationTypes() {

	}

	private SupportingPrimitiveEnumerationTypes(
			final ElementKind original,
			final ECustomEnumeration withCustomValueOf
	)
	{

		this.original = original;
		this.withCustomValueOf = withCustomValueOf;
	}

	public static SupportingPrimitiveEnumerationTypes of(
			final ElementKind original,
			final ECustomEnumeration withCustomValueOf
	)
	{

		return new SupportingPrimitiveEnumerationTypes(
				original,
				withCustomValueOf
		);
	}

	// ... getter methods

	public ElementKind getOriginal() {

		return original;
	}

	public void setOriginal(
			final ElementKind original
	)
	{

		this.original = original;
	}

	public ECustomEnumeration getWithCustomValueOf() {

		return withCustomValueOf;
	}

	public void setWithCustomValueOf(
			final ECustomEnumeration withCustomValueOf
	)
	{

		this.withCustomValueOf = withCustomValueOf;
	}

}
