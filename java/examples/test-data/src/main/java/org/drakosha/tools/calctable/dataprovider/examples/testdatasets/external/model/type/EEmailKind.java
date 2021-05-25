package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type;

/**
 * Enumeration representation of the dictionary 'Email Kind'.
 * <p>
 * Description: …
 */
public enum EEmailKind {

	// ... value constants

	PRIVATE(
			/* value: */ "P",
			/* display text: */ "private"
	),
	WORK(
			/* value: */ "W",
			/* display text: */ "work"
	),
	;

	// ... properties

	private final String value;

	private final String displayText;

	// ... constructors

	private EEmailKind(
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
