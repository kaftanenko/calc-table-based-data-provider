package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.collection;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingCollectionItem
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private boolean booleanProperty;

	private int numericProperty;

	private String textProperty;

	// ... constructors

	public SupportingCollectionItem() {

	}

	private SupportingCollectionItem(
			final boolean booleanProperty,
			final int numericProperty,
			final String textProperty
	)
	{

		this.booleanProperty = booleanProperty;
		this.numericProperty = numericProperty;
		this.textProperty = textProperty;
	}

	public static SupportingCollectionItem of(
			final boolean booleanProperty,
			final int numericProperty,
			final String textProperty
	)
	{

		return new SupportingCollectionItem(
			booleanProperty,
			numericProperty,
			textProperty
		);
	}

	// ... getter/setter methods

	public boolean isBooleanProperty() {

		return booleanProperty;
	}

	public void setBooleanProperty(
			final boolean booleanProperty
	)
	{

		this.booleanProperty = booleanProperty;
	}

	public int getNumericProperty() {

		return numericProperty;
	}

	public void setNumericProperty(
			final int numericProperty
	)
	{

		this.numericProperty = numericProperty;
	}

	public String getTextProperty() {

		return textProperty;
	}

	public void setTextProperty(
			final String textProperty
	)
	{

		this.textProperty = textProperty;
	}

}
