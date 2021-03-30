package org.business.tools.calctable.dataprovider.examples.dictionaries.model;

public class DictionaryEntry
		extends
		AbstractDomainObject
{

	// ... properties

	private int order;

	private String value;

	private String displayText;

	private String description;

	// ... constructors

	public DictionaryEntry() {

		super();
	}

	public DictionaryEntry(
			final int order,
			final String value,
			final String displayText,
			final String description
	)
	{

		super();
		this.order = order;
		this.value = value;
		this.displayText = displayText;
		this.description = description;
	}

	// ... getter/setter methods

	public int getOrder() {

		return order;
	}

	public void setOrder(final int order) {

		this.order = order;
	}

	public String getValue() {

		return value;
	}

	public void setValue(final String value) {

		this.value = value;
	}

	public String getDisplayText() {

		return displayText;
	}

	public void setDisplayText(final String displayValue) {

		this.displayText = displayValue;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

}
