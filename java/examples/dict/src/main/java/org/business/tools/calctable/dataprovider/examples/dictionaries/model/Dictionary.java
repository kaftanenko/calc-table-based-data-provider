package org.business.tools.calctable.dataprovider.examples.dictionaries.model;

import java.util.List;

public class Dictionary
		extends
		AbstractDomainObject
{

	// ... properties

	private final String name;

	private final String description;

	private final List<DictionaryEntry> dictionaryEntries;

	// ... constructors

	public Dictionary(
			final String name,
			final String description,
			final List<DictionaryEntry> dictionaryEntries
	)
	{

		super();
		this.name = name;
		this.description = description;
		this.dictionaryEntries = dictionaryEntries;
	}

	// ... getter methods

	public String getName() {

		return name;
	}

	public String getDescription() {

		return description;
	}

	public List<DictionaryEntry> getDictionaryEntries() {

		return dictionaryEntries;
	}

}
