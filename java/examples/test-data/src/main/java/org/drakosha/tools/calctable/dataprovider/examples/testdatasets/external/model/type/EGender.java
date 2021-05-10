package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type;

/**
 * Enumeration representation of the dictionary 'Gender'.
 * <p>
 * Description: …
 */
public enum EGender {

	// ... value constants

	MALE(
		/* value: */ "M",
		/* display text: */ "male"
	),
	FEMALE(
		/* value: */ "F",
		/* display text: */ "female"
	),
	;
	
	// ... properties
	
	private final String value;
	
	private final String displayText;
	
	// ... constructors
	
	private EGender(
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
