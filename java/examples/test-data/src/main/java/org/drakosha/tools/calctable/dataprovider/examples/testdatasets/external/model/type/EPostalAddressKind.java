package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.external.model.type;

/**
 * Enumeration representation of the dictionary 'Postal Address Kind'.
 * <p>
 * Description: …
 */
public enum EPostalAddressKind {

	// ... value constants

	HOME(
			/* value: */ "H",
			/* display text: */ "home"
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

	private EPostalAddressKind(
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
