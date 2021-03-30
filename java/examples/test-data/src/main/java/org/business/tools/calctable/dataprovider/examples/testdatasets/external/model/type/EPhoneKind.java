package org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.type;

/**
 * Enumeration representation of the dictionary 'Phone Kind'.
 * <p>
 * Description: …
 */
public enum EPhoneKind {

	// ... value constants

	HOME(
		/* value: */ "H",
		/* display text: */ "home"
	),
	MOBILE(
		/* value: */ "M",
		/* display text: */ "mobile"
	),
	WORK(
		/* value: */ "W",
		/* display text: */ "work"
	),
	WORK_MOBILE(
		/* value: */ "WM",
		/* display text: */ "work mobile"
	),
	;
	
	// ... properties
	
	private final String value;
	
	private final String displayText;
	
	// ... constructors
	
	private EPhoneKind(
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
