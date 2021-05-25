package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generated.type;

/**
 * Enumeration representation of the dictionary 'Some other Dictionary'.
 * <p>
 * Description: "Some other Dictionary" Description
 */
public enum ESomeOtherDictionary {

	// ... value constants

	OTHER_DISPLAY_TEXT_1(
			/* value: */ "other_value_1",
			/* display text: */ "Other Display Text 1"
	),
	OTHER_DISPLAY_TEXT_2(
			/* value: */ "other_value_2",
			/* display text: */ "Other Display Text 2"
	),
	OTHER_DISPLAY_TEXT_3(
			/* value: */ "other_value_3",
			/* display text: */ "Other Display Text 3"
	),
	;

	// ... properties

	private final String value;

	private final String displayText;

	// ... constructors

	private ESomeOtherDictionary(
			final String value,
			final String displayText
	)
	{

		this.value = value;
		this.displayText = displayText;
	}

	// ... getter methods

	public String getValue() {
		return value;
	}

	public String getDisplayText() {
		return displayText;
	}
}
