package ${packageName};

/**
 * Enumeration representation of the dictionary '${dictionary.name}'.
 * <p>
 * Description: ${dictionary.description}
 */
<#assign enumClassName=toEnumClassName(dictionary.name)>
public enum ${enumClassName} {

	// ... value constants

<#list dictionary.dictionaryEntries as dictionaryEntry>
<#assign enumConstantName=toEnumConstantName(dictionaryEntry.displayText)>
	${enumConstantName}(
		/* value: */ "${dictionaryEntry.value}",
		/* display text: */ "${dictionaryEntry.displayText}"
	),
</#list>
	;
	
	// ... properties
	
	private final String value;
	
	private final String displayText;
	
	// ... constructors
	
	private ${enumClassName}(
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
