package org.business.tools.calctable.dataprovider.examples.dictionaries.generated.type;

/**
 * Enumeration representation of the dictionary 'Some Dictionary'.
 * <p>
 * Description: "Some Dictionary" Description
 */
public enum ESomeDictionary {

	// ... value constants

	DISPLAY_TEXT_1(
		/* value: */ "value_1",
		/* display text: */ "Display Text 1"
	),
	DISPLAY_TEXT_2(
		/* value: */ "value_2",
		/* display text: */ "Display Text 2"
	),
	;
	
	// ... properties
	
	private final String value;
	
	private final String displayText;
	
	// ... constructors
	
	private ESomeDictionary(
		final String value,
		final String displayText
	) {
	
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
